package com.edu.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.edu.dao.IF_BoardDAO;
import com.edu.service.BoardServiceImpl;
import com.edu.service.IF_BoardService;
import com.edu.service.IF_BoardTypeService;
import com.edu.service.IF_MemberService;
import com.edu.util.CommonUtil;
import com.edu.vo.AttachVO;
import com.edu.vo.BoardTypeVO;
import com.edu.vo.BoardVO;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 Admin관리자단을 접근하는 클래스
 * 디스패처 서블렛 클래스는 톰캣(web.xml)이 실행될 때 제일 먼저 실행되는 클래스, 그래서, 게이트웨이라고 합니다.
 * 디스페처 서블릿 실행될 때 , 컨트롤러의 Request 매핑경로르 다 등록합니다.
 * 변수 Object를 만들어서 jsp로 전송 <-> jsp 폼값을 받아서 Object로 처리
 * @author 김일국
 */
@Controller
public class AdminController {
	
	// 디버그용 로그객체 생성(아래0
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	// 이 메소드는 회원목록을 출력하는 jsp와 매핑이 됩니다.
	@Inject
	private IF_MemberService memberService;
	@Inject
	private IF_BoardTypeService boardTypeService;
	@Inject
	private IF_BoardService boardService;
	@Inject
	private IF_BoardDAO boardDAO;
	@Inject
	private CommonUtil commonUtil;
	
	@RequestMapping(value="/admin/board/board_insert", method = RequestMethod.POST)
	public String board_insert(@RequestParam("file")MultipartFile[] files, BoardVO boardVO) throws Exception {
		String[] save_file_names = new String[files.length];
		String[] real_file_names = new String[files.length];
		int index = 0;
		for (MultipartFile file: files) {
			if(file.getOriginalFilename() != "") {
				save_file_names[index] = commonUtil.fileUpload(file); 	// 물리적으로 파일을 저장후 리턴값으로 uuid이름을 스트링으로 리턴해줌
				real_file_names[index] = file.getOriginalFilename(); 	// UI용 파일이름
			}
			index++;
		}
		// 기존 신규등록 jsp폼에서 보낸 boardVO값 아래 file에 대한 임시변수값을 저장하는 로직
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		boardService.insertBoard(boardVO); // DB에 저장
		return "redirect:/admin/board/board_list"; // 새로고침 무한등록을 방지하기위해  redirect 사용
		// 게시판 신규등록시 자동으로 page=1로 이동하게 설정
	}
	
	// 게시물 등록 폼을 Get으로 호출합니다
	@RequestMapping(value="/admin/board/board_insert_form", method = RequestMethod.GET)
	public String board_insert_form(@ModelAttribute("pageVO")PageVO pageVO) throws Exception {
		if (pageVO.getPage() == null) {
			pageVO.setPage(1);
		}
		return "admin/board/board_insert";
	}
	
