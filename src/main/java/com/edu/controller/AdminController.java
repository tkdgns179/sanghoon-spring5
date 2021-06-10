package com.edu.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value="/admin/member/member_list", method =  RequestMethod.GET)
	public String selectMember(PageVO pageVO) throws Exception {
		// jsp의 검색버튼 클릭시 search_type, search_keyword 내용이 PageVO클래스에 Set됩니다
		// 위에서 검색어를 받고  역방향 검색한 결과를 만들어서 jsp 보내줍니다
		
		// 필수값 설정
		if (pageVO.getPage() == null) { //jsp에서 전송값이 없을 때만 초기값 입력
			pageVO.setPage(1);
		}
		pageVO.setQueryPerPageNum(10);
		pageVO.setPerPageNum(10); // 하단 UI에 보여줄 페이지번호 개수
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		// pageVO.setTotalCount(listMember.size()); // 검색된 검색결과의 전체 카운트값 (단, 페이징 관련없는 개수)
		pageVO.setTotalCount(memberService.countMember(pageVO));
		logger.info("디버그" + pageVO.toString()); 
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
