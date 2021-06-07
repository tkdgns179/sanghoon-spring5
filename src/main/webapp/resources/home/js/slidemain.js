var slideNum = 0; // 슬라이드 인덱스 초기값 선언
var slideAuto = null; // null : 저장 공간은 있으나 값이 없음 // var str = ''; 값이 공백인 것
// on|off 이런 역할을 하는 변수를 switch|flag variable
// 함수 선언
function play_w(directw) { // 좌우로 슬라이드 되는 함수 선언
    // 아래 3개의 조건 중에 1개는 무조건 실행되는 구조
    if (directw == 'right') {
        slideNum += 1; // 슬라이드 번호 증가
        if (slideNum > 2) { slideNum = 0; } // 슬라이드 이미지가 3개일 때로 고정
    }
    else if (directw == 'left') {
        slideNum -= 1; // 슬라이드 번호 감소
        if (slideNum < 0) { slideNum = 2; } // 순서대로 무한 반복을 명시
    }
    else {
        slideNum = directw; // string으로 형 변환
    }

    // li tag에서 클래스가 seq인 것을 each함수로 길이 만큼 반복
    // 결과는 작은 점 원으로 바꾸는 명령
    $('.rollingbtn').find('li.seq a').each(function () {
        $('li.seq a img').attr('src', $('li.seq a img').attr('src').replace('_on.png', '_off.png'))
    });
    // 아래 결과는 현재 선택된 슬라이드 이미지(slideNum에 의한) _on.png(타원형 이미지)로 바꾸는 명령
    $('li.butt' + slideNum + ' a img').attr('src', $('li.butt' + slideNum + ' a img').attr('src').replace('_off.png', '_on.png'));
    // slideNum조건 실행 세 가지 중 한 가지는 무조건 실행
    if (slideNum == 0) {
        // 슬라이드 인덱스가 0번 일 때 다른 li tag를 1초간 서서히 투명도를 0으로 처리
        $('.viewImgList li.imglist0').animate({ 'opacity': 1 }, 1000); //서서히 나타나기
        $('.viewImgList li.imglist1').animate({ 'opacity': 0 }, 1000);
        $('.viewImgList li.imglist2').animate({ 'opacity': 0 }, 1000);
    }
    else if (slideNum == 1) {
        $('.viewImgList li.imglist0').animate({ 'opacity': 0 }, 1000);
        $('.viewImgList li.imglist1').animate({ 'opacity': 1 }, 1000); //서서히 나타나기
        $('.viewImgList li.imglist2').animate({ 'opacity': 0 }, 1000);
    }
    else if (slideNum == 2) {
        $('.viewImgList li.imglist0').animate({ 'opacity': 0 }, 1000);
        $('.viewImgList li.imglist1').animate({ 'opacity': 0 }, 1000);
        $('.viewImgList li.imglist2').animate({ 'opacity': 1 }, 1000); //서서히 나타나기
    }
    //  플래그(깃발) true(자동 슬라이드), false(자동 슬라이드 멈춤)
    if (slideAuto) { // true일 때 
        clearTimeout(slideAuto); // play_w 함수 실행 중지함
    }
    slideAuto = setTimeout('play_w("right")', 3000); // 3초 단위로 play_w('right') 실행
} // play_w 함수 END