	// RedirectAttribute 클래스로 redirect: 시  jsp값을 보냅니다 (사용자단에서 입력/수정/삭제 성공시 redirect사용할 때 데이터를 보낼 때 사용)
	@RequestMapping(value="/admin/board/board_update", method = RequestMethod.POST)
	public String board_update(@RequestParam("file")MultipartFile[] files, BoardVO boardVO, PageVO pageVO) throws Exception { // redirect는 @ModelAttribute로 전송불가
		List<AttachVO> delFiles = boardService.readAttach(boardVO.getBno());
		// List 객체(2차원 배열)의 크기는 .size로 구함 // 1차원 배열은 length로 구함  
		String[] save_file_names = new String[files.length];
		String[] real_file_names = new String[files.length];
		int index = 0;
		for (MultipartFile file : files) { // files
			if (file.getOriginalFilename() != "") { // 전송된 첨부파일이 있다면 실행
				int sun = 0; // DB 테이블에 저장된 순서에 대한 인덱스 초기값변수
				// 아래 반복문의 목적 : jsp폼에서 기존 1번위치에 기존파일이 있으면, 기존파일을 지우고 신규 파일을 덮어 씌우는 로직
				for(AttachVO file_name : delFiles) { // 기존파일을 가져와서 반복
					if(index == sun) { // jsp폼의 파일의 순서와 DB에 저장된 파일의 순서가 일치할 때
						File target = new File(commonUtil.getUploadPath(), file_name.getSave_file_name()); 
						if(target.exists()) {
							target.delete(); // 물리적인 파일 지우는 명령
							boardDAO.deleteAttach(file_name.getSave_file_name());
						}
					}
					sun++;
				}
				save_file_names[index] = commonUtil.fileUpload(file); // jsp폼에서 전송된 파일
				real_file_names[index] = file.getOriginalFilename(); 	// UI용 이름 임시저장
						
			}
			else {
				save_file_names[index] = null;
				real_file_names[index] = null;
			}
			index++;
		}
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		
		
		// 시큐어코딩 추가부 START ------------------------------------------------------------------------
		String rawContent = boardVO.getContent();
		String secContent = commonUtil.unScript(rawContent);
		boardVO.setContent(secContent);
		
		String rawTitle = boardVO.getTitle();
		String secTitle = commonUtil.unScript(rawTitle);
		boardVO.setTitle(secTitle);
		// 시큐어코딩 추가부 END ------------------------------------------------------------------------
		
		boardService.updateBoard(boardVO);
		// 첨부파일 작업전, 시큐어코딩 : 입력/수정시 시큐어코딩 적용, 뷰화면만 시큐어를 적용했다면 이번엔 입력수정시 적용함
		
		String queryStr ="bno="+boardVO.getBno()+"&page="+pageVO.getPage()+"&search_type="+pageVO.getSearch_type();
		return "redirect:/admin/board/board_view?"+queryStr; // 수정한 이후에는 board_view 페이지로 이동 -> 새로고침을 방지하기위해서
	}
	
	// 게시물 수정폼은 URL 쿼리스트링으로 접근
	@RequestMapping(value="/admin/board/board_update_form" ,method = RequestMethod.GET)
	public String board_update_form(Model model, @RequestParam("bno")Integer bno, @ModelAttribute("pageVO")PageVO pageVO)  throws Exception {
		// 첨부파일용 save_file_names, real_file_names 2개 배열값을 구해서 boardVO입력이 필요
		BoardVO boardVO = new BoardVO();
		boardVO = boardService.readBoard(bno);
		// 여기서 첨부파일 배열을 추가 
		List<AttachVO> listAttachVO = boardService.readAttach(bno);
		String[] save_file_names = new String[listAttachVO.size()]; 
		String[] real_file_names = new String[listAttachVO.size()];
		
		int idx = 0;
		for(AttachVO file_name : listAttachVO) {
			save_file_names[idx] = file_name.getSave_file_name();
			real_file_names[idx] = file_name.getReal_file_name();
			idx++;
		}
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		
		model.addAttribute("boardVO", boardVO);
		return "admin/board/board_update";
	}
	
	// 게시물 삭제는 URL 쿼리스트링으로 접근하지 않고, post방식으로 처리
	@RequestMapping(value="/admin/board/board_delete", method = RequestMethod.POST)
	public String board_delete(@RequestParam("bno")Integer bno, PageVO pageVO) throws Exception {
		// 디버스 삭제할 전역변수 경로 확인
		logger.info("디버그 전역 업로드경로 : "+ commonUtil.getUploadPath());
		// DB테이블 삭제한 이후, 첨부파일부터 있으면 삭제처리. 자바에서 파일핸들링처리
		// 기존 등록된 첨부파일 폴더에서 삭제할 UUID(고유한 식별값 생성하는 클래스)이름을 추출
		List<AttachVO> delFiles = boardService.readAttach(bno); // 해당게시물의 모든 첨부파일 delFiles에 임시로 담아 놓습니다
		boardService.deleteBoard(bno); // 첨부파일테이블 삭제 후 게시물 테이블 삭제
		// 물리적으로 파일삭제 처리
		for (AttachVO file_name : delFiles) { // File클래스 ("파일의 업로드된 위치", "삭제할 파일명")
			File target = new File(commonUtil.getUploadPath(), file_name.getSave_file_name()); 
			if(target.exists()) {
				target.delete(); // 물리적인 파일 지우는 명령
			}
		}
		
		String queryStr = "page="+pageVO.getPage()+"&search_type="+pageVO.getSearch_type();
		return "redirect:/admin/board/board_list?"+queryStr;
	}
	
