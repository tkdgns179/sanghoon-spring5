package com.edu.aop;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.edu.service.IF_BoardTypeService;
import com.edu.vo.BoardTypeVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 AOP기능 중 @Aspect & @ControllerAdvice으로 구현이 됩니다
 * @author 김상훈
 *
 */


@Component
@Aspect
@ControllerAdvice
public class AspectAdvice {
	private Logger logger = LoggerFactory.getLogger(AspectAdvice.class);
	@Inject
	private IF_BoardTypeService boardTypeService;
	
	// 나중에 게시물관리 기능 만들 떄 @Aspect로 AOP기능 추가할 예정  board_type을 항상 가져가도록 처리함
	// 세션 : Server - PC 구조상에서 클라이언트가 서버에 접속할 때 서버에 발생되는 정보를 세션이라고 함 -> 서버에 저장됨
	// 쿠키 : Server - PC 구조상에서 클라이언트가 서버에 접속할 때 서버에 발생되는 정보를 쿠키이라고 함 -> PC에 저장됨
	// 옛날에는 쿠키로 로그인 체크를 했음  -> 보안상 PC에 로그인정보가 저장되기 때문에 위험(인터넷 광고에만 사용) -> 그외는 세션으로만 사용합니다
 	// Aspect로 AOP를 구현할 때는 포인트 컷(Advice 참겨이 실행될 위치)이 필요합니다.
	// @Around = @Befor + @After : @Around(포인트 컷의 전과 후를 가르킴  ) 
	// @Around는 콜백함수 매개변수로 조인포인트객채(포인트컷에서 실행되는 메소드들)를 필수로 받습니다.
	// *(...)은 모든 메소드를 가르킴
	@Around("execution(* com.edu.controller.*Controller.*(..))")
	public Object sessionManager(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("무조건");
		// board_type 변수값을 세션에 저장하려고 함 클라이언트 각자 발생
		// HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		// 일반적인 컨트롤러에서는 매개변수 HttpServletRequest를 사용가능함
		// 컨트롤러 클래스에서 매개변수로 받을 값 (board_type) < pageVO, boardVO
		PageVO pageVO = null;
		String board_type = null; // jsp에서 전송되는 값을 임시로 저장, 목적은 세션변수 발생조건으로 사용
		// 조인 포인트 리스트의 객체들의 메소드의 args(인자값들)를 뽑아냄.
		for(Object object : pjp.getArgs()) {
			if (object instanceof PageVO) {
				pageVO = (PageVO) object;
				board_type = pageVO.getBoard_type();
			}
		}
		if(request != null) { // jsp에서 Get, Post가 있을 때
			// 세션값을 발생시킴
			HttpSession session = request.getSession(); // PC(Chrome Browser)가 스프링 프로젝트에 접근시 세션객체
			if (board_type != null) { // 최초로 세션변수가 발생
				session.setAttribute("session_board_type", board_type);
			}
			if (session.getAttribute("session_board_type") != null) {
				board_type = (String) session.getAttribute("session_board_type");
				if (pageVO != null) { // Set은 pageVO가 null이 아닐경우만 실행되도록 처리
					pageVO.setBoard_type(board_type); // 검색 목표달성 여기서 항상 값을 가져가도록 구현됩니다
				}
			}
		}
		// Aspect > 포인트 컷(Around) > 조인 포인트(메소드) > 매개변수로 구현한 결과를 리턴합니다
		Object result = pjp.proceed(); // 여기서 조인포인특 실행이 됩니다.
		return result;
	}
	
	// 아래 메소드는 컨트롤러의 메소드 실행 전에 값을 생성해서 model객체에 담아서 jsp로 자료를 전송
	// 위 @ControllerAdvice를 이용해서 컨트롤러의 모든 메소드가 실행되기전에 호출만 되면 아래 메소드가 자동실행됨(콜백함수)
	@ModelAttribute("listBoardTypeVO")
	public List<BoardTypeVO> listBoardTypeVO() throws Exception {
		
		return boardTypeService.selectBoardType();
	}
	
}
