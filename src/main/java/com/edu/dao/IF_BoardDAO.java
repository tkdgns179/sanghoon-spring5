package com.edu.dao;

import java.util.List;

import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * 이 인터페이스는 boardMapper.xml을 접근하기 위한 DAO(Data Access Object)입니다
 * DAO와 Service를 나눈 이유 확인
 * @author 김상훈
 *
 */
public interface IF_BoardDAO {
	
	// Attach CRUD ----------------------------------------------- 
	public void deleteAttachAll(Integer bno) throws Exception;
	public void deleteAttach(String save_file_name) throws Exception;
	public void updateAttach(AttachVO attachVO) throws Exception;
	public void insertAttach(AttachVO attachVO) throws Exception;
	public List<AttachVO> readAttach(Integer bno) throws Exception; 
	// 게시물 상세보기시 조회수 올리는 메소드
	public void updateViewCount(Integer bno) throws Exception;
	// Attach CRUD ----------------------------------------------- 
	
	// Board CRUD ---------------------------------------------------
	public int countBoard(PageVO pageVO) throws Exception;   
	public void deleteBoard(int bno) throws Exception;
	public void updateBoard(BoardVO boardVO) throws Exception;
	public BoardVO readBoard(int bno) throws Exception;
	public int insertBoard(BoardVO boardVO) throws Exception; 
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception;
	// Board CRUD ---------------------------------------------------
}
