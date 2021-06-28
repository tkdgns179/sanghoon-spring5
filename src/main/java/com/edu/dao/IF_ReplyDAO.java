package com.edu.dao;

import java.util.List;

import com.edu.vo.PageVO;
import com.edu.vo.ReplyVO;

/**
 * 이 인터페이스는 댓글 쿼리를 입출력(접근)하는 메소드 명세서입니다
 * @author 김상훈
 *
 */
public interface IF_ReplyDAO {
	public void deleteReplyAll(Integer bno) throws Exception;
	public void deleteReply(ReplyVO replyVO) throws Exception;
	public void updateReply(ReplyVO replyVO) throws Exception;
	public void replyCountUpdate(Integer bno, int count) throws Exception;
	public void insertReply(ReplyVO replyVO) throws Exception; // replyVO 필드값은 jsp post로 전달받음
	public int countReply(Integer bno) throws Exception;
	public List<ReplyVO> selectReply(Integer bno, PageVO pageVO) throws Exception; 
}
