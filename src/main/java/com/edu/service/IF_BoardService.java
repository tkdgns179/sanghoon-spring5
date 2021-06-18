package com.edu.service;

import java.util.List;

import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * 이 인터페이스는 DAO를 호출하는 서비스 인터페이스입니다
 * DAO의 클래스의 12개의 메소드가 7개로 줄어들었습니다. 나머지 5개는 구현 클래스에서 사용합니다
 * @author 김상훈
 *
 */
public interface IF_BoardService {
		// DAO클래스에 있는 첨부파일 CRUD중 R을 제외한 메소드 제외 -> 대신 구현클래스에서 사용만합니다 
		public List<AttachVO> readAttach(Integer bno) throws Exception; 
		//  ---------------------------------------------------------------------  
		
		// Board CRUD ---------------------------------------------------
		public int countBoard(PageVO pageVO) throws Exception;   
		public void deleteBoard(int bno) throws Exception;
		public void updateBoard(BoardVO boardVO) throws Exception;
		public BoardVO readBoard(int bno) throws Exception;
		public void insertBoard(BoardVO boardVO) throws Exception; 
		public List<BoardVO> selectBoard(PageVO pageVO) throws Exception;
		// Board CRUD ---------------------------------------------------
}
