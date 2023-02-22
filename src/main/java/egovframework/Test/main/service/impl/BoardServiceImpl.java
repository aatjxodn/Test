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

	public List<TestBoardServiceVO> search(TestBoardServiceVO vo) {
		return boardServiceDAO.search(vo);
	}
	

}
