<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<!-- 게시판용 css import -->
<link rel="stylesheet" href="/resources/home/css/board.css">

        <!-- 메인 콘텐츠 영역만 변경됩니다 -->
        <div id="container">
        
            <!-- 메인상단위치표시영역 -->
            <div class="location_area customer">
                <div class="box_inner">
                    <h2 class="tit_page">페이지 오류   <a href="${session_prevPage}" class="btn_baseColor">이전페이지</a></h2>
                    <p class="location">고객센터 <span class="path">/</span> 에러페이지</p>
                    <ul class="page_menu clear">
                        <li style="text-align:left;">
                        ${exception.getMessage()}
                        </li>
                    </ul>
                </div>
            </div>	
            <!-- //메인상단위치표시영역 -->
    
            <!-- 메인본문영역 START -->
            <div class="bodytext_area box_inner" style="text-align: left; padding:10px 40px">
            	<c:forEach var="stack" items="${exception.getStackTrace()}">
            		<li>
            			${stack.toString()}
            		</li>
            	</c:forEach>
			</div>
			<!-- 메인본문영역 END-->
		</div>
	<!-- 메인콘텐츠영역 END -->
<%@ include file="../include/footer.jsp"%>