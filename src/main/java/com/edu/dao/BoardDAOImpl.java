package com.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.vo.AttachVO;
import com.edu.vo.BoardVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 게시물 CRUD를 구현하는 DAO클래스입니다
 * @author 김상훈
 *
 */
@Repository
public class BoardDAOImpl implements IF_BoardDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void deleteAttachAll(Integer bno) throws Exception {
		// 외래키(bno - BoardVO)에 첨부된 모든 attachVO를 가져온 후 삭제
		sqlSession.delete("boardMapper.deleteAttachAll", bno);
	}

	@Override
	public void deleteAttach(String save_file_name) throws Exception {
		// 기본키(PK - save_file_name)과 일치하는 attachVO 삭제
		sqlSession.delete("boardMapper.deleteAttach", save_file_name);
	}

	@Override
	public void updateAttach(AttachVO attachVO) throws Exception {
		// *주의사항* 메소드명은 update이지만 template에는 insert로 작성되어있음
		sqlSession.insert("boardMapper.updateAttach", attachVO);
	}

	@Override
	public void insertAttach(AttachVO attachVO) throws Exception {
		sqlSession.insert("boardMapper.insertAttach", attachVO);
	}

	@Override
	public List<AttachVO> readAttach(Integer bno) throws Exception {
		return sqlSession.selectList("boardMapper.readAttach", bno);
	}

	@Override
	public void updateViewCount(Integer bno) throws Exception {
		sqlSession.update("boardMapper.updateViewCount", bno);
	}
// =========================================================================================================
	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		return sqlSession.selectOne("boardMapper.countBoard", pageVO);
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		sqlSession.delete("boardMapper.deleteBoard", bno);
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		sqlSession.update("boardMapper.updateBoard", boardVO);
	}

	@Override
	public BoardVO readBoard(int bno) throws Exception {
		
		return sqlSession.selectOne("boardMapper.readBoard", bno);
	}

	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		// 아래 주석내용과 동일
		sqlSession.insert("boardMapper.insertBoard", boardVO);
	}

	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		// sqlSession Template을 사용해서 mapperQuery사용
		return sqlSession.selectList("boardMapper.selectBoard", pageVO);
	}

}
