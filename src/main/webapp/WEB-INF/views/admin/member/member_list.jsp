<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                  <th class="text-center">BNO</th>
                  <th class="text-center">BOARD_TYPE</th>
                  <th class="text-center col-6">TITLE</th>
                  <th class="text-center">WRITER</th>
                  <th class="text-center">REG_DATE</th>
                </tr>
              </thead>
              <tbody>
                <!-- 아래 링크주소에 jsp에서 프로그램처리 예정 -->
                  <tr onclick="location.replace('board_view.html?bno=183')" style="cursor:pointer">
                    <td>183</td>
                    <td>NOTICE</td>
                    <td>Bacon ipsum dolor sit.</td>
                    <td><span class="tag tag-success">Approved</span></td>
                    <td>11-7-2014</td>
                  </tr>    
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
              <li class="paginate_button page-item previous disabled" id="example2_previous">
                <a href="#" aria-controls="example2"
                  data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
              </li>
              <li class="paginate_button page-item active">
                <a href="#" aria-controls="example2" data-dt-idx="1" tabindex="0"
                  class="page-link">1</a>
              </li>
              <li class="paginate_button page-item ">
                <a href="#" aria-controls="example2" data-dt-idx="2" tabindex="0"
                  class="page-link">2</a>
              </li>
              <li class="paginate_button page-item ">
                <a href="#" aria-controls="example2" data-dt-idx="3" tabindex="0"
                  class="page-link">3</a>
              </li>
              <li class="paginate_button page-item ">
                <a href="#" aria-controls="example2" data-dt-idx="4" tabindex="0"
                  class="page-link">4</a>
              </li>
              <li class="paginate_button page-item ">
                <a href="#" aria-controls="example2" data-dt-idx="5" tabindex="0"
                  class="page-link">5</a>
              </li>
              <li class="paginate_button page-item ">
                <a href="#" aria-controls="example2" data-dt-idx="6" tabindex="0"
                  class="page-link">6</a>
              </li>
              <li class="paginate_button page-item next" id="example2_next">
                <a href="#" aria-controls="example2" data-dt-idx="7"
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