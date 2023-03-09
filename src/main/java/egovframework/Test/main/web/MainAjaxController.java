package egovframework.Test.main.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import org.springframework.web.bind.annotation.RestController;

import egovframework.Test.main.service.TestBoardServiceVO;
import egovframework.Test.main.service.TestCommentVO;
import egovframework.Test.main.service.impl.BoardServiceImpl;
import egovframework.Test.main.service.impl.CommentImpl;
import egovframework.Test.main.service.impl.MemberImpl;
import egovframework.common.util.alertUtils;

@RestController
public class MainAjaxController {
	
	@Resource(name="memberService")
	private MemberImpl memberService;
	
	@Resource(name="boardService")
	private BoardServiceImpl boardService;
	
	@Resource(name="commentService")
	private CommentImpl commentService;
	
	@RequestMapping(value="/selectCommentList.do",produces = "application/json; charset=utf8", method=RequestMethod.POST)
	@ResponseBody
	public List<TestCommentVO> selectCommentList(TestCommentVO vo, Model model, HttpSession session) {
		
		List<TestCommentVO> selectCommentList = commentService.selectCommentList(vo);
		
		System.out.println("selectCommentList : " + selectCommentList);
		
		return selectCommentList;
	}

	@RequestMapping(value="/insertComment.do",produces = "application/json; charset=utf8", method=RequestMethod.POST)
	@ResponseBody
	public TestCommentVO insertComment(TestCommentVO vo, Model model ,HttpSession session) {
		
		if(commentService.insertComment(vo) > 0 ) {
			if (commentService.updateComment(vo) > 0){
				
			}
		}

		return vo;
	}
	
	@RequestMapping(value="/insertCommentComment.do",produces = "application/json; charset=utf8", method=RequestMethod.POST)
	@ResponseBody
	public TestCommentVO insertCommentComment(TestCommentVO vo, Model model, HttpSession session) {
		
		int maxGroupOrder = commentService.MaxGroupOrder(vo);
		if (maxGroupOrder != 0) {
			vo.setGroupOrder(maxGroupOrder + 1);
			commentService.insertCommentComment(vo);
			
		} else {
			commentService.insertCommentComment(vo);
		}
		
		commentService.updateCommentComment(vo);
	
		return vo;
	}
	
	@RequestMapping("/idcheck.do")
    @ResponseBody
    public int idcheck(@RequestBody String id) {
        
        int count = 0;
        
        count = memberService.idcheck(id);
        
        System.out.println("count :" + count);
        return count;
    }
	
	
	@RequestMapping("/deleteComment.do")
    @ResponseBody
    public int deleteComment(TestCommentVO vo, @RequestParam("deleteSeq") int deleteSeq) {
		
		vo.setSeq(deleteSeq);
		
		int deleteCom = commentService.deleteComment(vo);
		
		return deleteCom;
	}
	
	@RequestMapping("/goUpdateComment.do")
    @ResponseBody
    public int goUpdateComment(TestCommentVO vo) {
		
		int deleteCom = commentService.goUpdateComment(vo);
		
		return deleteCom;
	}
	
    
//	@RequestMapping(value="/search.do")
//	@ResponseBody
//	public List<TestBoardServiceVO> search(TestBoardServiceVO vo, 
//					@RequestParam(value="idx", defaultValue="0") String idx, @RequestParam(value="keyWord", defaultValue="0") int keyWord, HttpServletRequest request, @RequestBody String paramData)
//								 throws NullPointerException {
//		
//		System.out.println("idx : " + idx);
//		System.out.println("keyWord : " + keyWord);
//		System.out.println("idx : " + request.getParameter("idx"));
//		System.out.println(paramData);
//		
//		if (idx.equalsIgnoreCase("0")) {
//			vo.setPaymentId(keyWord);
//			
//			List<TestBoardServiceVO> search = boardService.search(vo);
//			
//			System.out.println("search1 : " + search);
//			
//			return search;
//			
//		} else if (idx.equalsIgnoreCase("1")) {
//			vo.setCustomerId(keyWord);
//			
//			List<TestBoardServiceVO> search = boardService.search(vo);
//			
//			System.out.println("search2 : " + search);
//			
//			return search;
//		}
//		
//		return null;
//	}
	
	@RequestMapping(value="/likeView.do")
	@ResponseBody
	public int updateLikeCnt(TestBoardServiceVO vo, HttpServletResponse response, Model model) throws IOException {

		int null2 = 0;
		int checkLike = boardService.checkLike(vo);
		
		if (checkLike == 0) {
			boardService.insertLikeBoard(vo);
			int updateLikeCnt = boardService.updateLikeCnt(vo);
			
			model.addAttribute("checkLike", checkLike);
			
			return updateLikeCnt;
		}
		
		return null2;
	}
	
	@RequestMapping(value="/cancleLikeView.do")
	@ResponseBody
	public int cancleLikeView(TestBoardServiceVO vo, HttpServletResponse response, Model model) throws IOException {

		int null2 = 0;
		int checkLike = boardService.checkLike(vo);
		
		if (checkLike == 1) {
			int deleteLikeBoard = boardService.deleteLikeBoard(vo);
			int cancleLikeCnt = boardService.cancleLikeCnt(vo);
			
			model.addAttribute("checkLike", checkLike);
			
			return cancleLikeCnt;
		}
		return null2;
	}
	
	
	

}
