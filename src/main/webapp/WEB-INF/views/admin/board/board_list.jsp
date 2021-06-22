<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<%@ include file= "../include/header.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">${pageVO.board_type} 리스트</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">${pageVO.board_type} 게시물 관리</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- 콘텐츠 내용 START  -->
        <div class="card">
          <div class="card-header">
            <h3 class="card-title">목록</h3>

            <div class="card-tools">
              <form name="form_search" action="/admin/board/board_list" methode="GET">
                <div class="input-group input-group-md" style="width: 400px;">
                  <select name="search_type" class="form-control pl-1 mx-3">
                    <option value="all">전체</option>
                    <option value="title">제목</option>
                    <option value="content">내용</option>
                  </select>
                  <input type="text" name="search_keyword" class="form-control float-right" placeholder="Search" style="width: 100px;">
                  <div class="input-group-append">
                    <button type="submit" class="btn btn-default">
                      <i class="fas fa-search"></i>
                    </button>
                    <%-- <input type="hidden" value="${pageVO.board_type}" name="board_type" > --%>
                  </div>
                </div>
              </form>
            </div>
			
            
          <!-- 
            <h3 class="card-title" style="position: absolute;">목록</h3>
            <div class="input-group input-group-md justify-content-end">
              
                <form name="form_search" action="/admin/board/board_list" method="GET" class="form-horizontal">
                  <select name="search_type" class="form-control float-left" style="width: inherit;">
                    <option>전체</option>
                    <option>제목</option>
                    <option>내용</option>
                  </select>  
                  <input type="text" name="table_search" class="form-control float-left" placeholder="Search" style="width: inherit;">
                  <div class="input-group-append float-left" style="width: inherit;">
                    <button type="submit" class="btn btn-default">
                      <i class="fas fa-search"></i>
                    </button>
                  </div>                                
                </form>
              </div>
          -->
           
            </div>

          <!-- /.card-header -->
          <div class="card-body table-responsive p-0">
            <table class="table table-hover">  <!-- 줄바꿈 하지 않을 때 text-nowrap 추가 -->
              <thead>
                <tr class="text-center">
                  <th class="text-center">BNO</th>
                  <th class="text-center">BOARD_TYPE</th>
                  <th class="text-center col-6">TITLE</th>
                  <th class="text-center">WRITER</th>
                  <th class="text-center">REG_DATE</th>
                </tr>
              </thead>
              <tbody>
              <c:if test="${empty listBoardVO}">
	              	<tr>
              		<td colspan="5" class="text-center">조회된 값이 없습니다</td>
	              	</tr>
              	</c:if>
                <!-- 아래 링크주소에 jsp에서 프로그램처리 예정 -->
                <c:forEach var="boardVO" items="${listBoardVO}">
                  <tr onclick="location.replace('/admin/board/board_view?bno=${boardVO.bno}&page=${pageVO.page}&search_type=${pageVO.search_type}&search_keyword=${search_keyword}')" style="cursor:pointer">
                    <td>${boardVO.bno}</td>
                    <td>${boardVO.board_type}</td>
                    <td>${boardVO.title}</td>
                    <td>${boardVO.writer}</td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss.SSSS" value="${boardVO.reg_date}"/></td>
                  </tr>    
                </c:forEach>
                </tbody>
            </table>
          </div>
          <!-- /.card-body -->
        </div>
        <!-- 콘텐츠 내용 END  -->
        <!-- 페이징처리 START -->
        <div class="row">
            <div class="col-12 text-right">
            <a href="/admin/board/board_insert" class="btn btn-primary mb-3">글쓰기</a>
            <ul class="pagination justify-content-center">
              <li class="paginate_button page-item previous ${pageVO.prev == false? 'disabled':''}" id="example2_previous">
                <a href="/admin/board/board_list?page=${pageVO.startPage - 1}&search_type=${pageVO.search_type}&search_keyword=${search_keyword}" aria-controls="example2"
                  data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
              </li>
              
              <c:forEach  begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1" var="idx">
              <li class="paginate_button page-item ${pageVO.page == idx? 'active':''}">
                <a href="/admin/board/board_list?page=${idx}&search_type=${pageVO.search_type}&search_keyword=${search_keyword}" aria-controls="example2" data-dt-idx="1" tabindex="0"
                  class="page-link">${idx}</a>
              </li>
              </c:forEach>
              
              <li class="paginate_button page-item next ${pageVO.next == false? 'disabled':''}" id="example2_next">
                <a href="/admin/board/board_list?page=${pageVO.endPage - 1}&search_type=${pageVO.search_type}&search_keyword=${search_keyword}" aria-controls="example2"
                  data-dt-idx="0" tabindex="0" class="page-link">Next</a>
              </li>
            </ul>
    
          </div>

        </div>
        <!-- 페이징처리 END -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>

  <!-- /.content-wrapper -->


<%@ include file= "../include/footer.jsp" %>