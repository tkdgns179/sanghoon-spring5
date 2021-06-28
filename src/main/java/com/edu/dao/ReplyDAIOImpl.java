package com.edu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.vo.PageVO;
import com.edu.vo.ReplyVO;

/**
 * 이 클래스는 sqlSession Template을 이용해서 쿼리를 실행하는 클래스입니다
 * @author 김상훈
 *
 */
@Repository
public class ReplyDAIOImpl implements IF_ReplyDAO{
	@Inject // @Autowired @Resource도 사용가능
	private SqlSession sqlSession;
	
	@Override
	public void deleteReplyAll(Integer bno) throws Exception {
		// 모든 레코드 삭제
		sqlSession.delete("replyMapper.deleteReplyAll", bno);
	}

	@Override
	public void deleteReply(ReplyVO replyVO) throws Exception {
		// 1개의 레코드 지우기
		sqlSession.delete("replyMapper.deleteReply", replyVO);
	}

	@Override
	public void updateReply(ReplyVO replyVO) throws Exception {
		sqlSession.update("replyMapper.updateReply", replyVO);
	}

	@Override
	public void replyCountUpdate(Integer bno, int count) throws Exception {
		// 매개변수가 2개일 때, 1개의 오브젝트로 담아보냅니다. -> Map 형태로 담아서 보냅니다
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("bno", bno);
		paramMap.put("count", count);
		sqlSession.update("replyMapper.replyCountUpdate", paramMap);
	}

	@Override
	public void insertReply(ReplyVO replyVO) throws Exception {
		sqlSession.insert("replyMapper.insertReply", replyVO);
	}

	@Override // 게시판에 해당하는 댓글의 총 수 -> 페이징처리 이용
	public int countReply(Integer bno) throws Exception {
		return sqlSession.selectOne("replyMapper.countReply", bno);
	}

	@Override
	public List<ReplyVO> selectReply(Integer bno, PageVO pageVO) throws Exception {
		// sqlSession Template ("매퍼쿼리명	","매개변수명")
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("queryStartNo", pageVO.getQueryStartNo());
		paramMap.put("queryPerPageNum", pageVO.getQueryPerPageNum());
		paramMap.put("bno", bno);
		return sqlSession.selectList("replyMapper.selectReply", paramMap);
	}

}
