package com.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.dao.IF_BoardDAO;
import com.edu.dao.IF_ReplyDAO;
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
	@Inject
	private IF_BoardDAO boardDAO;
	@Inject
	private IF_ReplyDAO replyDAO;
	
	@Override
	public List<AttachVO> readAttach(Integer bno) throws Exception {
		return boardDAO.readAttach(bno);
	}

	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		// 페이징 처리시 PageVO의 totalCount변수에 사용될 값을 리턴값으로 받음
		return boardDAO.countBoard(pageVO);
	}
	
	@Transactional
	@Override
	public void deleteBoard(int bno) throws Exception {
		// 게시물 삭제할 때, 총 3개의 메소드가 실행이 되어야합니다(1.댓글, 2.첨부파일 삭제 후 -> 3.게시물 삭제)
		/*
		 * 트랜잭션이 필요합니다 <작업순서도>
		 * 1. 첨부파일	삭제 
		 * 2. 게시물 	삭제 (에러로 인해 삭제가 안되어 체크포인트로 롤백해야할 상황이 생김)
		 * 위와 같은 상황을 방지하는 목적의 @Transantional
		 * 특이사항 : 첨부파일은 DB만 삭제 + 실제 업로드된 파일 삭제 2개를 실행해야함 
		 */
		
		boardDAO.deleteAttachAll(bno);
		replyDAO.deleteReplyAll(bno);
		boardDAO.deleteBoard(bno);
	}
	
	@Transactional
	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		// 첨부파일이 있으면, updateAttach -> updateBoard 2개의 메소드가 필요
		
		boardDAO.updateBoard(boardVO);
		// 첨부파일 DB처리 ↓
		int bno = boardVO.getBno();
		String[] save_file_names = boardVO.getSave_file_names();
		String[] real_file_names = boardVO.getReal_file_names();
		if (save_file_names == null) { return; } // 조건 미부합시 updateBoard() 스택이 끝남
		AttachVO attachVO = new AttachVO();
		int index = 0;
		String real_file_name = "";
		for(String save_file_name : save_file_names) {
			real_file_name = real_file_names[index];
			if (real_file_name != null) {
				attachVO.setBno(bno);
				attachVO.setSave_file_name(save_file_name);
				attachVO.setReal_file_name(real_file_name);
				
				boardDAO.updateAttach(attachVO);
			}
			index++;
		}
	}
	
	@Transactional // All or NotAll
	@Override
	public BoardVO readBoard(int bno) throws Exception {
		// 게시물 상세보기시 readBoard -> updateViewCount 2개의 메소드가 필요
		boardDAO.updateViewCount(bno);
		BoardVO boardVO = boardDAO.readBoard(bno);
		return boardVO;
	}

	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		// 첨부파일이 있으면, [부모] 게시물 insertBoard -> [자식]첨부파일 insertAttach 실행
		
		// 게시물 등록 + 반환값으로 bno 추가
		int bno = boardDAO.insertBoard(boardVO);
		// 첨부파일 등록 1개 이살일 때 가정해서 처리
		String[] save_file_names = boardVO.getSave_file_names(); 	// 폴더 저장용 파일명들
		String[] real_file_names = boardVO.getReal_file_names();	// UI용 배열 파일명들
		if (save_file_names == null) { return; } // 리턴발생시, insertBoard() 함수가 스택에서 사라짐  
		// ------------------------↓첨부파일이 null이 아닐 때 진행 됨↓-----------------
		int index = 0;				
		String real_file_name = ""; // UI용 1개의 파일명
		AttachVO attachVO = new AttachVO();
		for(String save_file_name : save_file_names) { // 첨부파일 개수만큼 반복진행
			if (save_file_name != null) {
				real_file_name = real_file_names[index];
				attachVO.setBno(bno);
				attachVO.setReal_file_name(real_file_name);
				attachVO.setSave_file_name(save_file_name);
				
				boardDAO.insertAttach(attachVO);
			}
			index++;
		}
	}

	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		//  DAO 1개 호출
		return boardDAO.selectBoard(pageVO);
	}

}
