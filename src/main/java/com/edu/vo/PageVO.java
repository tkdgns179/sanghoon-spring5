package com.edu.vo;
/**
 * 이 클래스는 공통(회원관리, 게시물관리, ...)으로 사용하는 페이징처리 + 검색기능의 클래스
 * @author 김상훈
 * p.s. 이 클래스는 Oracle이든, MySQL(MariaDB) 어디서든 공통으로 사용하는 Get/Set
 * 
 * <queryStartNo, queryPerPageNum, page, perPageNum, startPage, endPage>  
 * 검색에 사용되는 변수(쿼리변수만): 검색어(search_keyword), 검색조건(search_type)
 * 
 */
public class PageVO {
	private int queryStartNo; 		// 쿼리전용 변수
	private int queryPerPageNum; 	// 쿼리전용 변수
	private Integer page;			// jsp에서발생 자바전용
	private int perPageNum; 		// UI 하단에 보여줄 페이징 개수 계산에 필요
	
	private int totalCount;			// 계산식의 기초값으로써 이 전체개수가 구해진 이후 페이징등의 계산식이 진행됩니다
	
	private int startPage; 			// 위 perPageNum으로 구하는 UI하단 시작번호
	private int endPage; 			// 위 perPageNum으로 구하는 UI하단 페이지 끝번호 
	
	private boolean  prev; 			// UI하단에 이전 페이지로 이동이 가능한지 참거짓으로 판별하는 변수
	private boolean  next; 			// UI하단에 다음 페이지로 이동이 가능한지 참거짓으로 판별하는 변수
	
	private String search_keyword; 	// jsp에서 받은 검색어 쿼리전용 변수
	private String search_type; 	// 검색조건 해당하는 쿼리전용 변수
}
