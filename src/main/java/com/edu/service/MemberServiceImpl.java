package com.edu.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.edu.dao.IF_MemberDAO;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 회원관리 서비스 인터페이스를 구현하는 클래스.
 * 상속 extends, 구현 implements 키워드를 사용
 * 스프링 빈으로 등록하려면, @Service 어노테이션을 명시합니다.
 * @author 김상훈
 *
 */
@Service
public class MemberServiceImpl implements IF_MemberService{
	@Inject // IF_MemberDAO를 주입해서 객체로 사용(아래)
	private IF_MemberDAO memberDAO;
	
	Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	// 헤로쿠 클라우드에 30분 휴면상태를 꺠우는 기능을 추가
	public void herokuJobMethod() throws Exception {
		// 월~금 오전8시 부터 오후 11시까지 (미국시간 23, 0-14) 헤로쿠앱에 20분간격으로 접근
		// heroku container가 러닝할 수 있는 무료시간이 대략 한 달에 700시간정도 정해져있음 정해진 시간을 넘으면 먹통이 됨
		String urlStr = "https://sanghoon-spring5.herokuapp.com/";
		URL url = new URL(urlStr);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setUseCaches(false); 		// 접속시 캐시사용하지 않고 무조건 새로고침
		urlConnection.setReadTimeout(60000); 	// 접속 대기시간을 60초로 제한하였음
		// 20분마다 접속이 되는지 개발자가 확인하는 코드
		if (urlConnection != null && urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			logger.info("헤로쿠 앱이 활성화 상태입니다");
		}
		else {
			logger.info("헤로쿠 애빙 비활성화 상태입니다");
		}
	}
	
	@Override
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception {
		// 인터페이스에서 상속받은 메소드를 구현(아래)
		
		return memberDAO.selectMember(pageVO);
	}

	@Override
	public int countMember(PageVO pageVO) throws Exception {
		
		return memberDAO.countMember(pageVO);
	}

	@Override
	public void insertMember(MemberVO memberVO) throws Exception {
		memberDAO.insertMember(memberVO);
	}

	@Override
	public void deleteMember(String user_id) throws Exception {
		memberDAO.deleteMember(user_id);
	}

	@Override
	public MemberVO readMember(String user_id) throws Exception {
		return memberDAO.readMember(user_id);
	}

	@Override
	public void updateMember(MemberVO memberOne) throws Exception {
		memberDAO.updateMember(memberOne);
	}
	
	
	
}
