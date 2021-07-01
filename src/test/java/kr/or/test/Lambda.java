package kr.or.test;

import java.util.List;
import java.util.function.IntSupplier;

import com.edu.service.IF_MemberService;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 Lambda식(Arrow Method)을 테트합니다
 * @author 김상훈
 *
 */
public class Lambda {
	
	// static method 1. 이 클래스를 컴파일하면, 메모리에 등록되는 메소드
	// static을 지정하지 않으면 호출시에 메모리에 등록이됨
	// 				 2. 이 클래스에서만 메소드로 사용하겠다는 암시 (상속을 하지않음)
	public static int plus(int x , int y, String lambda ) {
		int result = 0;
		if(lambda.equals("nonLambda")) {
			// 람다식 적용전
			IntSupplier intSupplier = new IntSupplier(){
				
				@Override
				public int getAsInt() {
					int sum = x + y;
					return sum;
				}
				
				public int getAsInt2() {
					int product = x * y;
					return product;
				}
			};
			result = intSupplier.getAsInt();
		}
		// 람다식 적용 제약이 있음 인터페이스 추상메소드가 1개인 것만 익명구현객체로 가능
		// (매개변수) -> 자바일때 형식, 람다식 익명함수 매개변수 1개일 때는 괄호()생략가능
		// (무명함수) => ~ 자바스크립트일때 애로우함수
		if (lambda.equals("lambda")) {
			IntSupplier intSupplier = () -> x + y;
			result = intSupplier.getAsInt();
		}
		return result;
		
		//=========================람다식구현===============================
	}
	
	public String memberOne() {
		String result = "";
		
		
		return result; 
	}
	
	public static void main(String[] args) {
		System.out.println("람다식 적용전 메소드 결과 : "+plus(3, 4, "nonLambda"));
		System.out.println("람다식 적용후 메소드 결과 : "+plus(3, 4, "lambda"));
	}
}
