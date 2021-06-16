package com.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.vo.BoardTypeVO;

/**
 * 이 클래스는 게시판 생성관리 DAO입니다.
 * @author 김상훈
 *
 */
@Repository
public class BoardTypeDAOimpl implements IF_BoardTypeDAO {
	// sqlSession Template을 DI해줍니다.
	@Inject // JAVA 1.8부터 나온 신규 어노테이션입니다.
	private SqlSession sqlSession;
	
	
	@Override
	public void deleteBoardType(String board_type) throws Exception {
		sqlSession.delete("boardTypeMapper.deleteBoardType", board_type);
	}

	@Override
	public void updateBoardType(BoardTypeVO boardTypeVO) throws Exception {
		sqlSession.update("boardTypeMapper.updateBoardType", boardTypeVO);
	}

	@Override
	public BoardTypeVO readBoardType(String board_type) throws Exception {
		return sqlSession.selectOne("boardTypeMapper.readBoardType", board_type);
	}

	@Override
	public void insertBoardType(BoardTypeVO boardTypeVO) throws Exception {
		sqlSession.insert("boardTypeMapper.insertBoardType", boardTypeVO);
	}

	@Override
	public List<BoardTypeVO> selectBoardType() throws Exception {
		// sqlSession Template을 이용해서 MapperQuery를 실행 
		return sqlSession.selectList("boardTypeMapper.selectBoardType");
	}

}
