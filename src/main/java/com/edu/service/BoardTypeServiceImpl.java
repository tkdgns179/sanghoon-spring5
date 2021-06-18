package com.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.edu.dao.IF_BoardTypeDAO;
import com.edu.vo.BoardTypeVO;

@Service // @Service 어노테이션을 작성해야 스프링 빈으로 등록이 됩니다.
public class BoardTypeServiceImpl implements IF_BoardTypeService{
	@Inject
	private IF_BoardTypeDAO boardTypeDAO; 
	
	@Override // 부모(super) 인터페이스의 메소드를 상속받아서 재정의합니다 => 오버라이드
	public void deleteBoardType(String board_type) throws Exception {
		boardTypeDAO.deleteBoardType(board_type);
	}

	@Override
	public void updateBoardType(BoardTypeVO boardTypeVO) throws Exception {
		boardTypeDAO.updateBoardType(boardTypeVO);
	}

	@Override
	public BoardTypeVO readBoardType(String board_type) throws Exception {
		return boardTypeDAO.readBoardType(board_type);
	}

	@Override
	public void insertBoardType(BoardTypeVO boardTypeVO) throws Exception {
		boardTypeDAO.insertBoardType(boardTypeVO);
	}

	@Override
	public List<BoardTypeVO> selectBoardType() throws Exception {
		// DAO 클래스 객체를 이용해서 메소드를 호출(실행)
		return boardTypeDAO.selectBoardType();
	}

}
