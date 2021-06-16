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
            <h1 class="m-0">게시판 리스트</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">게시판 생성관리</li>
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
                  <th class="text-center">BOARD_TYPE</th>
                  <th class="text-center">게시판 이름</th>
                  <th class="text-center">출력순서</th>
                </tr>
              </thead>
              <tbody>
                <!-- 아래 링크주소에 jsp에서 프로그램처리 예정 -->
               <c:forEach var="boardTypeVO" items="${listBoardTypeVO}">
                <tr onclick="location.replace('/admin/bbs_type/bbs_type_update?board_type=notice');" style="cursor:pointer">
                    <td>${boardTypeVO.board_type}</td>
                    <td>${boardTypeVO.board_name}</td>
                    <td>${boardTypeVO.board_sun}</td>
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
            <a href="/admin/bbs_type/bbs_type_insert" class="btn btn-primary mb-3">게시판등록</a>
	    
          </div>

        </div>
        <!-- 페이징처리 END -->
      </div><!-- /.container-fluid -->
    </section>
  </div>
  <!-- /.content -->

<%@ include file="../include/footer.jsp" %>