	// 게시물 상세보기는 폼으로 접근하지 않고 URL쿼리 스트링으로 접근(GET)
	@RequestMapping(value="/admin/board/board_view", method = RequestMethod.GET)
	public String board_view(@RequestParam("bno")Integer bno, @ModelAttribute("pageVO")PageVO pageVO, Model model) throws Exception {
		
		BoardVO boardVO = boardService.readBoard(bno);
		// 첨부파일 부분 데이터도 board_view.jsp로 이동해야함
		List<AttachVO> files = boardService.readAttach(bno);
		// 배열객체 생성
		// 개발자가 만든 클래스형 객체 boardVO는 개발자가 만든 메소드 사용
		// 반면, List<AttachVO> files List클래스형 객체 files는 내장형 메소드 .size()
		String[] save_file_names = new String[files.size()];
		String[] real_file_names = new String[files.size()];
		// attach 테이블안의 해당 bno 게시물의 첨부파일 이름 파싱해서 jsp로 보내주는 과정
		int cnt = 0;
		
		for (AttachVO file_name : files) { // files 다수 레코드에서 1개의 레코드씩 추출
			save_file_names[cnt] = file_name.getSave_file_name();
			real_file_names[cnt] = file_name.getReal_file_name();
			cnt++;
		}
		// 위 for문은 세로데이터(다수레코드)를 가로데이터(1개의 레코드, 배열)에 담아서 1개의 레코드 boardVO에 담는 것이 목적
		boardVO.setSave_file_names(save_file_names); // 파싱한 결과를 boardVO의 필드에 set해줌 // 다운로드 로직
		boardVO.setReal_file_names(real_file_names); // 화면출력 
		model.addAttribute("boardVO", boardVO);  // 게시물 + 첨부파일
		model.addAttribute("checkImgArray", commonUtil.getCheckImgArray());
		return "/admin/board/board_view";
	}
	
	// 게시물 목록은 폼으로 접근하지 않고 URL로 접근하기 때문에 GET방식으로 처리
	@RequestMapping(value="/admin/board/board_list", method = RequestMethod.GET)
	public String board_list(@ModelAttribute("pageVO") PageVO pageVO, Model model) throws Exception {
		// 페이징처리를 위한 기본값 추가
		if (pageVO.getBoard_type() == null) {
			pageVO.setBoard_type("notice");
		}
		if (pageVO.getPage() == null) {
			pageVO.setPage(1);
		}
		pageVO.setPerPageNum(5); // UI하단에서 보여줄 페이징 번호 크기
		// totalCount를 구하기전 필수값 2개를 요함
		pageVO.setQueryPerPageNum(5);
		pageVO.setTotalCount(boardService.countBoard(pageVO));
		model.addAttribute("listBoardVO", boardService.selectBoard(pageVO));
		return "admin/board/board_list";
	}
	
	//================================================================================================
	
	// jsp에서 게시판생성관리에 GET/POST 접근할 때 URL을 bbs_type으로 지정 // +a bbs는 bulletin' board system의 약자
	// 왜 board_type으로 하지않고, bbs_type으로 하는 이유는 왼쪽메뉴 고정시키는 로직에서 경로 board와 겹치지 않기위해서
	@RequestMapping(value="/admin/bbs_type/bbs_type_list", method = RequestMethod.GET)
	public String selectBoardTypeList(Model model) throws Exception { // 목록폼
		// model.addAttribute("listBoardTypeVO", boardTypeService.selectBoardType());
		return "admin/bbs_type/bbs_type_list"; // 상대경로일 때는, views 폴더가 최상위루트입니다
	}
	
