$(document).ready(function(){
    $('.to_top a').smoothScroll();

    $('.openMOgnb').click(function(){
        // debug : alert('메뉴 보이기 클릭 액션');
        // $('.header_cont').css('display', 'block')
        $('.header_cont').slideDown("slow");
        $('#header').addClass('on');
    });
    $('.closePop').click(function(){
        // $('.header_cont').css('display', 'none')
        $('.header_cont').slideUp("fast");
        $('#header').removeClass('on');
    });
});

// PC용 대메뉴 마우스 아웃시 조건에 맞으면, 서브메뉴 사라지는 액션
var isOver1 = false; // 대메뉴1이 false일 떄 서브메뉴가 사라지는 조건
var isOverSub1 = false; // 서브메뉴1번이 false일 때 서브메뉴가 사라지는 조건
// 1번 서브메뉴를 숨기는 함수↓
function goHide1() {
    if (!isOver1 && !isOverSub1 ) { // 2개의 변수값이 false일 때 구현
        $('.gnb_depth2_1').stop().fadeOut('fast')
    }
}
var isOver2 = false; // 대메뉴2번이 false일 때 서브메뉴가 사라짐
var isOverSub2 = false; // 서브메뉴2번이 false일 때 서브메뉴가 사라지는 조건
// 2번 서브메뉴를 숨기는 함수
function goHide2() {
    if (!isOver2 && !isOverSub2) {
        $('.gnb_depth2_2').stop().fadeOut('fast')
    }
}
// PC용 대메뉴 마우스 롤오버 이벤트 만들기

$(document).ready(function(){
    // 대메뉴1 액션
    $('.openAll1').mouseover(function(){
        if ( parseInt($('header').css('width')) >= 1049 ) {
            $('.gnb_depth2_1').stop().fadeIn('slow'); // 서브메뉴 1번이 서서히 나타나게 처리
        }
        isOver1 = true; // false일 때 숨기는 조건 <-> true일 때 나타나는 조건
    });
    $('.openAll1').mouseout(function(){
        isOver1 = false;
        setTimeout('goHide1()', 200); // 기본은 goHide1() 이것만 하면 되는데, mouseout이벤트를 놓치면 안되기 때문에
    });
    // 서브메뉴1 액션
    $('.gnb_depth2_1').mouseover(function(){
        isOverSub1 = true; // 서브메뉴 1번이 계속 나타남
    });
    $('.gnb_depth2_1').mouseout(function(){
        isOverSub1 = false;
        setTimeout('goHide1()', 200);
    });
    

    // 대메뉴2 액션
    $('.openAll2').mouseover(function(){
        if ( parseInt($('header').css('width')) >= 1049 ) {
            $('.gnb_depth2_2').stop().fadeIn('slow'); // 서브메뉴 1번이 서서히 나타나게 처리
        }
        isOver2 = true; // false일 때 숨기는 조건 <-> true일 때 나타나는 조건
    });
    $('.openAll2').mouseout(function(){
        isOver2 = false;
        setTimeout('goHide2()', 200); // 기본은 goHide1() 이것만 하면 되는데, mouseout이벤트를 놓치면 안되기 때문에
    });
    // 서브메뉴2 액션
    $('.gnb_depth2_2').mouseover(function(){
        isOverSub2 = true; // 서브메뉴 1번이 계속 나타남
    });
    $('.gnb_depth2_2').mouseout(function(){
        isOverSub2 = false;
        setTimeout('goHide2()', 200);
    });
});