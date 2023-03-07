package egovframework.Test.main.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
	
    
	@RequestMapping(value="/search.do",produces = "application/json; charset=utf8", method=RequestMethod.POST)
	@ResponseBody
	public List<TestBoardServiceVO> search(TestBoardServiceVO vo) {
		
		List<TestBoardServiceVO> search = boardService.search(vo);
		
		System.out.println("ddddddddddddddddddddddddddd :::" + search);
		return search;
	}
	
	

}
