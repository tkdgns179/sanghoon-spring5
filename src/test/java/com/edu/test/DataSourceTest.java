package com.edu.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
	DataSource dataSource; // 메모리관리를 스프링이 대신해 줌
	// @Inject는 자바8부터 지원 하였고, 이전 자바7에서는 @Autowired로 객체를 만들었습니다
	
	// 스프링 코딩 시작
	// M-V-C 사이에 데이터를 입출력하는 임시저장 공간(VO클래스-멤버변수+Get/Set method)를 생성  
	// 보통 ValueObject 클래스는 DB테이블과 1:1로 매칭이 됩니다.
	// 그래서, 다음 단계로 진행합니다 
	// 1. MemberVO.java VO클래스를 생성
	// 2. DB(mybatis)쿼리를 만듭니다. (VO 사용됨)
	
	@Test
	public void selectMember() throws Exception {
		// 회원관리 테이블에서 더미로 입력한 100개의 레코드를 출력하는 메소드 테스트 ->회원관리목록이 출력
		// 스프링 코딩 순서
	}
	
	
	@Test
	public void oldQueryTest() throws Exception { 
		// 스프링빈을 사용하지 않을 때 예전 방식: 코딩테스트에서는 스프링 설정을 안쓰고, 직접 DB 아이디/암호 입력
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","XE2","apmsetup");
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
