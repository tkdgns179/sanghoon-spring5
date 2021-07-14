package com.edu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.service.IF_MemberService;
import com.edu.util.NaverLoginController;
import com.edu.vo.MemberVO;
import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * 이 클래스는 스프링 시큐리티의 /login 처리한 결과를 받아서 
 * /login_success를 처리하는 컨트롤러 클래스입니다
 * @author 김상훈
 *
 */
@Controller
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Inject
	private IF_MemberService memberService;
	
	@Inject
	private NaverLoginController naverLoginController;
	
	// 네이버 인증체크 후 이동할 URL = 콜백 URL 처리 -> URL로 네이버에서 매개변수를 줍니다 -> 쿼리스트링을 @RequestParam으로 받음
	// @RequestMapping(value = "/naver_callback") GET/POST 둘 다 가능
	@RequestMapping(value = "/naver_callback", method = {RequestMethod.GET, RequestMethod.POST})
	public String naver_callback(@RequestParam(required=false)String code, @RequestParam String state, HttpSession session,Model model, RedirectAttributes rdat) throws ParseException, IOException {
		// 네아로에서 로그인 취소 버튼을 눌렀을 때 처리하는 로직
		if (code == null) {
			rdat.addFlashAttribute("msgError", "네이버 인증처리를 취소했습니다");
			return "redirect:/login_form";
		}
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginController.getAccessToken(session, code, state);
		// 위 인증에 사용된 토큰은 네이버에 제공된 프로필정보를 가져올 때 토큰이 필요함
		String profile = naverLoginController.getUserProfile(oauthToken);
		// 위 스트링형 profile 정보를 json데이터로 파싱합니다 (key : value 형태로 만듦)
		// logger.info("디버그!! + profile.toString()); -> 문제가 생겨 디버그
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(profile);	 	// json 데이터로 파싱
		
		JSONObject jsonObj = (JSONObject) obj; 	// json 오브젝트 형변환 // 반환코드, 메세지변수값, response값
		JSONObject response_obj = (JSONObject) jsonObj.get("response"); // 프로파일
		String status = (String) jsonObj.get("message"); // 인증성공여부 확인 변수값
		// 위 최종적으로 출력된 response_obj를 파싱시작 (아래)
		String username = (String) response_obj.get("name");
		String useremail = (String) response_obj.get("email");
		
		// logger.info(status);
		if(status.equals("success")) {
			// 인증성공 이후, 스프링 시큐리티 ROLE_USER 권한을 받아야지만,
			// insert, member, update, delete URL에 접근이 가능
			// 여기서부터 코드가 네이버 RestAPI와 관계가 없는 코드가 진행이 됨
			
			// 스프링 시큐리티 권한을 강제로 만듭니다.
			List<SimpleGrantedAuthority> authorities = new ArrayList(); // 스프링 시큐리티 로직을 강제로 만듭니다
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			// 스프링 시큐리티 인증도 추가
			Authentication authentication = new UsernamePasswordAuthenticationToken(useremail, null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication); 
			// 위에서 발생한 인증토큰을 시큐리티클래스에 저장
			// 스프링 시큐리티 인증도 강제로 추가
			session.setAttribute("session_enabled", true);
			session.setAttribute("session_userid", useremail);
			session.setAttribute("session_levels", "ROLE_USER");
			session.setAttribute("session_username", username);
			session.setAttribute("session_login_type", "sns"); // 마이페이지 안보이게 처리용
			
			rdat.addFlashAttribute("msg", "네이버 아이디 로그인");
			// 출력결과 : 네이버아이디로그인이(가) 성공하였습니다
		}
		else {
			rdat.addFlashAttribute("msgError", "네이버 인증이 실패했습니다 다시 시도해주세요");
			return "redirect:/login_form";
		}
		return "redirect:/";
	}
	
	//HomeController에 있던 /login_form을 네아로 로그인URL 생성때문에 여기로 이동.
	@RequestMapping(value="/login_form",method=RequestMethod.GET)
	public String login_form(Model model,HttpSession session) throws Exception {
		//네이버 인증 Url구하기:세션은 서버에 클라이언트접속정보를 저장하는 공간이 세션입니다. 
		String naverAuthUrl = "";
		naverAuthUrl = naverLoginController.getAuthorizationUrl(session);
		model.addAttribute("url", naverAuthUrl);
		return "home/login";//.jsp생략
	}
	
	// ID, 암호 비교쿼리가 실행된 결과가 됩니다
	@RequestMapping(value = "/login_success", method = RequestMethod.GET)
	public String login_success(HttpServletRequest request, RedirectAttributes rdat) throws Exception {
		// request 목적 : session 객체를 만들기위해서(로그인 정보를 화면이 이동하더라도 유지하기위해서)
		// rdat의 목적: model객체로 값을 전송할 수 없는 상황일 때, 값을 jsp로 전송하기 위해서
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 싱글톤 객체를 만들거나, 사용하는 목적: 메모리관리를 위해서 1개만 객체로 만들어서 사용하기 위해서
		String userid = "";	// 사용자ID
		String levels = ""; 	// 권한이 들어갈 변수
		Boolean enabled = false;// 로그인 체크 (true = 아이디암호비교성공/ false = 이하실패)
		// principal 객체는 UserDetails 객체가 포함되어 있고, enabled라는 인증결과가 발생합니다.
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) { // 위 결과 principal 객체의 인스턴스가 UserDtails라면
			enabled = ((UserDetails) principal).isEnabled(); // true, false 반환
			// 인증결과로 로그인 체크를 합니다
		}
		// 로그인 인증이 true라면 실행 ↓ (세션값 - 로그인아이디, 권한, 회원이름 저장하는 목적)
		if (enabled) {
			HttpSession session = request.getSession();
			// 자바8이상에서 지원되는 람다식 사용 ↓
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			// authorities 회원에 등록되어있는 값이 들어옴
			// 우리 DB에서는 levels가 1개 필드라서 여러개 권한이 있을 수 없습니다.
			// 규모가 있는 DB에서는 tbl_member <- tbl_levels 테이블을 만들어서 여러개의 권한을 줍니다
			if (authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ANONYMOUS")).findAny().isPresent() ) {
				levels = "ROLE_ANONYMOUS";
			}
			if (authorities.stream().filter(o -> o.getAuthority().equals("ROLE_USER")).findAny().isPresent() ) {
				levels = "ROLE_USER";
			}
			if (authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent() ) {
				levels = "ROLE_ADMIN";
			}
			userid = ((UserDetails) principal).getUsername();
			// 위에서 구한 변수 3개 enabled, levels, userid를 세션으로 저장합니다
			session.setAttribute("session_enabled", enabled);	// 로그인 여부확인	
			session.setAttribute("session_levels", levels);		// 로그인한 회원의 권한
			session.setAttribute("session_userid", userid);		// 로그인한 아이디를 출력
			MemberVO memberVO = memberService.readMember(userid);
			session.setAttribute("session_username", memberVO.getUser_name());
		}
		rdat.addFlashAttribute("msg", "로그인"); // 로그인 성공여부를 jsp페이지로 보내주는 변수생성
		return "redirect:/";
	}
}
