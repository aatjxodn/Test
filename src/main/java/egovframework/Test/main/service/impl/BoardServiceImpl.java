package egovframework.Test.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.Test.main.service.TestBoardServiceVO;
import egovframework.Test.main.service.dao.BoardServiceDAO;

@Service("boardService")
public class BoardServiceImpl {
	
	@Resource(name="boardServiceDAO")
	private BoardServiceDAO boardServiceDAO;
	
	public List<TestBoardServiceVO> selectBoardList(int begin, int end) {
		return boardServiceDAO.selectBoardList(begin, end);
	}

	public int totalSelectBoardList() {
		return boardServiceDAO.totalSelectBoardList();
	}

	public TestBoardServiceVO selectView(TestBoardServiceVO vo) {
		return boardServiceDAO.selectView(vo);
	}
	
	public int viewCount(TestBoardServiceVO vo) {
		return boardServiceDAO.viewCount(vo);
	}

	public List<TestBoardServiceVO> orderByView(TestBoardServiceVO vo) {
		return boardServiceDAO.orderByView(vo);
	}
	
	public List<TestBoardServiceVO> search(TestBoardServiceVO vo) {
		return boardServiceDAO.search(vo);
	}

//	public List<TestBoardServiceVO> search(int begin, int end) {
//		return boardServiceDAO.search(begin, end);
//	}
	
	public int updateLikeCnt(TestBoardServiceVO vo) {
		return boardServiceDAO.updateLikeCnt(vo);
	}
	
	public int insertLikeBoard(TestBoardServiceVO vo) {
		return boardServiceDAO.insertLikeBoard(vo);
	}
	
	public int checkLike(TestBoardServiceVO vo) {
		return boardServiceDAO.checkLike(vo);
	}
	
	public TestBoardServiceVO likeCntTot(TestBoardServiceVO vo) {
		return boardServiceDAO.likeCntTot(vo);
	}
	
	public int cancleLikeCnt(TestBoardServiceVO vo) {
		return boardServiceDAO.cancleLikeCnt(vo);
	}
	
	public int deleteLikeBoard(TestBoardServiceVO vo) {
		return boardServiceDAO.deleteLikeBoard(vo);
	}

	public void insertBoard(TestBoardServiceVO vo) {
		boardServiceDAO.insertBoard(vo);
		
	}
	
	public void updateBoard(TestBoardServiceVO vo) {
		boardServiceDAO.updateBoard(vo);
	}
	
	public List<TestBoardServiceVO> selectTestRownum(TestBoardServiceVO vo) {
		return boardServiceDAO.selectTestRownum(vo);
	}

}
