package kr.or.test;

public class TestIO {
	public static void main(String[] args) {
		int i = 0;
		int j = 1;
		while((i = j) == 1) {
			System.out.println(i);
			j++;
		}
		
	}
}
