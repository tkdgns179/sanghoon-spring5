<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<%@ include file="../include/header.jsp"%>

<!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">게시판 뷰/수정</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">게시판생성관리</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- 콘텐츠 내용 -->
        <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">등록</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <!-- 첨부파일을 전송할때 enctype=필수 없으면, 첨부파일이 전송X -->
          <form name="form_write" method="post" action="/admin/bbs_type/bbs_type_update" enctype="multipart/form-data">
            <div class="card-body">
              <div class="form-group">
                <label for="board_type">게시판 타입</label>
                <!-- PK고유키, 식별자는 수정할 수 있으면, 여기 묶여있는 하위 게시물(FK)들은 소속을 잃어버립니다 -->	
                <input readonly value="${boardTypeVO.board_type}" name="board_type" type="text" class="form-control" id="board_type" placeholder="게시판 타입을 입력해주세요" required>
              </div>
              <div class="form-group">
                <label for="board_name">게시판 이름</label>
                <input value="${boardTypeVO.board_name}" name="board_name" type="text" id="board_name" class="form-control" placeholder="게시판 이름을 입력해주세요" required>
              </div>
              <div class="form-group">
                <label for="board_sun">출력 순서</label>
                <input value="${boardTypeVO.board_sun}" name="board_sun" type="text" class="form-control" id="board_sun" placeholder="출력순서를 입력해주세요" required>
              </div>
              
            </div>
            <!-- /.card-body -->

            <div class="card-footer text-right">
              <button type="submit" class="btn btn-primary">수정</button>
              <button type="button" class="btn btn-danger" id="btn_delete">삭제</button>
              <a href="/admin/bbs_type/bbs_type_list" class="btn btn-default">목록</a>
            </div>
          </form>
        </div>
        <!-- //콘텐츠 내용 -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.board_name -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="../include/footer.jsp"%>
<script>
$(document).ready(function(){
	$("#btn_delete").click(function(){
		// alert("버튼클릭");
		if(confirm("정말로 삭제하시겠습니까?")){
			var form_write = $("form[name = form_write]");
			form_write.attr("action", "/admin/bbs_type_delete");
			form_write.submit();
		}
	});
});
</script>