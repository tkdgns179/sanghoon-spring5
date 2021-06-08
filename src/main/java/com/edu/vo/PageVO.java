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
	private Integer page;			// jsp에서발생 자바전용 int인데 Null값을 허용
	private int perPageNum; 		// UI 하단에 보여줄 페이징 개수 계산에 필요
	
	private int totalCount;			// 계산식의 기초값으로써 이 전체개수가 구해진 이후 페이징등의 계산식이 진행됩니다
	
	private int startPage; 			// 위 perPageNum으로 구하는 UI하단 시작번호
	private int endPage; 			// 위 perPageNum으로 구하는 UI하단 페이지 끝번호 
	
	private boolean  prev; 			// UI하단에 이전 페이지로 이동이 가능한지 참거짓으로 판별하는 변수
	private boolean  next; 			// UI하단에 다음 페이지로 이동이 가능한지 참거짓으로 판별하는 변수
	
	private String search_keyword; 	// jsp에서 받은 검색어 쿼리전용 변수
	private String search_type; 	// 검색조건 해당하는 쿼리전용 변수
	
	public int getQueryStartNo() {
		return queryStartNo;
	}
	public void setQueryStartNo(int queryStartNo) {
		this.queryStartNo = queryStartNo;
	}
	public int getQueryPerPageNum() {
		return queryPerPageNum;
	}
	public void setQueryPerPageNum(int queryPerPageNum) {
		this.queryPerPageNum = queryPerPageNum;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public int getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public String getSearch_keyword() {
		return search_keyword;
	}
	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}
	public String getSearch_type() {
		return search_type;
	}
	public void setSearch_type(String search_type) {
		this.search_type = search_type;
	}
	
	
}
