<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="./include/header.jsp" %>
<!-- **이후 메인 컨테이너 영역**-->
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">Dashboard</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Dashboard v1</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- 최근 등록한 회원목록 START -->
        <div class="card">
          <div class="card-header">
            <h3 class="card-title">최근 등록한 회원</h3>

            <div class="card-tools">
              <!-- <span class="badge badge-danger">8 New Members</span> -->
              <button type="button" class="btn btn-tool" data-card-widget="collapse">
                <i class="fas fa-minus"></i>
              </button>
              <button type="button" class="btn btn-tool" data-card-widget="remove">
                <i class="fas fa-times"></i>
              </button>
            </div>
          </div>
          <!-- /.card-header -->
          <div class="card-body p-0">
            <ul class="users-list clearfix">
          <!-- 최신 등록한 회원정보 4개 출력 반복문 사용 -->
          <c:forEach var="memberVO" items="${latestMembers}">
              <li onclick="location.replace('/admin/member/member_view?user_id=${memberVO.user_id}&page=1')" style="cursor: pointer;">
              	<!-- 
              	<img alt="" src="/resources/admin/dist/img/default-150x150.png">
               	-->	
               	<!-- onerror 속성 : 엑박나올 때 처리해주는 속성 -->
                <img style="width:120px; height:120px;" onerror="this.src='/resources/admin/dist/img/default-150x150.png'" src="/resources/profile/${memberVO.user_id}.png" alt="User Image">
                <a class="users-list-name" href="">${memberVO.user_name }</a>
                <span class="users-list-date">
                <fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${memberVO.reg_date}"/>
                </span>
              </li>
          </c:forEach>
            </ul>
            <!-- /.users-list -->
          </div>
          <!-- /.card-body -->
          <div class="card-footer text-center">
            <a href="/admin/member/member_list">View All Users</a>
          </div>
          <!-- /.card-footer -->
        </div>
        <!-- 최근 등록한 회원목록 END -->
        <!-- 최근 게시물 리스트(공지사항+갤러리+Q&A) START 현재는 2개의 게시판이 나오게 됨 -->
        <!-- include와 import의 차이점 include 소스를 조립후 컴파일, import는 컴파일--> 
        <c:forEach var="boardTypeVO" items="${listBoardTypeVO}">
	        <c:import url="/admin/latest/latest_board?board_type=${boardTypeVO.board_type}&board_name=${boardTypeVO.board_name}" />
        </c:forEach>
        <!-- 최근 게시물 리스트 END  -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>

<!-- **여기까지 메인 컨테이너  영역** -->
<%@ include file="./include/footer.jsp" %>
