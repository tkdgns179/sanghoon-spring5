package com.edu.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 Admin관리자단을 접근하는 클래스
 * 변수 Object를 만들어서 jsp로 전송 <-> jsp 폼값을 받아서 Object로 처리
 * @author 김일국
 *
 */
@Controller
public class AdminController {
	
	// 디버그용 로그객체 생성(아래0
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	// 이 메소드는 회원목록을 출력하는 jsp와 매핑이 됩니다.
	@Inject
	private IF_MemberService memberService;
	
	// 아래경로는 회원신규등록 폼을 호출하는 URL
	@RequestMapping(value="/admin/member/member_insert_form", method = RequestMethod.GET)
	public String insertMemberForm(@ModelAttribute("pageVO") PageVO pageVO) throws Exception {
		
		return "admin/member/member_insert";
	}
	
	// 아래경로는 회원신규등록 처리하는 서비스호출 URL
	@RequestMapping(value="/admin/member/member_insert", method=RequestMethod.POST)
	public String insertMember(PageVO pageVo, MemberVO memberVO) throws Exception {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = memberVO.getUser_pw();
		String encPassword = passwordEncoder.encode(rawPassword);
		memberVO.setUser_pw(encPassword);
		memberService.insertMember(memberVO);
		return "redirect:/admin/member/member_list";
	}
	//아래 경로는 수정처리를 호출=DB를 변경처리함.
	@RequestMapping(value="/admin/member/member_update", method = RequestMethod.POST)
	public String updateMember(MemberVO memberVO, PageVO pageVO) throws Exception {
		//update 서비스만 처리하면 끝
		//업데이트 쿼리서비스 호출하기 전 스프링시큐리티 암호화 적용합니다.
		String rawPassword = memberVO.getUser_pw();
		if(!rawPassword.isEmpty()) {//수정폼에서 암호 입력값이 비어있지 않을때만 아래로직실행.
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encPassword = passwordEncoder.encode(rawPassword);
			memberVO.setUser_pw(encPassword);
		}
		memberService.updateMember(memberVO);//반환값이 없습니다.
		//redirect로 페이지를 이동하면, model로 담아서 보낼수 없습니다. 쿼리스트링(URL?)으로 보냅니다.
		String queryString = "user_id="+memberVO.getUser_id()+"&page="+pageVO.getPage()+"&search_type="+pageVO.getSearch_type()+"&search_keyword="+pageVO.getSearch_keyword();
		return "redirect:/admin/member/member_update_form?"+queryString;
	}
	
	
	//아래 경로는 수정폼을 호출=화면에 출력만=렌더링만 
	@RequestMapping(value="/admin/member/member_update_form", method=RequestMethod.GET)
	public String updateMemberForm(MemberVO memberVO, Model model,@ModelAttribute("pageVO")PageVO pageVO) throws Exception {
		//이 메서드는 수정폼에 pageVO, memberVO 2개의 데이터객체를 jsp로 보냅니다.
		//사용자1명의 레코드를 가져오는 멤버서비스(쿼리)를 실행(아래)
		MemberVO memberView = memberService.readMember(memberVO.getUser_id());
		//사용자1명의 레코드를 model에 담아서 + @ModelAttribute에 담아서 jsp로 보냅니다.
		model.addAttribute("memberVO", memberView);
		return "admin/member/member_update";//상대경로
	}
	
	
	@RequestMapping(value="/admin/member/member_delete", method=RequestMethod.POST)
	public String deleteMember(MemberVO memberVO) throws Exception {
		logger.info("디버그: " + memberVO.toString());
		//MemberVO memberVO는 클래스형 변수: String user_id 스트링형 변수 같은 방식.
		String user_id = memberVO.getUser_id();
		//이 메서드는 회원상세보기페이지에서 삭제버튼을 클릭시 전송받은 memberVO값을 이용해서 삭제를 구현(아래)
		memberService.deleteMember(user_id);//삭제쿼리가 실행됨
		//return "admin/member/member_list";//삭제후 이동할 jsp경로지정
		//위 방식대로하면, 새로고침하면, /admin/member/member_delete 계속 실행됩니다.-사용자단에서 실습
		//게시판테러상황을 방지하기 위해서, 쿼리를 작업 후 이동할때는 redirect(다시접속)라는 명령을 사용합니다.
		return "redirect:/admin/member/member_list";//단,redirect는 절대경로를 사용.
		}
	
