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
            <h1 class="m-0">${boardVO.board_type} 상세보기 </h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">${boardVO.board_type} 게시물관리</li>
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
        <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">보기</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <!-- enctype 필수 없으면 첨부파일이 전송X -->
          <form name="form_view" method="post" action="/admin/board/board_update_form" enctype="multipart/form-data">
            <div class="card-body">
              <div class="form-group">
                <label for="exampleInputEmail1">제목</label>
                <br>
                ${boardVO.title}
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">내용</label>
                <br>
                ${boardVO.content}
              </div>
              
              <div class="form-group">
                <label for="exampleInputPassword1">작성자</label>
                <br>
                ${boardVO.writer}
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">조회수</label>
                <br>
                ${boardVO.view_count}
              </div>
              <div class="form-group">
                <label for="exampleInputPassword1">작성일</label>
                <br>
                <fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss.SSSS" value="${boardVO.reg_date}"/>
              </div>
              <div class="form-group">
                <label for="exampleInputFile">첨부파일</label>
                <c:forEach begin="0" end="1" var="idx">
                
                <c:if test="${boardVO.save_file_names[idx] != null}">
                <div class="input-group">
                  <div class="custom-file">
                  	<!-- 첨부파일을 URL로 직접접근하지 못하기 때문에 다운로드전용 메소드생성 -->
                  	<!-- 첨부파일을 URL로 직접접근하지 못하기 때문에 컨트롤러로만 접근이 가능(다운로드전용 메소드 생성) -->
                    <a href="/download?save_file_name=${boardVO.save_file_names[idx]}&real_file_name=${boardVO.real_file_names[idx]}">
                    ${boardVO.real_file_names[idx]}
                    </a> 
                    <!-- 
                    JSTL에서 변수 사용하기 fn:split() 목적: 확장자를 이용해서 이미지 미리보기를 할 건지 결정 img태그사용
                    String[] fileNameArray = String.split('변수값', '분할기준값');
                     -->
                    <!-- ↓ 변수할당 ↓ -->
                    <c:set var="fileNameArray" value="${fn:split(boardVO.save_file_names[idx], '.')}"/>
                    <!-- ext(end)Name 사용법 : 그림판.얼굴.jpg -> [그림판, 얼굴, jpg]에서 마지막 인덱스의 값을 가져옴 -->
                    <c:set var="extName" value="${fileNameArray[fn:length(fileNameArray)-1]}" />
                    
                    <!-- ↓ switch case문 ↓ -->
                    <!-- containsIgnoreCase(); googling -->
                    <!-- containsIgnoreCase('찾을값의 문장', '비교기준값');  -->
                    <c:choose>
                    	<c:when test="${fn:containsIgnoreCase(checkImgArray, extName)}">
                    	<img src="/image_preview?save_file_name=${boardVO.save_file_names[idx]}" style="width:100%;">
                    	</c:when>
                    	<c:otherwise>
                    	<!-- 아무의미 없음 개발연습용 -->
                    	<c:out value="${checkImgArray}" /> 이미지가 아님
                    	</c:otherwise>
                    </c:choose>
                  </div>
                </div>
                </c:if>
                
                </c:forEach>
              </div>
              
            </div>
            <!-- /.card-body -->

            <div class="card-footer text-right">
              <button type="submit" class="btn btn-primary">수정</button>
              <button type="button" class="btn btn-danger" id="btn_delete">삭제</button>
              <button type="button" class="btn btn-default" id="btn_list">목록</button>
            </div>
            <input name="page" value="${pageVO.page}" type="hidden" >
            <input name="search_type" value="${pageVO.search_type}" type="hidden" >
            <input name="search_keyword" value="${pageVO.search_keyword}" type="hidden" >
            <input name="bno" value="${boardVO.bno}" type="hidden" >
          </form>
          	<input type="text" value="${boardVO.board_type}">
        </div>
        <!-- 콘텐츠 내용 END  -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>

  <!-- /.content-wrapper -->


<%@ include file="../include/footer.jsp" %>
<script>
$(document).ready(function(){
	var form_view = $("form[name = 'form_view']");
	$("#btn_list").click(function(){
		form_view.attr("action", "/admin/board/board_list");
		form_view.attr("method", "get"); // form_view의 기본 메소드를 post -> get
		form_view.submit();
	});
	
	$("#btn_delete").click(function(){
		if (confirm('정말로 삭제하시겠습니까?')) { // Yes를 클릭하면 구현내용실행
			form_view.attr("action", "/admin/board/board_delete");
			form_view.submit();	
		}
	});
});
</script>
