package com.edu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 DAO메소드를 호출하는 서비스클래스입니다.
 * @author 김상훈
 *
 */
@Service // 스프링 빈으로 등록
public class BoardServiceImpl implements IF_BoardService{

	@Override
	public List<AttachVO> readAttach(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardVO readBoard(int bno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