	@RequestMapping(value="/admin/member/member_view", method = RequestMethod.GET)
	public String viewMemberForm(Model model, @RequestParam("user_id")String user_id, @ModelAttribute("pageVO")PageVO pageVO) throws Exception {
		/*
		 * ?user_id="~" 안의 내용이 HttpRequest로 전달되어 값이 담김
		 * 이 메소드는 리스트 페이지에서 상세보기로 이동할 때, 1개의 레코드 값을 보여주는 구현내용이 들어값니다
		 * JUnit에서 테스트헀던 readMember 방식을 이용합니다.
		 * 다른점은 JUnit에서 식별자 ID를 강제로 지정했지만, 이 메소드에서는 @RequestParam 인터페이스를 이용해서 식별자 값을 받음
		 */
		model.addAttribute("memberVO", memberService.readMember(user_id));
		return "admin/member/member_view";
	}
	
	
	@RequestMapping(value="/admin/member/member_list", method =  RequestMethod.GET)
	public String selectMember(@ModelAttribute("pageVO")PageVO pageVO, Model model) throws Exception { // @ModelAttribute("pageVO") PageVo pageVO로 가능하기도 함
		// jsp의 검색버튼 클릭시 search_type, search_keyword 내용이 PageVO클래스에 Set됩니다
		// 위에서 검색어를 받고  역방향 검색한 결과를 만들어서 jsp 보내줍니다
		
		// 이 메소드는 두 가지 객체를 생성해서 jsp로 보내주어야 합니다.
		// 1. List<MemberVO> 타입의 객체를 생성해서 Model model이용하여 jsp로 보내줍니다
		// 2. PageVO pageVO (prev, next, startPage, endPage...를 받아서 사용함) 객체를 생성해서 Model model이용하여 jsp로 보내줍니다
		// 2번 객체부터 로직이 필요합니다 -> memberList구하는 쿼리변수가 pageVO로 부터 생성되기 때문에
		
		// 필수값 설정 (어떤값이 설정되어야할지 변수설정이 중요함)
		if (pageVO.getPage() == null) { //jsp에서 전송값이 없을 때만 초기값 입력
			pageVO.setPage(1); //default값
		}
		pageVO.setQueryPerPageNum(5); // memberList 객체에 필요 endPage 구할 때 필요
		pageVO.setPerPageNum(5); // 하단 UI에 보여줄 페이지번호 개수 // startPage
		// perPageNum이면 초기에 200개의 데이터를 가져와서 next가 false
		// perPageNum이면 초기에 50개의 데이터를 져와서 next가 true
		
		// queryPerPageNum, perPageNum 변수를 통해서 totalCount안의 calcPage() 메소드가 실행되며 변수값이 설정 됨
		pageVO.setTotalCount(memberService.countMember(pageVO));
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		// pageVO.setTotalCount(listMember.size()); // 검색된 검색결과의 전체 카운트값 (단, 페이징 관련없는 개수)
		logger.info("디버그" + pageVO.toString()); 
		
		model.addAttribute("listMember", listMember);
//		model.addAttribute("pageVO", pageVO); // 나중에 @ModelAttribute로 사용할 예정
		
		return "admin/member/member_list"; // jsp파일 상대경로가 들어갑니다
	}
	
	//URL요청 경로는 @RequestMapping 반드시 절대경로로 표시
	@RequestMapping(value="/admin", method = RequestMethod.GET)
	public String admin(Model model) throws Exception { // 에러발생시 Exception 클래스의 정보를 스프링으로 보내게 됩니다. 
		
		//아래 상대경로에서 /WEB-INF/views/폴더가 루트(생략prefix접두어) 입니다.
		//아래 상대경로 home.jsp에서 .jsp (생략suffix접미어) 입니다.
		return "admin/home";//리턴 경로=접근경로는 반드시 상대경로로 표시
	}
}
