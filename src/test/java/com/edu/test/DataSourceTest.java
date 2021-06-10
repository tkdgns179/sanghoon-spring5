package com.edu.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 오라클과 연동해서 CRUD를 테스트하는 클래스 입니다.
 * 과장(이사,팀장) JUnit CRUD까지 만들어서 일반사원에게 공개 (+a 회원관리)
 * @author 김상훈
 *
 */
// RunWith 인터페이스 : JUnit을 실행하는 클래스를 명시함
@RunWith(SpringJUnit4ClassRunner.class)
// 경로에서 **는 (모든 폴더를 명시) *(모든 파일명을 명시)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class DataSourceTest {
	// 디버그용 로그 객체생성
	
	private Logger logger = Logger.getLogger(DataSourceTest.class);
	// dataSource 객체는 데이터베이스 객체를 pool로 저장해서 사용할 때 DataSource객체 클래스를 사용합니다.(아래)
	
	@Inject // @Inject는 스프링에서 객체를 만드는 방법, 이전 자바에서는 new 키워드로 객체를 만들었음
	private DataSource dataSource; // 메모리관리를 스프링이 대신해 줌
	// @Inject는 자바8부터 지원 하였고, 이전 자바7에서는 @Autowired로 객체를 만들었습니다
	
	// 스프링 코딩 시작
	// M-V-C 사이에 데이터를 입출력하는 임시저장 공간(VO클래스-멤버변수+Get/Set method)를 생성  
	// 보통 ValueObject 클래스는 DB테이블과 1:1로 매칭이 됩니다.
	// 그래서, 다음 단계로 진행합니다 
	// 1. MemberVO.java VO클래스를 생성
	// 2. DB(mybatis)쿼리를 만듭니다. (VO 사용됨)
	
	@Inject // MemberService 서비스를 주입받아서 객체를 사용합니다
	private IF_MemberService memberService;
	
	@Test
	public void updateMember() throws Exception {
		// 이 메소드는 회원 정보수정(1개 레코드) .jsp에서 사용예정.
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail("admin@test.com");
		memberVO.setEnabled(true);
		memberVO.setLevels("ROLE_ADMIN");
		memberVO.setPoint(100);
		memberVO.setUser_name("최고관리자");
		memberVO.setUser_pw("");  // 입력하지 않으면, 업데이트에서 제외
		// 인코딩 객체
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		// 한 사이클 돌린 후, 스프링 시큐리티 로직을 적용 
		// 스프링5 시큐리티 암호화 적용 로직(아래)
		if ((memberVO.getUser_pw()).length() > 0 ) {
			String userPwEncoder = passwordEncoder.encode(memberVO.getUser_pw());
			memberVO.setUser_pw(userPwEncoder);
		}
		memberVO.setUser_id("admin"); // 수정 조회조건
		memberService.updateMember(memberVO);
		
		// ==========================여기 까지는 jsp에서 1명의 회원만 수정할 때 사용하는 로직=================================
		
		// ==========================이후 부터는 모든 회원 중의 시큐리티 암호화가 되지않은 사용자만 암호화 업데이트====================
		// 아래 수정 call호출을 회원 수만큼 반복을 해야 합니다.
		PageVO pageVO = new PageVO();
		
		pageVO.setPage(1);
		pageVO.setPerPageNum(10);
		pageVO.setQueryPerPageNum(1000);
		// MemberVO 타입을 가진 리스트형 객체 List<MemberVO>
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		// 향상된 for 반복문 사용 (memberOne : listMember) {구현 내용}
		for (MemberVO memberOne : listMember) { // listMember객체가 전부 비워질 때까지 반복을 함
			// memberOne 객체(1개의 레코드)의 암호를 뽑아서 시큐리티로 암호화 시킨 후 onePwEncoder 변수 입력
			// 혹시나 해시데이터로 변환된 값이 또 다시 해시함수로 인해 중복암호화 시킬 수 있으므로 제외조건을 추가함(아래)
			String rawPassword = memberOne.getUser_pw();
			if (rawPassword.length() < 10) { // 원시 암호데이터 길이가 50보다 작을 때만 암호화로직 진입
				String onePwEncoder = passwordEncoder.encode(rawPassword);
				memberOne.setUser_pw(onePwEncoder);
				memberService.updateMember(memberOne); 
			}
		}
		// memberService.updateMember(memberVO);  1명(admin만) 수정 -> 모든회원을 업데이트
		selectMember();
	}
	
	@Test
	public void readMember() throws Exception {
		// 이 메소드는 회원 상세보기 jsp에 사용될  예정
		MemberVO memberVO = new MemberVO();
		// 100명 중, 1명을 보려면 PK(= user_id)로 조회해야함 
		 String user_id = "admin";
		// memberVO.setUser_id("admin"); -> memberVO = memberService.readMember(user_id);
		memberVO = memberService.readMember(user_id);
	}
	
	@Test
	public void deleteMember() throws Exception {
		memberService.deleteMember("user_del");
		selectMember();
	}
	
	@Test
	public void insertMember() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setUser_id("user_del");
		memberVO.setUser_pw("1234"); // 스프링 시큐리티 중 512비트 암호화로 처리예정
		memberVO.setUser_name("삭제할 사용자");
		memberVO.setEmail("user@test.com");
		memberVO.setPoint(10);
		memberVO.setEnabled(true);
		memberVO.setLevels("ROLE_USER");
		memberService.insertMember(memberVO);
		selectMember();
	}
	
	@Test
	public void selectMember() throws Exception {
		// 회원관리 테이블에서 더미로 입력한 100개의 레코드를 출력하는 메소드 테스트 ->회원관리목록이 출력
		// 검색기능, 페이징기능 여기서 구현.
		// 현재 몇페이지, 검색어 임시저장 공간 -> DB에 페이징 조건문, 검색 조건문
		// 변수를 2~3개 이상은 바로 String 변수로 처리하지않고, VO 만들어서 사용
		// PageVO.java 클래스를 만들어서 페이징처리변수와 검색어변수 선언, Get/Set 생성
		// PageVO 만들기전 SQL쿼리로 실제 구현해 보면서, 필요한 변수 만들어야합니다.
		// pageVO 객체를 만들어서 가상으로 초기값을 입력합니다
		PageVO pageVO = new PageVO();
		int totalCount = memberService.countMember(pageVO);
		logger.info(totalCount+"------------------------------------");
		pageVO.setPage(1);
		pageVO.setPerPageNum(10);
		pageVO.setQueryPerPageNum(1000);
//		pageVO.setTotalCount(memberService.countMember());
		
//		pageVO.setSearch_type("user_id"); // 검색타입 all, user_id, user_name
//		pageVO.setSearch_keyword("admin");
		
		logger.info("pageVO 저장된 값 확인 "+pageVO.toString());
		// 위 setTotalCount()에서 calPage()함수를 실행시키는데 calcPage()에서 3가지 변수가 설정되어야함 *NullPointError
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		listMember.toString();
				
	}
	
	
	@Test
	public void oldQueryTest() throws Exception { 
		// 스프링빈을 사용하지 않을 때 예전 방식: 코딩테스트에서는 스프링 설정을 안쓰고, 직접 DB 아이디/암호 입력
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","XE","apmsetup");
		logger.debug("데이터베이스 접속이 성공하였습니다 DB종류는 "+connection.getMetaData().getDatabaseProductName());
		// 직접쿼리를 날릴빈다. 날리기전 쿼리문장 객체생성 statement // java.sql Statement 임포트
		Statement stmt = connection.createStatement();
		// 위처럼 쿼리문장 객체를 만드는 이유 => 보안 때문에(sql injection attack)
		
		//INSERT 쿼리문장을 만듭니다.(아래)
		//예전 방식으로 더미데이터(샘플데이터)를 100개 입력합니다.
		/*
		 * for(int cnt=0; cnt<100; cnt++) { // * deptno NUMBER(2)로 설정되어 3자리를 넘어가면 오류가 생김
		 * stmt.executeQuery("insert into dept02 values("+cnt+", '디자인부', '경기도')"); }
		 * 
		 */
		//INSERT UPDATE DELETE시 sql developer에서는 commit이 필수지만, 외부java 클래스 insert할 때는 자동커밋이 됩니다.
		//stmt.executeQuery("insert into dept02 values(20, '디자인부', '경기도')");
		
		
		// 테이블에 입력되어 있는 레코드를 select 쿼리 stmt문자으로 가져옴(아래)
		ResultSet rs = stmt.executeQuery("select * from dept order by deptno");
		// 위에서 저장된 rs 객체를 반복문으로 출력(아래)
		while(rs.next()) {// rs의 객체의 다음이 있는가-> true 없으면 -> false
			logger.debug(rs.getString("deptno")+" "+rs.getString("dname")+" "+rs.getString("loc"));
		}
		stmt = null; // 메모리 반환
		rs = null; 
		connection = null; // 메모리 초기화
	}
	
	@Test
	public void dbConnectionTest() {
		// 데이터베이스 커넥션 테스트: 설정은 root-context의 bean(spring class)를 이용
		try {
			Connection connection = dataSource.getConnection();
			logger.debug("데이터베이스 접속이 성공하였습니다 DB종류는 "+connection.getMetaData().getDatabaseProductName());
		} catch (SQLException e) {
			logger.debug("데이터베이스 접속이 실패하였습니다");
//			e.printStackTrace();
		}
	}
	@Test
	public void junitTest() {
		// *logger의 장점 : 조건에 따라서 출력을 조정할 수 있음
		// error debug info (상황별 메소드 출력가능)
		logger.debug("Junit 테스트 시작입니다.");
	}
}
