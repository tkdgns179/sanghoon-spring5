<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/header.jsp" %>
<!-- 게시판용 CSS -->
<link rel="stylesheet" href="/resources/home/css/board.css">
<!-- html5가 아닌 JS로 유효성 검사 core import -->
<script src="/resources/home/js/jquery.validate.js"></script>
<script src="/resources/home/js/additional-methods.js"></script>
<style>
    /* 모바일용 게시판 스타일 */
    .radio_area {
    	box-sizing: border-box;
        padding: 10px;
        
    }
    .gender {
            padding: 4px 10px;
            font-size: 14px;
    }
    /* 테블릿용 메인페이지 스타일 지정 801px ~ 무한대까지 재정의*/
    @media all and (min-width : 801px) {}

    /* PC용 메인페이지 스타일 지정 1066px ~ 무한대까지 재정의*/
    @media all and (min-width : 1066px) {}
</style>
<script>
    // 회원가입 전용 유효성 검사 부분
    $(document).ready(function () {
        $('.appForm').validate({
            rules : {
                password : "required", // password 인풋태그의 이름, 입력값 공백체크
                password : {            // password_chk 이풋태그이 이름
                    equalTo: '#password_lbl' // input 태그의 id값과 비교
                }
            }
        });
        $.extend($.validator.messages, {
            required : "필수 항목입니다.",
            email : "유효하지 않은 Email 주소 입니다.",
            digits : "숫자만 입력 가능합니다.",
            equalTo : "비밀번호가 일치하지 않습니다.",
        });        
    });
</script>
        <!-- 메인 콘텐츠 영역만 변경됩니다 -->
        <div id="container">
            <!-- 메인상단위치표시영역 -->
            <div class="location_area customer">
                <div class="box_inner">
                    <h2 class="tit_page">스프링 <span class="in">in</span> 자바</h2>
                    <p class="location">고객센터 <span class="path">/</span> 회원가입</p>
                    <ul class="page_menu clear">
                        <li><a href="#" class="on">회원가입</a></li>
                    </ul>
                </div>
            </div>	
            <!-- //메인상단위치표시영역 -->
    
            <!-- 메인본문영역 -->
            <div class="bodytext_area box_inner">
                <!-- 폼영역 -->
                <form method="POST" name="join_form" action="/join" class="appForm">
                    <fieldset>
                        <legend>회원가입폼</legend>
                        <p class="info_pilsoo pilsoo_item">필수입력(회원가입 후 관리자가 승인해야만, 로그인이 가능합니다)</p>
                        <ul class="app_list">
                            <li class="clear">
                                <label for="user_id_lbl" class="tit_lbl pilsoo_item">사용자아이디</label>
                                <div class="app_content"><input type="text" name="user_id" class="w100p" id="user_id_lbl" placeholder="사용자아이디를 입력해주세요" required/></div>
                            </li>
                            <li class="clear">
                                <label for="password_lbl" class="tit_lbl pilsoo_item">암호</label>
                                <div class="app_content"><input type="password" name="user_pw" class="w100p" id="password_lbl" placeholder="비밀번호를 입력해주세요" required/></div>
                            </li>
                            <li class="clear">
                                <label for="password_chk_lbl" class="tit_lbl pilsoo_item">비밀번호확인</label>
                                <div class="app_content"><input type="password" name="password_chk" class="w100p" id="password_chk_lbl" placeholder="비밀번호를 다시 입력해주세요" required/></div>
                            </li>
                            <li class="clear">
                                <label for="user_name_lbl" class="tit_lbl pilsoo_item">사용자이름</label>
                                <div class="app_content"><input type="text" name="user_name" class="w100p" id="user_name_lbl" placeholder="사용자이름을 입력해주세요" required/></div>
                            </li>
                            <li class="clear">
                                <label for="email_lbl" class="tit_lbl pilsoo_item">이메일</label>
                                <div class="app_content"><input type="email" name="email" class="w100p" id="email_lbl" placeholder="이메일을 입력해주세요" required/></div>
                            </li>
                            <li class="clear">
                                <label for="point_lbl" class="tit_lbl pilsoo_item">포인트</label>
                                <div class="app_content"><input readonly type="digits" name="point" class="w100p" id="point_lbl" placeholder="포인트를 입력해주세요" value="0" required/></div>
                            </li>
                            <li class="clear">
                                <label for="gender_lbl" class="tit_lbl pilsoo_item">로그인여부</label>
                                <div class="app_content ">
                                    <input checked value="false" type="radio" name="enabled" class="css-radio" id="enabled_lbl" />
                                    <label for="woman_lbl">금지</label>
                                </div>
                            </li>
                            <li class="clear">
								<label for="gender_lbl" class="tit_lbl pilsoo_item">권한여부</label>
								<div class="app_content radio_area">
									<select name="levels" class="gender" required>
										<option value="ROLE_USER">일반사용자</option>
									</select>
								</div>
							</li>
                            <li class="clear">
                                <label for="comment_lbl" class="tit_lbl pilsoo_item">문의내용</label>
                                <div class="app_content"><textarea name="comment" class="w100p" id="comment_lbl" placeholder="내용을 입력해주세요" required></textarea></div>
                            </li>
                            <li class="clear">
                                <label for="agree_lbl" class="tit_lbl pilsoo_item">개인정보활용동의</label>
                                <div class="app_content checkbox_area"><input type="checkbox" name="agree" class="css-checkbox" id="agree_lbl" required checked/>
                                <label for="agree_lbl" class="agree">동의함</label>
                                </div>
                            </li>
                        </ul>
                        <p class="btn_line">
                        <button class="btn_baseColor" id="btn_insert" disabled style="opacity:0.5;">회원가입</button>
                        </p>	
                    </fieldset>
                </form>
                <!-- //폼영역 -->
            </div>
            <!-- //메인본문영역 -->
        </div>
        <!-- //메인 콘텐츠 영역 -->


<%@ include file="./include/footer.jsp" %>
<script>
$(document).ready(function(){
	$("#btn_insert").click(function(){
		alert("준비중입니다")
	});
	$("#user_id_lbl").on("propertychange change keyup paste input", function(){
		if ($(this).val() != "") {
			$.ajax({
				type: "get",
				url: "/id_check?user_id="+$(this).val(),
				dataType: "text",
				success: function(result) {
					if (result == 0) {
						$("#btn_insert").attr("disabled", false);
						$("#btn_insert").css("opacity", "1");
						$("#msg").remove();
						$("#user_id_lbl").after("<div id='msg' style='color:blue';>사용가능한 ID입니다</div>")
					}
					else { // 중복아이디가 존재할 때 구현내용
						$("#btn_insert").attr("disabled", true);
						$("#btn_insert").css("opacity", "0.5");
						$("#msg").remove();
						$("#user_id_lbl").after("<div id='msg' style='color:red';>중복된 ID입니다</div>")
					}
				},
				error: function() {
					alert("RestAPI서버가 작동하지 않습니다");
				}
			})
		}
		else {
			setTimeout(() => {
			$("#msg").remove();
			}, 400);
		}
	})
});
</script>