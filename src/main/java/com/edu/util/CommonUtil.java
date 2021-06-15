package com.edu.util;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;

/**
 * 이 클래스는 이 프로젝트에서 공통으로 사용하는 유틸리티 기능을 모아놓은 클래스입니다
 * @author 김상훈
 * 컨트롤러 기능 @Controller (jsp와 바인딩이 필요할 때 필수 어노테이션입니다)
 * 컴포넌트는 @Component는 MVC가 아닌 기능들을 모아놓은 것을 스프링빈으로 명시, 여기서 jsp와 바인딩이 필요하여 @Controller를 사용함
 */
@Controller
public class CommonUtil {
	// 멤버변수 생성
	private Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	@Inject
	private IF_MemberService memberService;
	
	// RestAPI서버 맛보기 (제대로 만들려면 @RestController 사용함)
	@RequestMapping(value="/id_check", method = RequestMethod.GET)
	@ResponseBody // 반환받은 값의 헤더값을 제외하고, 내용(body)만 반환하겠다는 것을 명시
	public String id_check(@RequestParam("user_id")String user_id) throws Exception {
		// 중복아이디를 체크하는 로직(아래)
		String memberCnt = "1";
		
		if (!user_id.isEmpty()) {
			MemberVO memberVO = memberService.readMember(user_id);
			logger.info("디버그!! " + memberVO); // 공백을 전송해도 null이기 때문에 조건이 1개 필요
			if(memberVO == null) { // 중복아이디가 존재하지 않으면 {}안을 실행 // !는 논리부정기호
				memberCnt = "0";
			}
		}
		return memberCnt; // 0.jsp로 가는게 아니라 이유는 @ResponseBody 때문이고, RestAPI는 값만 반환
	}
}
