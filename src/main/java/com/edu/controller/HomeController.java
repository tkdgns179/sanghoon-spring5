package com.edu.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//외부 라이브러리(모듈) 사용 = import
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.service.BoardServiceImpl;
import com.edu.service.IF_BoardService;
import com.edu.service.IF_MemberService;
import com.edu.vo.BoardVO;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 MVC웹프로젝트를 최초로 생성시 자동으로 생성되는 클래스
 * 웹브라우저의 요청사항을 view단(jsp)에 연결시켜주는 클래스 @Controller.
 * 스프링에서 관리하는 클래스를 스프링빈(콩) = 스프링빈을 명시 @Controller 애노테이션
 * Beans(콩들) 그래프로 이 프로젝트의 규모를 확인가능.
 * 스프링이 관리하는 클래스=스프링빈은 파일아이콘에 S가 붙습니다. 
 */

@Controller
public class HomeController {
	//스프링빈(클래스) 에서는 로거로 디버그를 합니다.=로거객체를 만듭니다.
	// 로그중 slf4j(Spring Log For Java)
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	IF_MemberService memberService;
	@Autowired
	IF_BoardService boardService;
	
	/**
	 * 사용자요청(웹브라우저)을 받아서=@RequestMapping인테페이스를 사용해서 메서드명을 스프링이 구현합니다.
	 *  ,router(루트rootX)
	 * return 값으로 view(jsp)를 선택해서 작업한 결과를 변수로 담아서 화면에 전송 후 결과를 표시(렌더링) 합니다.
	 * 폼(자료)전송시 post(자료숨김), get(자료노출-URL쿼리스트링?있는자료전송)
	 */
	// 이제부터 일반적인 개발방식 VO -> 쿼리 -> DAO ->Service (관리자단에서 여기까지 끝) 
	// 관리자단에서 작성한 Service 사용자단에서 그대로 이용,  컨트롤러부터 분리해 작업 -> jsp
	
	@RequestMapping(value = "/home/board/board_list", method = RequestMethod.GET)
	public String board_list(@ModelAttribute("pageVO")PageVO pageVO, Model model) throws Exception {
		if(pageVO.getPage() == null ) {
			pageVO.setPage(1);
		}
		// pageVO의 2개의 변수값을 필수로 설정해야함
		pageVO.setQueryPerPageNum(5);
		pageVO.setPerPageNum(5);
		int totalCount = boardService.countBoard(pageVO);
		pageVO.setTotalCount(totalCount); // 여기에서 startPage, endPage, prev, next 변수값이 발생함
		logger.info(pageVO.toString());
		List<BoardVO> boardList = boardService.selectBoard(pageVO);
		model.addAttribute("boardList", boardList);
		return "home/board/board_list";
	}
	
	// 404 File Not Found 에러 처리하는 GET 호출 추가
	@RequestMapping(value = "/home/error/error_404", method = RequestMethod.GET)
	public String error_404(HttpServletRequest request, Model model) {
		model.addAttribute("prevPage", request.getHeader("Referer"));
		return "/home/error/error_404";
	}
	
	// 회원가입 폼 호출 POST방식
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(MemberVO memberVO, RedirectAttributes rdat) throws Exception {
		// jsp폼에서 levels를 ROLE_ADMIN으로 해킹할까봐 여기서 강제로 입력합니다
		BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
		memberVO.setUser_pw(passwordEncode.encode(memberVO.getUser_pw()));
		memberService.insertMember(memberVO);
		rdat.addFlashAttribute("msg", "회원가입"); //회원가입 이(가) 성공했습니다.
		return "redirect:/login_form";
	}
	
	
	// 회원가입 폼 호출 Get방식
	@RequestMapping(value = "/join_form", method = RequestMethod.GET)
	public String join_form() throws Exception {
		
		return "home/join";
	}
	
	// 마이페이지에서 회원탈퇴 POST방식 처리만
	@RequestMapping(value = "/member/mypage_leave", method = RequestMethod.POST)
	public String mypage_leave(MemberVO memberVO,RedirectAttributes rdat) throws Exception {
 		memberService.updateMember(memberVO);
		// rdat.addFlashAttribute("msg", "회원탈퇴"); // logout으로 넘어가기 때문에 실행되지못함
		return "redirect:/logout";
	}
	
	// 마이페이지 회원정보수정 POST방식 처리 후 msg를 hidden값으로 jsp로 전송합니다
	@RequestMapping(value = "/member/mypage", method = RequestMethod.POST)
	public String mypage(MemberVO memberVO,RedirectAttributes rdat) throws Exception {
		// 암호를 인코딩 처리합니다. 단, 암호를 변경하는 값이 있을 때
		if (!memberVO.getUser_pw().isEmpty()) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String rawPassword = memberVO.getUser_pw();
			memberVO.setUser_pw(passwordEncoder.encode(rawPassword)); 
		}
		
 		memberService.updateMember(memberVO);
		rdat.addFlashAttribute("msg", "회원정보수정"); // 회원정보수정성공 alert이 팝업됨
		return "redirect:/member/mypage_form";
	}
	
	// 마이페이지 폼 호출 GET방식, (= 회원수정폼) model에 담아서 변수값 전송이 필요
	@RequestMapping(value = "/member/mypage_form", method = RequestMethod.GET)
	public String mypage_form(HttpServletRequest request, Model model) throws Exception {
		// 로그인한 사용자 세션을 session_userid를 가지고 IF_MemberService 인터페이스의 readMember를 호출
		// jsp에서 발생된 세션을 가져오려고 하기 때문에 HttpServletRequest 인터페이스 객체를 가져옵니다
		HttpSession session = request.getSession(); // 싱글톤 객체입니다
		String user_id = (String) session.getAttribute("session_userid");
		model.addAttribute("memberVO", memberService.readMember(user_id));
		return "home/member/mypage";
	}
	
	// 사용자단 로그인 폼 호출 GET, 로그인 POST처리는 컨트롤러에서 하지 않고 Spring Security로 처리
	@RequestMapping(value = "/login_form", method = RequestMethod.GET)
	public String login_form() throws Exception {
		
		return "home/login";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage(Model model) { //콜백메스드,자동실행됨.
		String jspVar = "@서비스(DB)에서 처리한 결과";
		model.addAttribute("jspObject", jspVar);
		logger.info("디버그 스프링로고사용: " + jspVar);//System.out 대신 logger 객체를 사용
		//home.jsp파일로 자료를 전송(스프링)하는 기능= model인터페이스 객체(스프링이처리)에 내용만 채우면됨
		return "home/index";//확장자가 생략 .jsp가 생략되어 있음.
	}
	
}
