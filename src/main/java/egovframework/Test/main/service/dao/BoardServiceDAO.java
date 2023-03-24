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
	// 좋아요 
	public int updateLikeCnt(TestBoardServiceVO vo);
	// 좋아요 체크
	public int insertLikeBoard(TestBoardServiceVO vo);
	// 좋아요 체크2
	public int checkLike(TestBoardServiceVO vo);
	// 좋아요 총개수
	public TestBoardServiceVO likeCntTot(TestBoardServiceVO vo);
	// 좋아요 개수 삭제
	public int cancleLikeCnt(TestBoardServiceVO vo);
	// 좋아요 테이블 삭제
	public int deleteLikeBoard(TestBoardServiceVO vo);
	// 게시글 파일 업로드
	public void insertBoard(TestBoardServiceVO vo);
	// 게시글 파일 수정
	public void updateBoard(TestBoardServiceVO vo);
	// 새로운 게시글 조회
	public List<TestBoardServiceVO> selectTestRownum(TestBoardServiceVO vo);
}
