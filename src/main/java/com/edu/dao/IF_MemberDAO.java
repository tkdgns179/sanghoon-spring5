package com.edu.dao;

import java.util.List;

import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 인터페이스는 회원관리에 관련된 CRUD 메소드 명세가 포함된 파일
 * 이 인터페이스는 메소드명만 있고, {구현내용}이 없는 목록파일 
 * @author 김상훈
 *
 */
public interface IF_MemberDAO {
	// List<제네릭타입> : MemberVO 1개의 레코드를 여러 개 출력을 위해 List<클래스형> 감싸주면
	// 다수의 레코드를 저장할 수 있는 형태가 됩니다.
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception;
	// 이 곳에 CRUD 메소드가 계속 추가됩니다.
	
	// 회원의 전체 명수를 구합니다.
	public int countMember(PageVO pageVO) throws Exception;

	public void insertMember(MemberVO memberVO) throws Exception;

	public void deleteMember(String user_id) throws Exception;

	public MemberVO readMember(String user_id) throws Exception;

	public void updateMember(MemberVO memberOne) throws Exception;
}
