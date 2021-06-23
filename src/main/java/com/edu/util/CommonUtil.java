package com.edu.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	// 첨부파일 업로드/다운로드/삭제/인서트/수정에 모두 사용될 저장결로를 1개 지정해서 전역으로 사용
	@Resource(name = "uploadPath")
	private String uploadPath; // root-context.xml 업로드경로 클래스빈 id값을 받아서 String변수 입력
	
	public String getUploadPath() {
		return uploadPath;
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
	
	// 파일 업로드 공통 메소드 (21-06-23 현재는 AdminController에서 사용하지만 HomeController에서도 사용할 예정임)
	public String fileUpload(MultipartFile file) throws IOException {
		// UUID클래스로 저장될 고유 식별(PK) 파일명을 생성 후 실제 물리적으로 저장
		String realFileName = file.getOriginalFilename();
		// 폴더에 저장할 PK파일명을 생성(아래)
		UUID uid = UUID.randomUUID(); // 유니크 ID값 생성
		String saveFileName = uid.toString() + "." + StringUtils.getFilenameExtension(realFileName);
		byte[] fileData = file.getBytes();
		File target = new File(uploadPath, saveFileName);
		FileCopyUtils.copy(fileData, target); // 물리적으로 폴더에 저장됩니다
		return saveFileName;
		
	}
	
	
	
}