	//-------------------------------------------------- url이 같아도 전송방식이 다르면 다르게 매핑됨 ---------------
	// bbs_type_list.jsp의 게시판 생성버튼을 클릭했을 때 이동하는 폼 경로
	@RequestMapping(value="/admin/bbs_type/bbs_type_insert", method = RequestMethod.GET)
	public String insertBoardTypeForm() throws Exception { // 입력폼
		return "admin/bbs_type/bbs_type_insert";
	}
	
	// bbs_type_list.jsp 입력폼에서 전송된 값을 BoardTypeVO에 (단)담아서 {구현} 
	// 단 자동으로 바인딩 되려면, 폼의 name이 속성 value가 VO멤버변수명과 동일해야함 // 전에는 @RequestParam으로 받아주었음
	@RequestMapping(value="/admin/bbs_type/bbs_type_insert", method = RequestMethod.POST)
	public String insertBoardType(BoardTypeVO boardTypeVO) throws Exception { // 
		boardTypeService.insertBoardType(boardTypeVO);
		return "redirect:/admin/bbs_type/bbs_type_list"; // redirect:는 절대경로를 사용합니다 forward:도 사용가능 (뒤로가기가 됨)
	}
	//--------------------------------------------------------------------------------------------------
	
	// 게시판 생성관리는 이 기능은 사용자단에서 UI를 사용할 일이 업식 떄문에, Read, Update를 1개로 사용합니다.
	// bbs_type_list.jsp의 목록에서 1개의 라인을 클릭했을 때 , 이동하는 폼 경로
	@RequestMapping(value="/admin/bbs_type/bbs_type_update", method = RequestMethod.GET)
	public String updateBoardTypeForm(@RequestParam("board_type")String board_type, Model model) throws Exception {
		model.addAttribute("boardTypeVO", boardTypeService.readBoardType(board_type));
		return "admin/bbs_type/bbs_type_update";
	}
	
	@RequestMapping(value="/admin/bbs_type/bbs_type_update", method = RequestMethod.POST)
	public String updateBoardType(BoardTypeVO boardTypeVO) throws Exception {
		boardTypeService.updateBoardType(boardTypeVO);
		return "redirect:/admin/bbs_type/bbs_type_update?board_type="+boardTypeVO.getBoard_type();
	}
	
	@RequestMapping(value="/admin/bbs_type/bbs_type_delete", method = RequestMethod.POST)
	public String deleteBoardType(@RequestParam("board_type")String board_type) throws Exception {
		boardTypeService.deleteBoardType(board_type);
		return "redirect:/admin/bbs_type/bbs_type_list";
	}
	
	//============================================================================================
	
	// 아래경로는 회원신규등록 폼을 호출하는 URL쿼리스트링으로 보낸 것을 받을 때는 GET방식으로 받습니다.
	@RequestMapping(value="/admin/member/member_insert_form", method = RequestMethod.GET)
	public String insertMemberForm(@ModelAttribute("pageVO") PageVO pageVO) throws Exception {
		
		return "admin/member/member_insert";
	}
	
