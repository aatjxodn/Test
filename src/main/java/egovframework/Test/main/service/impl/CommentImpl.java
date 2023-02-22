package egovframework.Test.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.Test.main.service.TestCommentVO;
import egovframework.Test.main.service.dao.CommentDAO;

@Service("commentService")
public class CommentImpl {

	@Resource(name="commentDAO")
	private CommentDAO commentDAO;
	
	public List<TestCommentVO> selectCommentList(TestCommentVO vo) {
		return commentDAO.selectCommentList(vo);
	}
	
	public int insertComment(TestCommentVO vo) {
		return commentDAO.insertComment(vo);
	}
	
	public int updateComment(TestCommentVO vo) {
		return commentDAO.updateComment(vo);
	}
	
	public int insertCommentComment(TestCommentVO vo) {
		return commentDAO.insertCommentComment(vo);
	}
	
	public int updateCommentComment(TestCommentVO vo) {
		return commentDAO.updateCommentComment(vo);
	}
	
	public int MaxGroupOrder(TestCommentVO vo) {
		return commentDAO.MaxGroupOrder(vo);
	}
	
	public int totalSelectCommentList(TestCommentVO vo) {
		return commentDAO.totalSelectCommentList(vo);
	}
	
	
	
	
}
