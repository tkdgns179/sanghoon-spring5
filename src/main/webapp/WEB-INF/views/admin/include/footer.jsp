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
<!-- **여기까지 footer.jsp영역** -->
    