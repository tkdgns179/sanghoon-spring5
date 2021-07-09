package com.edu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.dao.IF_BoardDAO;
import com.edu.service.IF_MemberService;
import com.edu.vo.BoardVO;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 이 프로젝트에서 공통으로 사용하는 유틸리티 기능을 모아놓은 클래스입니다
 * @author 김상훈
 * 컨트롤러 기능 @Controller (jsp와 바인딩이 필요할 때 필수 어노테이션입니다)
 * 컴포넌트는 @Component는 MVC가 아닌 기능들을 모아놓은 것을 스프링빈으로 명시, 여기서 jsp와 바인딩이 필요하여 @Controller를 사용함
 */
@Controller
public class CommonUtil {
	// 멤버변수 생성
	
	@Inject
	private IF_MemberService memberService;
	@Inject
	private IF_BoardDAO boardDAO;
	
	private Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	@Resource(name = "uploadPath")
	private String uploadPath; // root-context.xml 업로드경로 클래스빈 id값을 받아서 String변수 입력
	
	public String getUploadPath() {
		return uploadPath;
	}
	
	@RequestMapping(value="/file_delete", method = RequestMethod.POST)
	@ResponseBody
	public String file_delete(@RequestParam("save_file_name")String save_file_name) { 
		// Ajax는 예외처리를 스프링에 던지지 않고 try catch문으로 처리함
		String result = ""; // Ajax로 보내는 변수
		try {
			boardDAO.deleteAttach(save_file_name);
			File target = new File(uploadPath+"/"+save_file_name);
			if (target.exists()) { // DB에서 삭제
				target.delete();
			}
			result = "success";
		} catch (Exception e) {
			result = "fail: " + e.toString();
		} 
		return result; // Ajax에서 진입성공/실패를 확인가능
	}
	
	// 다운로드 처리도 같은 페이지에서 결과값만 반환받는 @ResponseBody 사용
	@RequestMapping(value="/download", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource download(@RequestParam("save_file_name")String save_file_name, @RequestParam("real_file_name")String real_file_name, HttpServletResponse response) throws Exception {
		// FileSystem~ 스프링 코어에서 제공하는 전용 파일처리 클래스
		File file = new File(uploadPath + "/" + save_file_name);
		response.setContentType("application/download; utf-8"); // 아래한글, ppt문서등에서 한글이 깨는 것을 방지하는 코드
		real_file_name = URLEncoder.encode(real_file_name); // ie에서 URL 한글일 때
		response.setHeader("Content-Disposition", "attachment; filename="+ real_file_name );
		
		return new FileSystemResource(file);
	}

	// 첨부파일 업로드/다운로드/삭제/인서트/수정에 모두 사용될 저장결로를 1개 지정해서 전역으로 사용
	
	// 페이지이동이 아닌 같은 페이지에 결과값만 반환하는 @ResponseBody 어노테이션 사용
	@RequestMapping(value="/image_preview", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> imagePreview(@RequestParam("save_file_name")String save_file_name, HttpServletResponse response) throws Exception {
		// 파일을 입출력할 때는 파일을 byte형으로 입출력할 때 발생되는 통로인 스트림이 발생
		ByteArrayOutputStream baos = new ByteArrayOutputStream();	//출력 스트림
		FileInputStream fis = new FileInputStream(uploadPath + "/"+ save_file_name);
		
		int readCount = 0;
		byte[] buffer = new byte[1024]; // buffer: 임시저장소 1KB
		byte[] fileArray = null; 		// 출력스트림의 결과를 저장하는 공간
		// 반복문의 목적 : fis로 입력받은 save_file_name 바이트값이(배열) -1(더이상 읽을 내용이 없음)이 나올 때 까지
		while ((readCount = fis.read(buffer)) != -1) {
			// 입력스트림으로 받은 바이트형 데이터를 출력스트림을 통해 출력함 (파일입출력은 바이트로 구성되어있음)
			baos.write(buffer, 0, readCount); // (rawData, 종료조건, 반복횟수)
			// 결과는 baos에 누적된 결과가 들어감 -> jsp로 전달
		}
		fileArray = baos.toByteArray(); // baos 객체를 byte[]로 파싱합니다
		fis.close(); 					// 입력스트림 닫기
		baos.close();					// 아웃풋스트림 닫기
		
		// fileArray값을 jsp로 보내주는 작업, final 이 메소드에서만 사용하겠다는 명시
		final HttpHeaders headers = new HttpHeaders();
		String ext = FilenameUtils.getExtension(save_file_name);
		// 이미지 확장자에 따라서 매칭되는 헤더값이 변해야지만, 이미지 미리보기가 정상으로 보입니다.
		switch (ext.toLowerCase()) {
		case "png":
			headers.setContentType(MediaType.IMAGE_PNG);
			break;
		case "jpg":
			headers.setContentType(MediaType.IMAGE_JPEG);
			break;
		case "gif":
			headers.setContentType(MediaType.IMAGE_GIF);
			break;
		case "jpeg":
			headers.setContentType(MediaType.IMAGE_JPEG);
			break;
		case "bmp":
			headers.setContentType(MediaType.parseMediaType("image/bmp"));
			break;
		
		default: break;
		}
		
		return new ResponseEntity<byte[]>(fileArray, headers, HttpStatus.CREATED); // 생성자 초기값(rawData, header정보, http상태값)
	}
	
	// XSS 크로스 사이트 스크립트 방지용 코드로 파싱하는 메소드
	public String unScript(String data) {
		// if (data == null || data.trim().equals(""))
		if (data.isEmpty()) {
			return "";
		}
		String ret = data;
		ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");
        ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)", "&lt;embed");
        ret = ret.replaceAll("<(F|f)(O|o)(R|r)(M|m)", "&lt;form");
        ret = ret.replaceAll("</(F|f)(O|o)(R|r)(M|m)", "&lt;form");

		return ret;
	}
	

	// 첨부파일이 이미지인지 아닌지 확인하는 데이터생성
	private ArrayList<String> checkImgArray = new ArrayList<String>(){
		{
		add("gif");
		add("jpg");
		add("jpeg");
		add("png");
		add("bmp");
		}
	};
	
	
	public ArrayList<String> getCheckImgArray() {
		return checkImgArray;
	}

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
		return memberCnt; // 0.jsp로 가지않음 이유는 @ResponseBody 때문이고, RestAPI는 값만을 반환함
	}
	
