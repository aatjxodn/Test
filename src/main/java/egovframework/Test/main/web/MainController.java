package egovframework.Test.main.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import egovframework.Test.main.service.TestBoardServiceVO;
import egovframework.Test.main.service.TestCommentVO;
import egovframework.Test.main.service.TestMemberVO;
import egovframework.Test.main.service.TestPagingVO;
import egovframework.Test.main.service.impl.BoardServiceImpl;
import egovframework.Test.main.service.impl.CommentImpl;
import egovframework.Test.main.service.impl.MemberImpl;
import egovframework.common.util.StringUtils;
import egovframework.common.util.alertUtils;

@Controller
public class MainController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	@Resource(name = "memberService")
	private MemberImpl memberService;

	@Resource(name = "boardService")
	private BoardServiceImpl boardService;

	@Resource(name = "commentService")
	private CommentImpl commentService;

	
	@RequestMapping("/main.do")
	public String mainPage() {
		return "login";
	}
	
	@RequestMapping("/goSignUp.do")
	public String goSignUp() {
		return "/goSignUp";
	}
	
	@RequestMapping("/sample.do")
	public String sample() {
		return "sample";
	}
	

	// 함수 집어 넣어서 암호화해서 회원가입시키기
	@RequestMapping("/signUp.do")
	public String signUp(TestMemberVO vo, Model model) throws Exception {
		
		String txt = vo.getPassword();
		vo.setPassword(egovframework.common.util.StringUtils.testSHA256(txt));

		System.out.println(egovframework.common.util.StringUtils.testSHA256(txt));

		memberService.signUp(vo);
		
		LOGGER.info("회원가입 성공 성공");

		return "/login";
	}

	@RequestMapping("/login.do")
	public String login(TestMemberVO vo, HttpSession session, Model model, HttpServletResponse response) throws Exception {

		System.out.println("로그인 시작");
		vo.setPassword(egovframework.common.util.StringUtils.testSHA256(vo.getPassword()));
		
		TestMemberVO user = memberService.login(vo);
		
		if (user != null) {

			session.setAttribute("user", user);
			
			LOGGER.info("로그인 성공 : " + user);
			
			return "forward:selectBoardList.do";

		} else {
			
			alertUtils.alert(response, "아이디 또는 비밀번호가 틀렸습니다.");
			LOGGER.info("로그인 실패 : " + user);
			
			return "/login";

		}

	}

	@RequestMapping("/logout.do")
	public String logout(TestMemberVO vo, HttpSession session, HttpServletResponse response) {
		
		TestMemberVO userOut = memberService.logout(vo);
		
		LOGGER.debug("로그아웃 ID : " + userOut);
		
		session.invalidate();
		
		return "redirect:/main.do";
	}

	@RequestMapping("/selectBoardList.do")
	public String selectBoardList(TestPagingVO p, TestBoardServiceVO vo, TestMemberVO vo2,
			HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) throws IOException {

		TestMemberVO user = (TestMemberVO) session.getAttribute("user");

		if (user == null) {
			alertUtils.alertAndMovePage(response, "아이디 기록이 없습니다.", "main.do");
		}
		
		String localIp = StringUtils.IPetRemoteAddr(request);
		String ipCheck = memberService.admin_ipCheck(vo2);
		String[] arrIp = ipCheck.split(",");		
		// db ip 배열이랑 위에 ip 체크
		for (int i=0; i < arrIp.length; i++) {
			if (localIp.trim().equalsIgnoreCase(arrIp[i].replace("\"", "").trim())) {
				// 로그 찍기
				LOGGER.debug("접근 ip : ["+localIp+"]");
				
				p.setPages(p, boardService);

				List<TestBoardServiceVO> selectBoardList = boardService.selectBoardList(p.getBegin(), p.getEnd());
				List<TestBoardServiceVO> orderByView = boardService.orderByView(vo);

				model.addAttribute("page", p);
				model.addAttribute("selectBoardList", selectBoardList);
				model.addAttribute("totalSelectBoardList", boardService.totalSelectBoardList());
				model.addAttribute("orderByView", orderByView);

				System.out.println(selectBoardList);
				
				return "/boardList";
			}
		}
		
		// 로그 찍기
		LOGGER.debug("접근 불가능 ip : ["+localIp+"]");
		// alert 클래스 호출
		alertUtils.alertAndMovePage(response, "접근할 수 없는 ip입니다. 관리자에게 문의하세요.", "main.do");

		return null;
	}

	@RequestMapping("/selectView.do")
	public String selectView(@RequestParam(value = "paymentId") String paymentId, TestBoardServiceVO vo, TestCommentVO vo2, Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		
		// 유저 가져오기
		TestMemberVO user = (TestMemberVO) session.getAttribute("user");
		// 유저가 없다면 로그인화면으로 이동
		if (user == null) {
			alertUtils.alertAndMovePage(response, "아이디 기록이 없습니다.", "main.do");
		}
		
		String userId = user.getId();
		vo.setId(userId);
		Cookie viewCookie = null;
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				// 만들어진 쿠키들을 확인하며, 만약 들어온 적 있다면 생성되었을 쿠키가 있는지 확인
				if (cookies[i].getName().equals(paymentId)
						&& cookies[i].getValue().equals(userId)) {
					System.out.println("if문 쿠키 이름 : " + cookies[i].getName());
					// 찾은 쿠키를 변수에 저장
					viewCookie = cookies[i];
					// 쿠키가 있으니 조회수 증가 x 
					TestBoardServiceVO selectView = boardService.selectView(vo);
					int totalSelectCommentList = commentService.totalSelectCommentList(vo2);
					int checkLike = boardService.checkLike(vo);
					TestBoardServiceVO likeCntTot = boardService.likeCntTot(vo);
					
					model.addAttribute("likeCntTot", likeCntTot);
					model.addAttribute("checkLike", checkLike);
					model.addAttribute("selectView", selectView);
					model.addAttribute("totalSelectCommentList", totalSelectCommentList);
				}
			}
		}
		// 만들어진 쿠키가 없음을 확인
		if (viewCookie == null) {
			
			try {
				// 이 페이지에 왔다는 증거용(?) 쿠키 생성
				Cookie newCookie = new Cookie(paymentId, userId);
				// 쿠키 시간 설정
				newCookie.setMaxAge(60*60*24);
				// 쿠키 넣기
				response.addCookie(newCookie);
				// 쿠키가 없으니 쿠키 생성하면서 상세보기 진행
				TestBoardServiceVO selectView = boardService.selectView(vo);
				// 상세보기에 달린 댓글 개수 조회
				int totalSelectCommentList = commentService.totalSelectCommentList(vo2);
				// 상세보기 조회수 조회
				boardService.viewCount(vo);
				int checkLike = boardService.checkLike(vo);
				TestBoardServiceVO likeCntTot = boardService.likeCntTot(vo);
				
				model.addAttribute("likeCntTot", likeCntTot);
				model.addAttribute("checkLike", checkLike);
				model.addAttribute("selectView", selectView);
				model.addAttribute("totalSelectCommentList", totalSelectCommentList);

			} catch (Exception e) {
				e.getStackTrace();
			}
			// 만들어진 쿠키가 있으면 증가로직 진행하지 않음
		} else {
			viewCookie.getValue();
		}

		return "/selectView";
	}
	
	
	@RequestMapping("/search.do")
	public String search(TestBoardServiceVO vo, Model model, 
			HttpServletRequest request, TestPagingVO p, HttpServletResponse response) throws IOException {
		
		String idx = request.getParameter("idx");
		int keyWord = Integer.parseInt(request.getParameter("keyWord"));
		
		
		if (String.valueOf(keyWord) == null) {
			alertUtils.alert(response, "검색어가 없습니다.");
		}
		
		if (idx.equalsIgnoreCase("0")) {
			vo.setPaymentId(keyWord);
			vo.setIdx(idx);
			
			List<TestBoardServiceVO> searchList = boardService.search(vo);
			int searchTot = searchList.size();
			List<TestBoardServiceVO> orderByView = boardService.orderByView(vo);
			
			model.addAttribute("orderByView", orderByView);
			model.addAttribute("searchList", searchList);
			model.addAttribute("searchTot", searchTot);
			
			
		} else if (idx.equalsIgnoreCase("1")) {
			vo.setCustomerId(keyWord);
			vo.setIdx(idx);
			
			List<TestBoardServiceVO> searchList = boardService.search(vo);
			int searchTot = searchList.size();
			List<TestBoardServiceVO> orderByView = boardService.orderByView(vo);
			
			model.addAttribute("orderByView", orderByView);
			model.addAttribute("searchList", searchList);
			model.addAttribute("searchTot", searchTot);
			
		}
		
		return "/search";
	}
	
	@RequestMapping("/goInsertBoard.do")
	public String goInsertBoard() {
		return "insertBoard";
	}
	
	// 글쓰기
    @RequestMapping(value = "/insertBoard.do")
    public String write(@ModelAttribute("vo") TestBoardServiceVO vo) throws Exception {
 
        // 파일 업로드 처리
        String fileName = null;
        MultipartFile uploadFile = vo.getUploadFile();
        
        if (!uploadFile.isEmpty()) {
            String originalFileName = uploadFile.getOriginalFilename();
            vo.setOriFileName(originalFileName);
            String ext = FilenameUtils.getExtension(originalFileName); // 확장자 구하기
            UUID uuid = UUID.randomUUID(); // UUID 구하기
            fileName = uuid + "." + ext;
            uploadFile.transferTo(new File("D:/egovTest/eGovFrameDev-3.10.0-64bit/workspace/Test/src/main/webapp/images/egovframework/upload/" + fileName));
        }
        vo.setFileName(fileName);
 
        boardService.insertBoard(vo);
 
        return "redirect:selectBoardList.do";
    }
    
	// 글수정
    @RequestMapping(value = "/updateTest.do")
    public String updateTest(@ModelAttribute("testVo") TestBoardServiceVO vo, HttpServletRequest request) throws Exception {
        
        // 파일 업로드
        String fileName = null;
        MultipartFile uploadFile = vo.getUploadFile();
        if (!uploadFile.isEmpty()) {
            String originalFileName = uploadFile.getOriginalFilename();
            String ext = FilenameUtils.getExtension(originalFileName); // 확장자구하기
            UUID uuid = UUID.randomUUID(); // uuid구하기
            fileName = uuid + "." + ext;
            uploadFile.transferTo(new File("D:/egovTest/eGovFrameDev-3.10.0-64bit/workspace/Test/src/main/webapp/images/egovframework/upload/" + fileName));
            vo.setFileName(fileName);
        }else{
        	boardService.updateBoard(vo);
        	return "redirect:selectBoardList.do";
        }
        
        boardService.updateBoard(vo);
        return "redirect:selectBoardList.do";
        // return "redirect:testDetail.do?testId=" + vo.getPaymentId();
    }

}
