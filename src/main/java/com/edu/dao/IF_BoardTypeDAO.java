package com.edu.dao;

import java.util.List;

import com.edu.vo.BoardTypeVO;

/**
 * 이 인터페이스는 게시판 생성관리 쿼리를 접근하기 위한 명세서 파일입니다.
 * 그러면, DAO와 다를 것은 없지만, 나중에 DAO 10개 -> 서비스 5개로 해결이 가능하게 되어서 사용함
 * 스프링부트는 위의 과정이 1개로 합쳐져 있어서 상대적으로 간단한 프로젝트에 스프링부트를 사용함
 * @author 김상훈
 * 
 */
public interface IF_BoardTypeDAO {
	
	// CRUD 메소드 명세만 생성 (아래 5개) 
	public void deleteBoardType(String board_type) throws Exception;
	
	public void updateBoardType(BoardTypeVO boardTypeVO) throws Exception;
	
	public BoardTypeVO readBoardType(String board_type) throws Exception;
	
	public void insertBoardType(BoardTypeVO boardTypeVO) throws Exception;
	
	// BoardTypeVO 1개의 레코드를 저장한 클래스를 List<제네릭타입>형 다중 레코드로 묶어서 받습니다.
	public List<BoardTypeVO> selectBoardType() throws Exception;
	
}
