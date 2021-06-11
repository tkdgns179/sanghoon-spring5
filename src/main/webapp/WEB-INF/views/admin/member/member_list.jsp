<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../include/header.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">회원 리스트</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">관리자 관리</li>
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
			<!-- 내용검색폼 -->
            <div class="card-tools">
              <form name="form_search" action="/admin/member/member_list" methode="GET">
                <div class="input-group input-group-md" style="width: 400px;">
                  <select name="search_type" class="form-control pl-1 mx-3" style="">
                    <option value="all">전체</option>
                    <option value="user_id">아이디</option>
                    <option value="user_name">이름</option>
                  </select>
                  <input type="text" name="search_keyword" class="form-control float-right" placeholder="Search" style="width: 100px;">
                  <div class="input-group-append">
                    <button type="submit" class="btn btn-default">
                      <i class="fas fa-search"></i>
                    </button>
                  </div>
                </div>
              </form>
            </div>

            
          <!--
            <div class="card-tools">
              <div class="input-group input-group-md">
                <form name="form_search" action="board_list.html" method="GET" class="form-horizontal">
                  <select class="form-control float-left" style="width: inherit;">
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
            </div> 
          -->
           
            </div>

          <!-- /.card-header -->
          <div class="card-body table-responsive p-0">
            <table class="table table-hover">  <!-- 줄바꿈 하지 않을 때 text-nowrap 추가 -->
              <thead>
                <tr class="text-center">
                  <th class="text-center">사용자아이디</th>
                  <th class="text-center">사용자이름</th>
                  <th class="text-center">이메일</th>
                  <th class="text-center">레벨</th>
                  <th class="text-center">가입일자</th>
                </tr>
              </thead>
              
              <tbody>
              	<c:if test="${empty listMember}">
	              	<tr>
              		<td colspan="5" class="text-center">조회된 값이 없습니다</td>
	              	</tr>
              	</c:if>
	            <!-- listMember 리스트가 비었을 때 -->
                <!-- JSTL 반복문으로 listMember 객체 바인딩 -->
                <c:forEach var="memberVO" items="${listMember}">
				<tr style="cursor: pointer;" onclick="location.replace('/admin/member/member_view?page=${pageVO.page}&search_type=${pageVO.search_type}&search_keyword=${pageVO.search_keyword}&user_id=${memberVO.user_id}');">
                    <td><c:out value="${memberVO.user_id}" /></td>
                    <td><c:out value="${memberVO.user_name}" /></td>
                    <td><c:out value="${memberVO.email}" /></td>
                    <td><span class="tag tag-success">${memberVO.levels}</span></td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss.SSSS" value="${memberVO.reg_date}"/></td>
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
            <a href="board_write.html" class="btn btn-primary mb-3">글쓰기</a>
            <ul class="pagination justify-content-center">
            
              
              <li class="paginate_button page-item previous <c:out value="${pageVO.prev == false? 'disabled':'' }"></c:out>" id="example2_previous">
                <a href="/admin/member/member_list?page=${pageVO.startPage-1}&search_keyword=${pageVO.search_keyword}&seach_type=${search_type}" aria-controls="example2"
                  data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
              </li>
              
              <c:forEach begin="${pageVO.startPage}" end="${pageVO.endPage}" step="1" var="idx">
              
              <li class="paginate_button page-item <c:out value="${idx == pageVO.page? 'active':''}" />">
                <a href="/admin/member/member_list?page=${idx}&search_keyword=${pageVO.search_keyword}&search_type=${pageVO.search_type}"
                aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">${idx}</a>
              </li>
              <c:out value=""></c:out>
              </c:forEach> 
              
              <li class="paginate_button page-item <c:out value="${pageVO.next == false? 'disabled':'' }"></c:out>" id="example2_next">
                <a href="/admin/member/member_list?page=${pageVO.endPage+1}&search_keyword=${pageVO.search_keyword}&search_type=${search_type}" aria-controls="example2" data-dt-idx="7"
                  tabindex="0" class="page-link">Next</a>
              </li>
            </ul>
    
          </div>

        </div>
        <!-- 페이징처리 END -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>


<%@ include file="../include/footer.jsp" %>