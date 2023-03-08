package egovframework.Test.main.service.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import egovframework.Test.main.service.TestBoardServiceVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("boardServiceDAO")
public interface BoardServiceDAO {
	// 게시물 조회
	public List<TestBoardServiceVO> selectBoardList(@Param("begin")int begin, @Param("end") int end);
	// 게시물 개수 조회
	public int totalSelectBoardList();
	// 게시물 상세보기
	public TestBoardServiceVO selectView(TestBoardServiceVO vo);
	// 게시물 조회수 조회
	public int viewCount(TestBoardServiceVO vo);
	// 조회순 나열
	public List<TestBoardServiceVO> orderByView(TestBoardServiceVO vo);
	// 게시글 검색 페이징
//	public List<TestBoardServiceVO> search(@Param("begin")int begin, @Param("end") int end);
	// 게시글 검색 페이징
	public List<TestBoardServiceVO> search(TestBoardServiceVO vo);
	
}
