package egovframework.Test.main.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType;

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

	

	// 함수 집어 넣어서 암호화해서 회원가입시키기
	@RequestMapping("/signUp.do")
	public String signUp(TestMemberVO vo, Model model) throws Exception {
		
		String txt = vo.getPassword();
		vo.setPassword(egovframework.common.util.StringUtils.testSHA256(txt));

		System.out.println(txt);
		System.out.println(egovframework.common.util.StringUtils.testSHA256(txt));

		memberService.signUp(vo);

		return "/login";
	}

	@RequestMapping("/login.do")
	public String login(TestMemberVO vo, HttpSession session, Model model, HttpServletResponse response) throws Exception {

		System.out.println("로그인 시작");
		vo.setPassword(egovframework.common.util.StringUtils.testSHA256(vo.getPassword()));
		
		TestMemberVO user = memberService.login(vo);
		
		if (user != null) {

			System.out.println(">> 로그인 성공");
			session.setAttribute("user", user);
			
			System.out.println("유저  : " + user);
			
			return "forward:selectBoardList.do";

		} else {
			System.out.println(">> 로그인 실패");
			System.out.println("유저  : " + user);
			alertUtils.alert(response, "아이디 또는 비밀번호가 틀렸습니다.");

			return "/login";

		}

	}

	@RequestMapping("/logout.do")
	public String logout(TestMemberVO vo, HttpSession session) {
		
		TestMemberVO userOut = memberService.logout(vo);
		
		LOGGER.debug("로그아웃 ID : " + userOut);
		
		session.invalidate();
		
		return "/login";
	}

	@RequestMapping("/selectBoardList.do")
	public String selectBoardList(TestPagingVO p, TestBoardServiceVO vo, TestMemberVO vo2,
			HttpServletRequest request, HttpServletResponse response,
			Model model, HttpSession session) throws IOException {

		TestMemberVO user = (TestMemberVO) session.getAttribute("user");

		if (user == null) {
			alertUtils.alert(response, "아이디 기록이 없습니다.");
			return "/login";
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
	public String selectView(
			@RequestParam(value = "paymentId") String paymentId,
			TestBoardServiceVO vo, TestCommentVO vo2, Model model,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		// 유저 가져오기
		TestMemberVO user = (TestMemberVO) session.getAttribute("user");
		String userId = user.getId();
		vo.setId(userId);
		Cookie viewCookie = null;
		Cookie[] cookies = request.getCookies();

		System.out.println("cookie : " + cookies);

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				// System.out.println("쿠키 이름 : "+cookies[i].getName());
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
		} else {
			System.out.println("cookies 확인 로직 : 쿠키가 없습니다.");
		}
		// 만들어진 쿠키가 없음을 확인
		if (viewCookie == null) {
			System.out.println("viewCookie 확인 로직 : 쿠키 없음");
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
				System.out.println("쿠키 넣을때 오류  : " + e.getMessage());
				e.getStackTrace();
			}
			// 만들어진 쿠키가 있으면 증가로직 진행하지 않음
		} else {
			System.out.println("viewCookie 확인 로직 : 쿠키 있음");
			String value = viewCookie.getValue();
			System.out.println("viewCookie 확인 로직 : 쿠키 value : " + value);
		}

		return "/selectView";
	}
	
	
	@RequestMapping("/search.do")
	public String search(TestBoardServiceVO vo, Model model, 
			HttpServletRequest request, TestPagingVO p, HttpServletResponse response) throws IOException {
		
		String idx = request.getParameter("idx");
		int keyWord = Integer.parseInt(request.getParameter("keyWord"));
		
		if (String.valueOf(keyWord) == null) {
			alertUtils.alert(response, "ㅋㅎㅎㅎㅎㅎㅎ");
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

}
