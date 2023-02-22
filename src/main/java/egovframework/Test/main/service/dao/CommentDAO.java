package egovframework.Test.main.service.dao;

import java.util.List;

import egovframework.Test.main.service.TestCommentVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("commentDAO")
public interface CommentDAO {
	// 댓글 조회
	public List<TestCommentVO> selectCommentList(TestCommentVO vo);
	// 댓글 입력
	public int insertComment(TestCommentVO vo);
	// 댓글의 대댓글 입력
	public int insertCommentComment(TestCommentVO vo);
	// 대댓글 쿼리0
	public int updateComment(TestCommentVO vo);
	// 대댓글 쿼리1
	public int updateCommentComment(TestCommentVO vo);
	// 대댓글 쿼리2
	public int MaxGroupOrder(TestCommentVO vo);
	// 댓글 개수 조회
	public int totalSelectCommentList(TestCommentVO vo);
	
}
