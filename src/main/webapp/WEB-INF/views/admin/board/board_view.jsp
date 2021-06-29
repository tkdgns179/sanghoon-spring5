<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../include/header.jsp" %>
<style>
/* 아래 미디어쿼리는 IE10 이상에서만 적용되는 CSS입니다 */
@media screen and (-ms-high-contrast: active), (-ms-high-contrast: none) {
	.ie_only {
		max-height: 500px;
		overflow: auto;
	}
}
</style>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">${boardVO.board_type} 상세보기</h1>
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
        <!-- 콘텐츠 내용 -->
        <div class="card card-primary">
          <div class="card-header">
            <h3 class="card-title">보기</h3>
          </div>
          <!-- /.card-header -->
          <!-- form start -->
          <!-- 첨부파일을 전송할때 enctype=필수 없으면, 첨부파일이 전송X -->
          <form name="form_view" method="get" action="/admin/board/board_update_form" enctype="multipart/form-data">
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
                <fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${boardVO.reg_date}"/> 
              </div>
              <div class="form-group">
                <label for="exampleInputFile">첨부파일</label>
                <div class="input-group">
                <c:forEach begin="0" end="1" var="idx">
	                <c:if test="${boardVO.save_file_names[idx] != null}">
	                  
	                  <div class="ie_only">
	                  
	                  	<!-- JSTL의 c:url 태그로 URL감싸주면 인코딩처리됩니다.(한글이 인코딩이됩니다) -->
	                  	<c:url value="/download" var="url"> 
						   <c:param name="save_file_name" value="${boardVO.save_file_names[idx]}" />
						   <c:param name="real_file_name" value="${boardVO.real_file_names[idx]}" /> 
						</c:url>
						<a href="${url}">
	                  	<!-- 첨부파일을 URL로 직접접근하지 못하기 때문에 컨트롤러로만 접근이 가능(다운로드전용 메서드생성)IE에서 한글쿼리스트링문제때문에 사용X -->
	                    <%-- <a href="/download?save_file_name=${boardVO.save_file_names[idx]}&real_file_name=${boardVO.real_file_names[idx]}"> --%>
	                    ${boardVO.real_file_names[idx]}
	                    </a>
	                    <!-- jstl에서 변수사용하기 fn.split('데이터','분할기준값') 목적: 확장자를 이용해서 이미지 미리보기를 할 건지 결정 img태그사용
	                    	String[] fileNameArray = String.split('변수값','분할기준값');
	                    -->
	                    <c:set var="fileNameArray" value="${fn:split(boardVO.save_file_names[idx],'.')}" />
	                    <!-- 그림판.얼굴.코.JPG = 3개배열, 그림판.jpg = 2개배열 -->
	                    <c:set var="extName" value="${fileNameArray[fn:length(fileNameArray)-1]}" />
	                    <!-- 그림판.얼굴.jpg 파일을 위 변수로 처리시 extName = fineNameArray[2] = jpg -->
	                    <!-- 자바언어로는 switch ~ case문 ~ default -->
	                    <!-- containsIgnoreCase('찾을값의문장','비교기준값') -->
	                    <c:choose>
	                    	<c:when test="${fn:containsIgnoreCase(checkImgArray,extName)}">
	                    		<img src="/image_preview?save_file_name=${boardVO.save_file_names[idx]}" style="width:100%;">
	                    	</c:when>
	                    	<c:otherwise>
	                    		<!-- 아무의미 없이 개발연습용으로  -->
	                    		<c:out value="${checkImgArray}" /> 이미지가 아님.
	                    	</c:otherwise>
	                    </c:choose>
	                  </div>
	                
	                </c:if>
                </c:forEach> 
                </div>              
              </div>
            </div>
            <!-- /.card-body -->

            <div class="card-footer text-right">
              <button type="submit" class="btn btn-primary">수정</button>
              <button type="button" class="btn btn-danger" id="btn_delete">삭제</button>
              <button type="button" class="btn btn-default" id="btn_list">목록</button>
            </div>
            <input name="page" value="${pageVO.page}" type="hidden">
            <input name="search_type" value="${pageVO.search_type}" type="hidden">
            <%-- <input name="search_keyword" value="${pageVO.search_keyword}" type="hidden"> --%>
            <input name="bno" value="${boardVO.bno}" type="hidden">
          </form>
        </div>
        
        <!-- 댓글 입력폼 -->
        <div class="col-md-12">
          <div class="card-default">
            <div class="card-header">
              <h3 class="card-title">댓글 쓰기</h3>
            </div>
            <div class="card-body p-0">
              <div class="bs-stepper linear">
              <div class="bs-stepper-header" role="tablist">
                <div class="line"></div>
              </div>
              <div class="bs-stepper-content">
                <!-- your steps content here -->
                <div id="logins-part" class="content active dstepper-block" role="tabpanel" aria-labelledby="logins-part-trigger">
                <div class="form-group">
                  <label for="replyer">작성자</label>
                  <input type="text" class="form-control" id="replyer" placeholder="작성자를 입력하세요">
                </div>
                <div class="form-group">
                  <label for="reply_text">댓글내용</label>
                  <input type="text" class="form-control" id="reply_text" placeholder="댓글내용을 입력하세요">
                </div>
                </div>
                <div id="information-part" class="content" role="tabpanel" aria-labelledby="information-part-trigger">
                <button type="button" class="btn btn-warning" id="btn_reply_write">댓글등록</button>
                <input type="hidden" value="1" id="reply_page">
                </div>
              </div>
              </div>
            </div>
            <!-- /.card-body -->
            <div class="card-footer">
              아래 댓글리스트 버튼을 클릭하시면 댓글 목록이 출력이 됩니다.
            </div>
            </div>
        </div>
        <!-- //댓글 입력폼 -->
        <!-- 댓글 타임라인 -->
        <div class="col-md-12">
          <!-- The time line -->
          <div class="timeline">
          <!-- timeline time label -->
          <div class="time-label">
            <span class="bg-red" data-toggle="collapse" href="#collapseReply" role="button" id="btn_reply_list">
              댓글리스트
              [<span id="reply_count">${empty boardVO.reply_count? '0': boardVO.reply_count}</span>]
            </span>
          </div>
          <!-- 콜랩스 시작 -->
          <div class="collapse timeline" id="collapseReply">
          <!-- time-label 이후 after요소로 템플릿결과가 여기에 출력됨. -->
          <!-- /.timeline-label -->
          <!-- timeline item -->
          <!-- 댓글리스트를 자바스크립트의 빵틀(템플릿)을 만듭니다.  -->
          <!-- 고전append함수를 사용하지 않고, handlebars라는 확장프로그램(아래) 임포트 -->
          <!-- 장점은 기존 퍼블리셔가 만든태그를 그대로 사용가능 -->
          <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
          <script id="template" type="text/x-handlebars-template">
          {{#each .}}
          <div class="div_template" data-rno="{{rno}}">
            <i class="fas fa-envelope bg-blue"></i>
            <div class="timeline-item">
            <h3 class="timeline-header">{{replyer}}</h3>
            <div class="timeline-body">{{reply_text}}</div>
            <div class="timeline-footer">
              <a class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modal-reply">수정</a>
            </div>
            </div>
          </div>
          {{/each}}
          </script>
          
            <!-- 페이징 처리 -->
            <div class="row">
              <ul class="pagination" style="margin: 0 auto;">
                <!-- <li class="paginate_button page-item previous disabled" id="example2_previous">
                  <a href="#" aria-controls="example2" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
                </li>
                <li class="paginate_button page-item active">
                  <a href="#" aria-controls="example2" data-dt-idx="1" tabindex="0" class="page-link">1</a>
                </li>
                <li class="paginate_button page-item ">
                  <a href="#" aria-controls="example2" data-dt-idx="2" tabindex="0" class="page-link">2</a>
                </li>
                <li class="paginate_button page-item ">
                  <a href="#" aria-controls="example2" data-dt-idx="3" tabindex="0" class="page-link">3</a>
                </li>
                <li class="paginate_button page-item ">
                  <a href="#" aria-controls="example2" data-dt-idx="4" tabindex="0" class="page-link">4</a>
                </li>
                <li class="paginate_button page-item ">
                  <a href="#" aria-controls="example2" data-dt-idx="5" tabindex="0" class="page-link">5</a>
                </li>
                <li class="paginate_button page-item ">
                  <a href="#" aria-controls="example2" data-dt-idx="6" tabindex="0" class="page-link">6</a>
                </li>
                <li class="paginate_button page-item next" id="example2_next">
                  <a href="#" aria-controls="example2" data-dt-idx="7" tabindex="0" class="page-link">Next</a>
                </li> -->
              </ul>
            </div>
            <!-- //페이징 처리 -->
            </div>
            <!-- //콜랩스 끝 -->
          </div>
          <!-- END timeline item -->
        </div>
        <!-- //댓글 타임라인 -->

        <!-- //콘텐츠 내용 -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
 <!-- /.content-wrapper -->
  <div class="modal fade" id="modal-reply">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">작성자명</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <input class="form-control" type="text" name="modal_reply_text" id="modal_reply_text" value="댓글내용 출력">
        </div>
        <div class="modal-footer ">
          <!-- justify-content-between : 양쪽배분정렬 -->
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
          <button id="btn_reply_update" type="button" class="btn btn-primary">수정</button>
          <button id="btn_reply_delete" type="button" class="btn btn-danger">삭제</button>
          <input type="hidden" id="rno" name="rno">
        </div>
      </div>
      <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
  </div>
<%@ include file="../include/footer.jsp" %>
<script>
var printPagingList = function(pageVO, target){
    $(target).html('');
// pageVO = 스프링에서 받는 json데이터, 변수 3개 pageVO.prev(이전 데이터가 있다면 true), pageVO.next(다음 데이터가 있다면 true), pageVO = 5페이지 있다고 가정
    // Previous 출력 ↓
    if (pageVO.prev) {prevlink = '';} else {prevlink = 'disabled';}
    var pagination = ''; // 문자열 누적변수
    pagination += '<li class="paginate_button page-item previous '+prevlink+'" id="example2_previous">';
    pagination += '<a href="'+(pageVO.startPage - 1)+'" aria-controls="example2" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>';
    pagination += '</li>'; // pagination = pagination + '</li>';

    var active = '';
    for (var i=pageVO.startPage; i<=pageVO.endPage; i++) {
        if (i == pageVO.page) {active = 'active';} else {active = '';}
        pagination += '<li class="paginate_button page-item '+active+'"><a href="'+i+'" aria-controls="example2"';
        pagination += 'data-dt-idx="1" tabindex="0" class="page-link">'+(i)+'</a></li>';
    }
    // Next 출력 ↓
    if (pageVO.next) {nextlink = '';} else {nextlink = 'disabled';}
    pagination += '<li class="paginate_button page-item next '+nextlink+'" id="example2_next">';
    pagination += '<a href="'+(pageVO.endPage - 1)+'" aria-controls="example2" data-dt-idx="7" tabindex="0" class="page-link">Next</a></li>';
    
    $(target).append(pagination);
};
// 댓글 리스트 버튼 클릭액션으로 댓글 리스트를 출력
// 성공 후, 가져온 json데이터 template에 파싱
var printReplyList = function(data, templateData, target) {
var template = Handlebars.compile(templateData.html()); // 템플릿을 태그로 변환
var html = template(data); // 파싱처리
$('.div_template').remove(); // 기존 댓글데이터 누적 방지
target.prepend(html);
};
// 함수형 변수로서 댓글 리스트를 RestAPI에서 받아서 출력하는 변수
var replyList = function() {
	var page = $("#reply_page").val();
	$.ajax({
		type: "post",
		url: "/reply/reply_list/${boardVO.bno}/"+page,
		dataType: "json", // 전송받는 데이터 형태 json
		success: function(result){
			if (typeof result == "undefined" || result == null || result == null) {
				$("#collapseReply").empty(); // div태그안의 내용 삭제하기
				$("#collapseReply").html('<div class="pagination justify-content-center"><ul class="pagination pageVO">조회된 값이 없습니다</ul></div>');
			} 
			else {
				// json 데이터를 화면에 파싱합니다 (옛날에는 xml태그를 파싱하였음)
				// #template 빵틀에 result데이터를 바인딩해서 출력
				// json.parse(문자열) -> 일반문자열을 json으로 변경하는 함수
				// json.stringify(json데이터) -> json데이터를 일반문자열로 변경하는 함수
				console.log("여기까지" + JSON.stringify(result.replyList)); // 크롬콘솔에서 확인
				printReplyList(result.replyList, $("#template"), $("#collapseReply"));
				printPagingList(result.pageVO, '.pagination');
			}
		},
		error: function() {
			alert("RestAPI서버가 작동하지 않습니다 다음에 이용해주세요")
		}
	});
}
</script>
<script>
// 댓글 CRUD 처리
$(document).ready(function(){
	// 댓글 모달창 삭제버튼의 액션처리
	$("#btn_reply_delete").click(function(){
		
	});
	// 댓글 모달창 수정버튼의 액션처리
	$("#btn_reply_update").click(function(){
		// RestAPI Endpoint로 보낼 값 지정
		var reply_text = $("#modal_reply_text").val();
		var rno = $("#rno").val();
		if (reply_text == '' || rno == '') {
			alert("댓글내용을 입력해주세요");
			return false;			
		}
		$.ajax({
			type: 'patch', // 컨트롤러의 메소드
			url : '/reply/reply_update',
			dataType : 'text', // RestAPI컨트롤러에서 받는 데이터형식 
			data: JSON.stringify({
				rno : rno,
				reply_text : reply_text,
			}), // 보내는 데이터 형시을 json을 텍스트 형식으로 변경
			headers: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "PATCH"
			}, // json데이터 형식으로 브라우저에 내장된 헤더값을 지정
			success : function(result) { // 댓글입력 성공시 실행 콜백함수
				if (result == "success") {
				alert("댓글을 수정하였습니다")
				$("#modal-reply").modal("hide");
				// 댓글 수정 후 화면에 댓글 목록 출력하는 함수 실행
				replyList(); // 화면의 일부분만 리프레시(재생)
				}
			},
			error : function(){
				alert("RestAPI서버가 작동하지 않습니다 잠시후 이용해주세요")
			},
		});
	});
	
	// 하단 페이지 링크의 속성처리
	$(".pagination").on("click", "li a", function(event){
		event.preventDefault(); // a태그의 링크속성을 사용하지않음
		$("#reply_page").val($(this).attr("href"));
		replyList();
	});
	
	// 댓글 리스트 버튼
	$("#btn_reply_list").click(function(){
		replyList();
	});
	
	// 댓글 등록 버튼
	$("#btn_reply_write").click(function(){
		// RestAPI Endpoint로 보낼 값 지정
		var bno = "${boardVO.bno}"; // 자바변수값
		var reply_text = $("#reply_text").val();
		var replyer = $("#replyer").val();
		if (reply_text == '' || replyer == '') {
			alert("아이디 또는 댓글을 작성해주세요");
			return false;			
		}
		$.ajax({
			type: 'post', // 컨트롤러의 메소드
			url : '/reply/reply_insert',
			dataType : 'text', // RestAPI컨트롤러에서 받는 데이터형식 
			data: JSON.stringify({
				bno : bno,
				reply_text : reply_text,
				replyer : replyer
			}), // 보내는 데이터 형시을 json을 텍스트 형식으로 변경
			headers: {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			}, // json데이터 형식으로 브라우저에 내장된 헤더값을 지정
			success : function(result) { // 댓글입력 성공시 실행 콜백함수
				var reply_count = $("#reply_count").text();
				$("#reply_count").text(parseInt(reply_count) + 1); // 문자를 숫자로 바꿔서 더해줌
				// 댓글을 신규등록후 댓글페이징의 1페이지로 이동
				$("#reply_page").val("1"); // input 히든타입에 value값을 넣어서 이동시킬 예정
				// 댓글입력후 화면에 댓글목록 뿌리는 함수 만들 예정 
			},
			error : function(){
				alert("RestAPI서버가 작동하지 않습니다 잠시후 이용해주세요")
			},
		});
	});
});
</script>

<script>
$(document).ready(function(){
	var form_view = $("form[name='form_view']");//전역변수
	$("#btn_list").click(function(){
		//여기서는 함수내 변수
		form_view.attr("action","/admin/board/board_list");
		form_view.submit();
	});
	$("#btn_delete").click(function(){
		if(confirm('정말로 삭제 하시겠습니까?')) {//Yes를 클릭하면 아래내용 실행
			form_view.attr("action","/admin/board/board_delete");
			form_view.attr("method", "post");
			form_view.submit();
		}
	});
});
</script>
<script>
  // 댓글 리스트에서 수정 버튼 클릭시 현재 선택한 값을 modal창에 보여주는 것을 구현(아래)
  $(document).ready(function(){
      $('.timeline').on("click", '.div_template', function () {
          _text = $.trim($(this).find('.timeline-body').text());
          $('#rno').val($(this).attr('data-rno'));
          $('#modal_reply_text').val(_text);
          $('.modal-title').html($(this).find('.timeline-header').text());
      });
  });
</script>
