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
		// this.page-1하는 이유 jsp에서는 get으로 받을때 1부터 받지만 
		// 쿼리에서는 0,1,2....으로 시작하기 때문에 page*1 = 1페이지당 보여줄 개수
		queryStartNo = (this.page-1)*queryPerPageNum;
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
		calcPage();
	}
	private void calcPage() {
		// 이 메소드는 totalCount 변수값을 기반으로 prev, next, startPage, endPage...etc 구현하게 됩니다
		// jsp에서 클릭한 페이지번호  예로 1부터 5까지는 클릭하면 임시 끝페이지가 5
		// 만약 6번 페이지를 클릭하면, 임시 끝페이지가 10으로 바뀜
		// "< 1(startPage) 2 3 4 5(tempEnd) >" next 클릭시 "< 6(startNum) 7 8 9 10(tempEnd) >" 로 바뀜 
		// 13페이지가 전부라면 < 11(startNum) 12 13(endPage) >으로 끝나게 됨
		
		int tempEnd = (int) Math.ceil(page/(double)this.perPageNum)*this.perPageNum;
		this.startPage = (tempEnd - this.perPageNum) + 1;
		// UI페이지하단에 페이지번호가 출력되도록 하는 반복의 시작 변수
		// next를 누르면 tempEnd가 10으로 바뀌고 이에 따라 startPage도 바뀌어야 하는데 tempEnd가 10이고 여기에 5개의 페이지를 노출시키는 perPageNum을 빼주고 1을 더해주면 됨
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
