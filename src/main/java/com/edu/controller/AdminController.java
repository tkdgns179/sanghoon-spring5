package com.edu.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
