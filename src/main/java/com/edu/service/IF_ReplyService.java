package com.edu.service;

import java.util.List;

import com.edu.vo.PageVO;
import com.edu.vo.ReplyVO;

/**
 * 이 인터페이스는 댓글 DAO를이용해서 댓글CRUD를 구현하는 서비스
 * @author 김상훈
 *
 */
public interface IF_ReplyService {
	public void deleteReply(ReplyVO replyVO) throws Exception;
	public void updateReply(ReplyVO replyVO) throws Exception;
	public void insertReply(ReplyVO replyVO) throws Exception; // replyVO 필드값은 jsp post로 전달받음
	public int countReply(Integer bno) throws Exception;
	public List<ReplyVO> selectReply(Integer bno, PageVO pageVO) throws Exception; 
}
