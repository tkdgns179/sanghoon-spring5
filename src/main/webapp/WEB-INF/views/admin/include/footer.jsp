<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- **이후 footer.jsp영역** -->
<!-- /.content-wrapper -->

  <footer class="main-footer">
    <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong>
    All rights reserved.
    <div class="float-right d-none d-sm-inline-block">
      <b>Version</b> 3.1.0
    </div>
  </footer>


  <!-- Control Sidebar 오른쪽 바둑판 메뉴 클릭시 나오는 내용 -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- demo.js에서 출력할 내용이 존재, demo.js 사용안하고 직접만듦 -->
    <div class='text-center mt-4'>
      <h5>로그아웃</h5><hr class="mb-2"/>
      <button type="button" class="btn btn-primary" id="btn_logout">로그아웃</button>
    </div>
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery Core -->
<script src="/resources/admin/plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 Core -->
<script src="/resources/admin/plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="/resources/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Summernote -->
<script src="/resources/admin/plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars 왼쪽 스크롤 메뉴 코어 -->
<script src="/resources/admin/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="/resources/admin/dist/js/adminlte.js"></script>
<!-- AdminLTE for demo purposes 오른쪽 메뉴 코어 -->
<!-- <script src="/resources/admin/dist/js/demo.js"></script> -->
</body>
</html>
<style>
.sidebar-dark-primary .nav-sidebar>.nav-item>.nav-link.active, .sidebar-light-primary .nav-sidebar>.nav-item>.nav-link.active
{
background-color: #fff;
color: #000;
}
</style>
<!-- **여기까지 footer.jsp영역** -->
 <script>
 // 로그아웃 버튼처리
 $("#btn_logout").click(function(){
	location.replace('/logout'); // security-context.xml에  설정된 /logout 호출 
 });
 
 // 왼쪽 메뉴 선택시 active(bootstrap클래스)를  동적으로 추가하는 코드
 $(document).ready(function(){
	// 현재 선택한 url값을 기준으로 삼습니다
	var current = location.pathname;
	var current2 = current.split("/")[2]; // 위 current 변수값을 /로 분해해서 배열로 만든후 3번째 인덱스값을 추출
	// 제이쿼리의 반복문 each 
	$(".nav-treeview li a").each(function(){ // indexOf 찾는 문자열이 없으면 -1을 리턴합니다
		if($(this).attr('href').indexOf(current2) != -1) { // URL에서 current2와 같은값이 있으면
			if(current2 != "board") { // 게시물관리 메뉴만 제외하고, 다른메뉴에 적용 코드
				$(this).addClass("active");
			}
		}
		else {
			$(this).removeClass("active");
		}
	});
	
 });
 
 
 </script>