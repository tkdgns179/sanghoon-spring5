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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.edu.service.IF_BoardService;
import com.edu.service.IF_BoardTypeService;
import com.edu.vo.BoardTypeVO;
import com.edu.vo.BoardVO;
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
	@Inject
	private IF_BoardService boardService;
	
	// 나중에 게시물관리 기능 만들 떄 @Aspect로 AOP기능 추가할 예정  board_type을 항상 가져가도록 처리함
	// 세션 : Server - PC 구조상에서 클라이언트가 서버에 접속할 때 서버에 발생되는 정보를 세션이라고 함 -> 서버에 저장됨
	// 쿠키 : Server - PC 구조상에서 클라이언트가 서버에 접속할 때 서버에 발생되는 정보를 쿠키이라고 함 -> PC에 저장됨
	// 옛날에는 쿠키로 로그인 체크를 했음  -> 보안상 PC에 로그인정보가 저장되기 때문에 위험(인터넷 광고에만 사용) -> 그외는 세션으로만 사용합니다
 	// Aspect로 AOP를 구현할 때는 포인트 컷(Advice 참겨이 실행될 위치)이 필요합니다.
	// @Around = @Befor + @After : @Around(포인트 컷의 전과 후를 가르킴  ) 
	// @Around는 콜백함수 매개변수로 조인포인트객채(포인트컷에서 실행되는 메소드들)를 필수로 받습니다.
	// *(...)은 모든 메소드를 가르킴
	
	// 아래 조인포인트(중단점)은 board_delete, board_update* 메소드를 실행할 때, 본인이 작성한 글인지 확인하는 기능을 추가함
	// 단, ROLE_ADMIN 권한을 가지고 있는 사용자는 제외
	@Around("execution(* com.edu.controller.HomeController.board_delete(..)) || execution(* com.edu.controller.HomeController.board_update*(..))")
	public Object check_board_crud(ProceedingJoinPoint pjp) throws Throwable {
		// request 객체는 (이전페이지 URL || 세션 값)을 가져오기 위함
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		// 참조값 null로 초기화
		String user_id = null;
		BoardVO boardVO = null;
		logger.info("디버그 호출된 메서드 명은: "+pjp.getSignature().getName());
		
		for(Object object : pjp.getArgs()) {
			if(object instanceof BoardVO) {
				user_id = ((BoardVO) object).getWriter();
			}
			// 조인포인트 메소드의 매개변수가 object
			if(object instanceof Integer) {
				boardVO = boardService.readBoard((Integer) object);
				user_id = boardVO.getWriter();
			}
		}
		if (request != null) {
			HttpSession session = request.getSession();
			if (!user_id.equals(session.getAttribute("session_userid")) && "ROLE_USER".equals(session.getAttribute("session_levels"))) {
				// 메세지를 redirect로  .addFlashAttribute로 지정해서 보냈음
				// 그 대신 Flash라는 클래스를 사용해서 전달함
				FlashMap flashMap = new FlashMap();
                flashMap.put("msgError", "게시물은 본인글만 수정/삭제 가능합니다.");
                FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
                flashMapManager.saveOutputFlashMap(flashMap, request, null);

				return "redirect:" + request.getHeader("Referer");
			}
		}
		Object result = pjp.proceed();
		return result;
	}
	
	// 아래 조인포인트(중단점)은 검색어와 게시판타입의 값을 세션으로 유지시키는 기능
	@Around("execution(* com.edu.controller.*Controller.*(..))")
	public Object sessionManager(ProceedingJoinPoint pjp) throws Throwable {
		// board_type 변수값을 세션에 저장하려고 함 클라이언트 각자 발생
		// HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		// 일반적인 컨트롤러에서는 매개변수 HttpServletRequest를 사용가능함
		// 컨트롤러 클래스에서 매개변수로 받을 값 (board_type) < pageVO, boardVO
		PageVO pageVO = null;
		String board_type = null; // jsp에서 전송되는 값을 임시로 저장, 목적은 세션변수 발생조건으로 사용
		String search_keyword = null; // IE에서 한글검색시 에러발생 때문에 추가
		// 조인 포인트 리스트의 객체들의 메소드의 args(인자값들)를 뽑아냄.
		for(Object object : pjp.getArgs()) {
			if (object instanceof PageVO) { // AOP실행 메소드중 매개변수 PageVO pageVO객체 판단
				// 결과는 게시판, 멤버 서비스에 PageVO를 사용하는 서비스에만 적용됨 게시판생성관리에는 적용되지않음
				pageVO = (PageVO) object;
				board_type = pageVO.getBoard_type();
				search_keyword = pageVO.getSearch_keyword();
			}
		}
		if(request != null) { // jsp에서 Get, Post가 있을 때
			// 세션값을 발생시킴
			HttpSession session = request.getSession(); // PC(Chrome Browser)가 스프링 프로젝트에 접근시 세션객체
			// 검색폼이 있는 jsp에서 발생이 됨
			if (search_keyword != null) { //검색어가 발생하면 최초로 request안의 session에 변수를 넣어줍니다
				session.setAttribute("session_search_keyword", search_keyword);
			}
			if (session.getAttribute("session_search_keyword") != null) {
				search_keyword = (String) session.getAttribute("session_search_keyword");
				if (pageVO != null) { // get, set중에 set하는 pageVO가 null이면 에러발생하기 때문에 추가한 코드
					pageVO.setSearch_keyword(search_keyword);
				}
			}
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
		Object result = pjp.proceed(); // 여기서 조인포인트가 실행이 됩니다.
		return result;
	}
	
	// 이 메소드는 컨트롤러에서 Exception이 발생했을 때, 여기서 intercept(가로채기)해서 
	// 에러메세지를 개발자가 작성한 jsp화면에 뿌려주는 역할 -> prevPage, exception 변수 2개 전송
	// ModelAndView는 Model과 View기능을 합친 클래스임
	@ExceptionHandler(Exception.class)
	public ModelAndView errorModelAndView(Exception ex, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		// 이전페이지로 돌아가기용 데이터 생성
		String referer = request.getHeader("Referer"); // 크롬>네트워크>파일>Referer>이전페이지 URL이 존재
		request.getSession().setAttribute("session_prevPage", referer);
		
		// 컨트롤러에서 받은 Exception 객체를 ModelAndView로 전달 
		modelAndView.addObject("exception", ex);
		modelAndView.setViewName("home/error/error_spring");
		return modelAndView;
	}
	
	// 아래 메소드는 컨트롤러의 메소드 실행 전에 값을 생성해서 model객체에 담아서 jsp로 자료를 전송
	// 위 @ControllerAdvice를 이용해서 컨트롤러의 모든 메소드가 실행되기전에 호출만 되면 아래 메소드가 자동실행됨(콜백함수)
	@ModelAttribute("listBoardTypeVO")
	public List<BoardTypeVO> listBoardTypeVO() throws Exception {
		
		return boardTypeService.selectBoardType();
	}
	
}
