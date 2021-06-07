package kr.or.test;
/**
 * 이 클래스는 자바앱에서 개발자 예외를 처리하는 방법 실습하는 클래스 입니다
 * @author 김상훈
 *
 */
public class ExceptionTest {
	
	public static void main(String[] args) {
		// 외부 클래스 형 변수에 값을 저장해서 출력하는 프로그램(아래)
		MemberVO memberVO = new MemberVO();
		// 위 처럼 동일 패키지안의 클래스는 import없이 사용 가능합니다.
		memberVO.setName("홍길동");
		memberVO.setAge(10);
		memberVO.setPhoneNum("000-0000-0000");
		// toString()을 오버라이딩합니다
		System.out.println(memberVO.toString());	
		
		// 배열변수 선언
		String[] stringArray = {"10", "2a", "100"};
		int indexValue = 0;
		for (int cnt=0; cnt<=3; cnt++) {
/*
* 	 		개발자가 예외처리하는 기본 형식 => try {구현프로그램에서 에러가 발생하면 catch로 이동 *필수*} catch(이러한 에러가 발생하면) 
*  			{*필수*에러발생시 구현내용} catch() {}... finally {에러가 발생하든 말든 무조건 실행  *선택사항*}
*/			
			try {
				indexValue = Integer.parseInt(stringArray[cnt]);
			} catch (NumberFormatException e) { //Exception(포괄적객체) 대신에 선별적으로 예외사항을 잡습니다
				System.out.println(cnt+"번째 숫자형태가 올바르지 않습니다. 확인해주세요!");
				System.out.println("에러내용 = "+e.toString());
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("배열의 크기를 벗어났습니다.");
				System.out.println("에러내용 = "+e.toString());
			} 
			finally {
				System.out.println("indexValue의 값 = "+indexValue);
				System.out.println((cnt)+ "번째 프로그램이 종료되었습니다");
			}
			System.out.println("----------------------------------");
		}
			
	}
	
}