	// 아래경로는 회원신규등록 처리하는 서비스호출 URL
	@RequestMapping(value="/admin/member/member_insert", method=RequestMethod.POST)
	public String insertMember(HttpServletRequest request, MultipartFile file, PageVO pageVo, MemberVO memberVO) throws Exception {
		if(!file.getOriginalFilename().isEmpty()) {
			commonUtil.profile_upload(memberVO.getUser_id(), request, file);
		}
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String rawPassword = memberVO.getUser_pw();
		String encPassword = passwordEncoder.encode(rawPassword);
		memberVO.setUser_pw(encPassword);
		memberService.insertMember(memberVO);
		return "redirect:/admin/member/member_list";
	}
	//아래 경로는 수정처리를 호출=DB를 변경처리함.
	@RequestMapping(value="/admin/member/member_update", method = RequestMethod.POST)
	public String updateMember(HttpServletRequest request, MultipartFile file, MemberVO memberVO, PageVO pageVO) throws Exception {
		// 프로필 이미지 처리 추가
		if (!file.getOriginalFilename().isEmpty()) {
			String user_id = memberVO.getUser_id();
			commonUtil.profile_upload(user_id, request, file);
		}
		//update 서비스만 처리하면 끝
		//업데이트 쿼리서비스 호출하기 전 스프링시큐리티 암호화 적용합니다.
		String rawPassword = memberVO.getUser_pw();
		if(!rawPassword.isEmpty()) {//수정폼에서 암호 입력값이 비어있지 않을때만 아래로직실행.
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encPassword = passwordEncoder.encode(rawPassword);
			memberVO.setUser_pw(encPassword);
			// 스프링시큐리티 내장클래스에서 user_pw(admin1234)와 password(해시값) 비교함수
		}
		memberService.updateMember(memberVO);//반환값이 없습니다.
		//redirect로 페이지를 이동하면, model로 담아서 보낼수 없습니다. 쿼리스트링(URL?)으로 보냅니다.
		String queryString = "user_id="+memberVO.getUser_id()+"&page="+pageVO.getPage()+"&search_type="+pageVO.getSearch_type();
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
	public String deleteMember(HttpServletRequest request, MemberVO memberVO) throws Exception {
		logger.info("디버그: " + memberVO.toString());
		//MemberVO memberVO는 클래스형 변수: String user_id 스트링형 변수 같은 방식.
		String user_id = memberVO.getUser_id();
		//이 메서드는 회원상세보기페이지에서 삭제버튼을 클릭시 전송받은 memberVO값을 이용해서 삭제를 구현(아래)
		memberService.deleteMember(user_id);//삭제쿼리가 실행됨
		//return "admin/member/member_list";//삭제후 이동할 jsp경로지정
		//위 방식대로하면, 새로고침하면, /admin/member/member_delete 계속 실행됩니다.-사용자단에서 실습
		//게시판테러상황을 방지하기 위해서, 쿼리를 작업 후 이동할때는 redirect(다시접속)라는 명령을 사용합니다.
		//DB테이블 삭제 후 회원 프로필 이미지가 exist() == true일 때, 삭제하는 로직 추가
		commonUtil.profile_delete(user_id, request);
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
		PageVO pageVO = new PageVO(); // 최소 3개의 기본값이 필수
		pageVO.setQueryPerPageNum(4);
		pageVO.setPage(1);
		List<MemberVO> latestMembers = memberService.selectMember(pageVO);
		model.addAttribute("latestMembers", latestMembers);
		return "admin/home";//리턴 경로=접근경로는 반드시 상대경로로 표시
	}
	// 메인페이지 또는 대시보드에 최신 테이블리스트를 출력하는 2가지 방법(위, model사용)
	// 아래는 jstl c:import방식 : 최신 게시물용도로 사용 // 페이지안에서 컴파일된 다른 페이지를 불러올 수 있음 // include같은 방식
	@RequestMapping(value = "/admin/latest/latest_board", method = RequestMethod.GET)
	public String latest_board(@RequestParam(value = "board_type", required = false)String board_type,@RequestParam(value = "board_name", required = false)String board_name, Model model) throws Exception {
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);
		pageVO.setQueryPerPageNum(5);
		pageVO.setBoard_type(board_type);
		List<BoardVO> latestBoard = boardService.selectBoard(pageVO);
		model.addAttribute("board_name", board_name);
		model.addAttribute("board_type", board_type);
		model.addAttribute("latestBoard", latestBoard);
		return "admin/latest/latest_board";
	}
	
	
	
	
	
}
