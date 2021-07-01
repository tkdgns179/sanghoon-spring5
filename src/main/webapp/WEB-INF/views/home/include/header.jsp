<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title> 스프링 </title>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- jQuery core import -->
    <script src="/resources/home/js/jquery-3.6.0.js"></script>
    <!-- 상단 바로가기 링크시 부드럽게 상단으로 이동하는 js import -->
    <script src="/resources/home/js/jquery.smooth-scroll.min.js"></script>
    <!-- 
        화면을 초기화 시키는 reset style import : cross browsing을 처리하기 위해
        Chrome, IE, Edge, Safari, Fire Fox의 h1, p, ul, div.. 의 태크 영역이 조금씩 틀림
        작업한 결과가 모든 브라우저(크로싱 브라우저)에 똑같이 보이게 하기위한 reset.css 
    -->
    <link rel="stylesheet" href="/resources/home/css/reset.css">
    <!-- 여기서 부터는 사용자 정의형 스타일 + 스크립트 추가(아래) -->
    <!-- mobile core import -->
    <link rel="stylesheet" href="/resources/home/css/mobile.css">
    <!-- tablet core import -->
    <link rel="stylesheet" href="/resources/home/css/tablet.css">
    <!-- pc core import -->
    <link rel="stylesheet" href="/resources/home/css/pc.css">
    

    <!-- main(mobile) js core import -->
    <script src="/resources/home/js/main.js"></script>
    <!-- mainslide.js core import  -->
    <script src="/resources/home/js/slidemain.js"></script>

    <style>
    /* 테블릿용 메인페이지 스타일 지정 801px ~ 무한대까지 재정의*/
    @media all and (min-width : 801px) {
    
    }

    /* PC용 메인페이지 스타일 지정 1066px ~ 무한대까지 재정의*/
    @media all and (min-width : 1066px) {
        
    }
    </style>
    <script>
    // 공통으로 사용하는 변수: 로그인 성공 메세지, 게시물 등록/성공/삭제 성공메세지
    if ("${msg}" != "") {
    	alert("${msg}이(가) 성공하였습니다!")
    }
    </script>
    
    
</head>

<body>
    <!-- 헤더에서푸터까지 -->
    <div id="wrap">
        <!-- 헤더상단메뉴영역영역 -->
        <header id="header">
            <div class="header_area box_inner clear">
                <!-- 상단로고영역 -->
                <h1><a href="/">스프링 in 자바</a></h1>
                <!-- //상단로고영역 -->

                <!-- 상단메뉴메뉴영역 -->
                <p class="openMOgnb">
                    <a href="#">
                        <b class="hdd">메뉴열기</b>
                        <span></span><span></span><span></span>
                    </a>
                </p>
                <div class="header_cont">
                    <ul class="util clear">
                    <c:choose>
                    	<c:when test="${session_enabled eq 'true'}">
	                        <!-- 로그인 후 보이는 메뉴(아래) -->
	                        <li><a href="#">${session_username}님 환영합니다.</a></li>
	                        <li><a href="/logout">로그아웃</a></li>
	                        <li><a href="/member/mypage_form">마이페이지</a></li>
	                        <c:if test="${session_levels eq 'ROLE_ADMIN'}">
	                        <li><a href="/admin">AdminLTE</a></li>
	                        </c:if>
                        </c:when>
						<c:otherwise>
							<li><a href="/login_form">로그인</a></li>
	                        <li><a href="/join_form">회원가입</a></li>
						</c:otherwise>                    	
                    </c:choose>
                    </ul>
                    <nav>
                        <ul class="gnb clear">
                            <li><a href="board_list.html" class="openAll1">샘플홈페이지</a>

                                <div class="gnb_depth gnb_depth2_1">
                                    <ul class="submenu_list">
                                        <li><a href="board_list.html">반응형홈페이지</a></li>
                                    </ul>
                                </div>
                            </li>
                            <li><a href="board_list.html" class="openAll2">커뮤니티</a>
                                <div class="gnb_depth gnb_depth2_2">
                                    <ul class="submenu_list">
                                        <li><a href="board_list.html">공지사항</a></li>
                                        <li><a href="board_list.html">갤러리게시판</a></li>
                                    </ul>
                                </div>
                            </li>
                        </ul>
                    </nav>
                    <p class="closePop"><a href="javascript:;">닫기</a></p>
                </div>
                <!-- //상단메뉴메뉴영역 -->
            </div>
        </header>
        <!-- //헤더상단메뉴영역영역 -->
    