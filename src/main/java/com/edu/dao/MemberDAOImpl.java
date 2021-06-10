package com.edu.dao;

/**
 *  이 클래슨느 IF_memberDAO 인터페이스를 구현하는 클래스 입니다.
 *  implements 키워드로 상속을 받습니다.
 *  단, DAO기능의 구현클래스는 스프링빈으로 등록이 필요. 그래서, @Repository
 *  @author 김상훈
 */

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;



@Repository
public class MemberDAOImpl implements IF_MemberDAO {
	@Inject // SqlSession 스프링빈을 주입합니다. = 옛말로 객체를 생성한다고 함
	private SqlSession sqlSession;
	
	@Override // 부모 인터페이스의 메소드를 상속해서 재정의합니다.
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception {
		//  SqlSession의 메소드를 이용해서 매퍼 쿼리를 사용합니다.
		List<MemberVO> listMember = sqlSession.selectList("memberMapper.selectMember", pageVO);
		return listMember;
	}

	@Override
	public int countMember(PageVO pageVO) throws Exception {
		// SqlSession bean의 메소드를 이용해서 mapper query를 실행 (아래)
		int totalCount = sqlSession.selectOne("memberMapper.countMember", pageVO);
		return totalCount;
	}

	@Override
	public void insertMember(MemberVO memberVO) throws Exception {
		// SqlSession bean의 메소드를 이용해서 mapper query를 실행 (아래)
		sqlSession.insert("memberMapper.insertMember", memberVO);
	}

	@Override
	public void deleteMember(String user_id) throws Exception {
		// SqlSession bean의 메소드를 이용해서 mapper query를 실행 (아래)
		sqlSession.delete("memberMapper.deleteMember", user_id);
	}

	@Override
	public MemberVO readMember(String user_id) throws Exception {
		// 데이터베이스 마이바티스 쿼리를 호출(실행)
		MemberVO memberVO = sqlSession.selectOne("memberMapper.readMember", user_id);
		return memberVO;
	}

	@Override
	public void updateMember(MemberVO memberOne) throws Exception {
		// DB 마이바티스 쿼리 호출(아래) 
		sqlSession.update("memberMapper.updateMember", memberOne);
	}

}