	// 사용자단에서 사용 : JsonView로 RestAPI구현
	@RequestMapping(value = "id_check_2010", method = RequestMethod.GET)
	public String id_check_2010(@RequestParam("user_id")String user_id, Model model) throws Exception {
		
		String memberCnt = "1"; // 중복ID가 있는 것을 기본값으로 설정
		
		if (!user_id.isEmpty()) {
			MemberVO memberVO = memberService.readMember(user_id);
			if (memberVO == null) {
				memberCnt = "0";
			}
		}
		model.addAttribute("memberCnt", memberCnt);
		return "jsonView"; // servlet에서 정의한 스프링 빈ID를 적으면, json객체로 결과를 반환합니다
	}
	
	// 파일 업로드 공통 메소드 (21-06-23 현재는 AdminController에서 사용하지만 HomeController에서도 사용할 예정임)
	public String fileUpload(MultipartFile file) throws IOException {
		// UUID클래스로 저장될 고유 식별(PK) 파일명을 생성 후 실제 물리적으로 저장
		String realFileName = file.getOriginalFilename();
		// 폴더에 저장할 PK파일명을 생성(아래)
		UUID uid = UUID.randomUUID(); // 유니크 ID값 생성
		String saveFileName = uid.toString() + "." + StringUtils.getFilenameExtension(realFileName);
		// file의 MultipartFile 클래스형 객체  클래스형 자료(멤버변수, 메소드... )는 직접 저장을 할 수 없음
		// 그래서, 바이트형으로 파싱(변환)해서 저장해야함 -> byte(8bits 01010101) 이진 비트형 자료로 변환필요  
		// 자바 자료형 정수 : byte(bit로 구성) short int long, 실수형(소수점) : float < double
		byte[] fileData = file.getBytes();
		File target = new File(uploadPath, saveFileName);
		FileCopyUtils.copy(fileData, target); // 물리적으로 폴더에 저장됩니다
		return saveFileName;
		
	}
	
	// 게시물 CRUD시 작성자가 본인인지 확인하는 메소드를 추가합니다
	public String board_crud_check(HttpServletRequest request, BoardVO boardVO, PageVO pageVO, RedirectAttributes rdat) throws Exception {
		HttpSession session = request.getSession();
		// 로그인한 세션ID와 boardVO.writer사용자와 비교해서 같으면 계속진행하고, 틀리면 멈추도록 코드작성
		if (!boardVO.getWriter().equals(session.getAttribute("session_userid"))) {
			rdat.addFlashAttribute("msgError", "작성자가 아닙니다!");
			return "redirect:/home/board/board_view?bno="+boardVO.getBno()+"&page="+pageVO.getPage();	
		}
		return "";
	}
	
	
}
