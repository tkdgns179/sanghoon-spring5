package com.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.dao.IF_ReplyDAO;
import com.edu.vo.PageVO;
import com.edu.vo.ReplyVO;

/**
 * 이 클래스는 댓글 DAO 일부 구현하여 CRUD를 실행하는 서비스클래스입니다
 * @author 김상훈
 *
 */
@Service
public class ReplyServiceImpl implements IF_ReplyService{
	@Inject
	private IF_ReplyDAO replyDAO;
	
	@Transactional
	@Override
	public void deleteReply(ReplyVO replyVO) throws Exception {
		replyDAO.deleteReply(replyVO);
		replyDAO.replyCountUpdate(replyVO.getBno(), -1);
	}

	@Override
	public void updateReply(ReplyVO replyVO) throws Exception {
		replyDAO.updateReply(replyVO);
	}
	
	@Transactional // All or NotAll
	@Override
	public void insertReply(ReplyVO replyVO) throws Exception {
		// 2개의 DAO를 호출(실행)
		replyDAO.insertReply(replyVO);
		replyDAO.replyCountUpdate(replyVO.getBno(), 1);
	}

	@Override
	public int countReply(Integer bno) throws Exception {
		// 해당 게시물에 달린 댓글의 전체 개수 호출
		return replyDAO.countReply(bno);
	}

	@Override
	public List<ReplyVO> selectReply(PageVO pageVO) throws Exception {
		// DAO 객체 사용
		return replyDAO.selectReply(pageVO);
	}

}
