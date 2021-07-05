#### 작업예정.
- 정방향개발(앞으로): 매퍼쿼리>DAO>Service>[JUnit-IoC,DI기능구현]컨트롤러클래스>JSP
- 역방향개발(클래스간이동빨라서): 정방향으로 개발한 것 검증용만 사용.
- ========= 3주간 작업내역  시작(관리자단-제일 손이 많이감) =========
- 관리자단 회원목록 처리 마무리(1.페이징및 검색기능구현)OK.
- model을 이용해서 결과를 JSP로 구현.(2.JSP화면은 표준언어인 JSTL로 구현)OK.
- 나머지 관리자 회원관리 CRUD 화면 JSP처리OK.
- [공지]06-17 목요일(4교시) UI 디자인 시험 있습니다.(화면기획서XLS제작, 화면설계서PPT제출용)OK.
- 관리자단 게시판 생성관리 CRUD 처리.(3.AOP기능구현)OK.
- 관리자단 게시물관리 CRUD 처리(4.파일업로드구현,5.트랜잭션구현).
- ---------------------
- 관리자단 댓글 CRUD 처리(6.RestAPI기능구현-개발트렌드).
- 관리자단 왼쪽메뉴 UI 메뉴 고정시키기(7.jQuery로 구현).
- 사용자단 로그인 화면 JSP로 만들기.
- 로그인처리 및 관리자 권한체크 기능 추가(8.스프링시큐리티구현).
- ======== 3주간 작업내역 끝(07.02금) ===================
- ======== 2주간 작업내역 시작 (사용자단은 관리자단 로직을 사용합니다.)========
- 사용자단 회원가입, 수정, 탈퇴 JSP기능 추가.
- 사용자단 게시판 CRUD 처리.
- 사용자단 댓글 CRUD 처리.
- 헤로쿠 클라우드에 배포(9.클라우드 배포CI/CD구현-개발트렌드).깃(최신소스)-연동-헤로쿠(배포)
- 문서작업(제출용)
- [실습시간이 가능하다면: 알고리즘 다이어그램기반으로 자바코딩테스트]
- [실습시간이 가능하다면: 사용자단 네이버아이디로그인 처리(10.외부RestAPI구현).]
- ======== 2주간 작업내역 끝(07.16금) ===================
- 헤로쿠 클라우드에 배포할때, 매퍼폴더의 mysql폴더내의 쿼리에 now()를 date_add(now(3), interval 9 HOUR) 변경예정.(이유는 DB서버 타임존 미국이기 때문에)

#### 20210525(화) 작업
- 자바 기초 실습
- 자바 .java클래스파일을 컴파일해서 생성된 .class파일을 실행하는 구조.
- 파이썬/자바스크립트는 인터프리터 방식을 실행
- 자바스크립트는 함수구조의 프로그래밍
- 자바는 객체지향(Object Oriented Program) 프로그래밍
- Object(객체) = 실행가능한 Class(클래스)
- 아스키, 유니코드(UTF-8 : UnicodeTypeFormat - 8 bit)
- 아스키코드 IoT에서 데이터 전송 시 필수로 확인해야합니다. 0을 전송 -> 48값을 받습니다.
- IoT 프로그램시 if(var1 == 48) {구현내용}
- 공유기가 하위로 가질 수 있는 사설IP는 공유기 자기 IP + 255개(여유분) = 256개 인터넷이 가능.
- 영어인코딩은 아스키코드로 다 표현이 가능  ASCII
- 한글 인코딩, 영어 인코딩, 중국어/일본어 인코딩...등등은 아스키코드로 다 표현 못함 그래서, 유니코드 등장.UTF-8
- Hex (16진수), Dec (10진수), Char(문자) = 127개 = 아스키문자의 크기 (컴문자 - 사람문자 1:1 매칭)
- Oct (8진수), Bit(2진수)
- ASCII코드 7비트코드 (127글자) -> ANSI코드 8비트(256글자) -> Unicode(65536글자) - UTF-8
- UTF8mb4(이모지까지 포함 : 이모티콘 + 이미지)
- 자바언어를 한다는 것은 컴파일  후 실행이 된다는 의미. -> 실습예정
- 자바스크립트(or 파이썬)는 그냥 실행해서 프로그램이 만들어 집니다. -> 실습예정
- 스프링 MVC 프로젝트 : MVC(Model View Controller) 웹 프로그램 구조(Architecture)
- src/test/java 폴더 : 테스트 작업은 여기서 하라는 약속
- src/main/java 폴더 : 진짜 프로그램 작업을 하는 폴더
- javac HelloWorld.java -encoding UTF-8 (한글 내용도 컴파일됨)
- ↑ java compiler로  실행한 경로가 -> HelloWorld.class 실행파일이 생성됨.
- \*주의 클래스파일은 실행 패키지의 루트(최상위)에서 실행해야 함.
- kr.or.test패키지 root폴더 src/test/java 폴더에서  실행해야함
- java kr.or.test.HelloWorld 클래스를 실행하게 됨
- 이클립스 나오기 전, 25년전 javac로 직접 컴파일해서 class프로그램을 만들었음
- 지금은 터미널에서 javac 사용하지 않고, 이클립스에서 바로 실햅합니다.
- javac : 자바앱 컴파일러 -> 실행파일을 만듦 .class (바이트코드) (자바환경 JVM)
- 메이븐(Maven) : 웹 프로그램 컴파일 -> 웹앱 실행파일을 만듦 .war (Tomcat에서 실행)
- 메이븐이 컴파일할 때, 자바 소스만 컴파일 하는 것이 아니고, 외부 라이블러리도 가져와서 바인딩(묶어줌)하게 됨 = 패키징이라고 합니다. = .war파일로 패키징 됨.
- 메이븐이 관리하는 외부 라이브러리파일(lib) 목록을 저장하는 파일 pom.xml 입니다.
- pom.xml에서 자바버전을 1.6 -> 1.8 변경 후 메이븐을 업데이트 합니다.
- 외부 라이브러리를 참조 하는 방식 (dependency)
- jar파일 : javaARchive = jar 자바클래스를 패키징한 파일입니다.

#### 20210526(수) 작업 
- 클래스(붕어빵틀) → 객체(붕어빵)
- 자바는 클래스를 실행 가능하게 하는 객체지향 프로그래밍 OOP(Object Oriented Programing)
- 객체 = 오브젝트(Object) = 인스턴스(instance) = 실행가능한 클래스
- 객체(Object), 클래스(Class) : 클래스형 자료를 실행 가능하게 만든 것을 Object(=instance)
- 추상화 (Abstract) : 오프라인 업무 -> 대표 업무만  뽑아낸 클래스 = 추상클래스
- 오버라이드 : 상속받아서 재정의 = 메소드 오버라이드 -> 다형성 구현함
- 클래스 구성요소 : 멤버변수 + 멤버메서드(실행) + 서브클래스
- 변하지 않는 수 = 상수 e.g) PI = 3.141569..
- 보통 상수 변수는 클래스 상단에 위치합니다.
- 기본형(정수자료형 \*소문자시작) : byte < short < int < long // e.g) 10L(롱타입 변수)
- 기본형(실수자료형 - 소수점 \*소문자시작) : float < double
- // e.g) 12.34f (실수형 숫자)
- 기본형(문자형 - '1개의 문자' 단따옴표로 값을 입력해야 오류가 안남 *소문자시작) : char 1개의 문자만 입력 cf) char a = 'ab' //에러
- 문자형에서 유니코드 \u숫자 입니다\
- 기본형(불리언형- 참, 거짓) : boolean, (0|1)
- 참조형(대문자로 시작) : 참조형 변수의 대표적인 변수 클래스입니다.
- 클래스 변수(저장) -> 실행가능하게 되었을 때, 인스턴스 변수라고 함(메모리에 로드된 상태)
- 인스턴스라는 말보다는 오브젝트라는 말을 더 많이 사용합니다.
- String(문자열 클래스 변수) : 대문자로 시작함
- 상수 변수 명시적 : final이라는 예약어를 사용 e.g) final int MAX = 100; // 변수명은 대문자로 사용하는 것이 관례
- for-each 반복문 전까지 실습

#### 20210527(목) 작업예정
- 3장 객체와 클래스 부터 시작
- 스프링에서는 상속 extends 보다는 interface/implements를 더 많이 사용합니다.
- abstract클래스 (추상클래스) : 구현내용이 없이, 메소드명만 있는 클래스.
- 메서드명만 있기(구현내용이 없기) 떄문에, 객체로 만들 수 없는 클래스 입니다.
- 부모로서 자식에게 상속만(메소드 이름만)해서 재사용만 가능하게 됩니다.
- 객체로 만들 수 없다 -> 실행가능한 클래스로 사용 못하지만, 프로그램을 구조화 시킬 때 필요.  
↳tep1 aaa = new Step1(); // 이렇게 생성자로 객체를 만들지 못함
- final class : 부모가 될 수 없는 클래스(상속해서 재사용이 불가능한 클래스)
- 접근제어자 : public(패키지위치에 상관없이 접근 - 제일 많이 사용
- public 클래스안에서 멤버변수는 private을 제일 많이 사용 (private 보안성을 갖춤)
- public 클래스안에서 멤버메소드는 public을 많이 사용 (외부 다른 클래스의 메소드를 사용하기 위해서) 대신, 변수가  private이기 때문에, 직접수정이 안되고 실행만 됩니다.
- Instance (인스턴스) /클라우드 = Object (객체) /자바 = 실행중인 클래스
- 클래스는 자료형(e.g. int, long, String)은 멤버변수, 멤버메소드, 서브클래스, 등등 포함할 수 있습니다. ex) C언어의 구조체 자료형
- 이클립스에서 커밋, 푸쉬가 안 될때 커밋 하단에 Force 항목 체크하고 커밋, 푸쉬 진행

#### 202120528(금) 작업
- 오라클11g ExpressEdition 설치예정. OracleXE112_Win64
- SQL Developer 다운받아서 압축해제 -> ERD 제작할 예정.
- Structured Query Language : 구조화된 질의 언어
- Query String (질의 문자열) : URL에서 전송하는 값을 문자열로 저장한 내용 e.g.) ?로 시작, 데이터베이스서버에 질의
- CLI(Command Line Interface) : SQL*PLUS 터미널화면으로 SQL Query X
- GUI(Graphic User Interface) : SQL Developer 윈도우화면에서 에디터에서  SQL Query 실행 O
- SQL Developer 프로그램으로 ERD로 프로젝트 구조제작예정
- 수업시작전, Step2 클래스에서 MeberService 객체를 직접 접근할 때 static 메서드 (컴파일시 실행가능 = 메모리 로딩)로 변경되는 원리 설명
- Entity Relation Diagram : 테이블관계도형 (아래)
- 프로젝트 진행 : 발주(클라이언트) -> 수주(개발사) -> 디자인UI(Client-Developer) -> ERD(이사|팀장) -> 코딩시작(ERD를 토대로 UI 소스 프로그램 입히기) 
- ------------------------------------------------------------------------------------------
- ERD에서 SQL Query 생성 -> 물리테이블을 생성
- 첨부파일(자식) -> 게시판테이블(부모) <- 댓글테이블(자식)
- memberService 객체를 이용해서 메소드에 접근할 때는 (런타임시or 호출시 실행이 가능 = 메모리에 로딩됨)
- 자바는 Garbage Collector가 힙영역에 사용하지 않는 객체를 지워줌
- 스프링 특징 3가지 (IoC, AOP, DI)
- class A extends B 에서 this => A(자식클래스), super => B(부모클래스)
- 추상클래스 실습은 오늘, 인터페이스 실습은 스프링에서
- 다형성(Polymorphism) : 메서드 @Override (상속), 메소드 오버로드 : 함수명은 같으나 인자의 자료형 or 매개변수의 수가 다름

#### 20210531(월) 작업예정
- 토드(SQL Developer와 비슷한 상용프로그램)에서는 버튼으로 포워드엔지니어링이 처리
- 무료인 SQL Developer에서는 버튼X. DDL(Data Definition Language)문을 실행해서 포워드엔지니어링을 처리.
- 참고, MySQL용 워크벤치는 무료이지만, 버튼으로 포워드엔지니어링이 가능.
- SQL쿼리는 3가지로 분류
- DDL문 : Data Definition Language 	데이터 모델 정의언어 e.g. 	CREATE table 
- DCL문 : Data Control Language 		데이터 제어언어		 	COMMIT, ROLLBACK
- DML문 : Data Manipulation Language 데이터 조작언어 			(SELECT, INSERT, UPDATE, DELETE) CRUD쿼리
- ERD 그림만든 것을 물리테이블로 만드는것 = FORWARD ENGENEERING
- 데이터 딕셔너리를 모델과 동기화: 자료사전(데이터의 데이터)을 DB테이블과 동기화
- 데이터 딕셔너리는 메타 데이터와 동일합니다.: 콘텐츠X, 구조 그리고 구성정보만 존재.
- 물리테이블 ERD 그림으로 만드는 것 = REVERSE ENGENEERING (기존 DB분석)
- Spring security 2단계 구현예정.
- 1. 로그인인증(ENABLED): AUTHENTICATION (로그인X, 글쓰기 기능X, 관리자X)
- 2. 권한체크(LEVELS) : AUTHORITY (관리자-admin URL 접근가능, 일반회원은 사용자 홈페이지에서 공지사항X 갤러리만 사용가능합니다.)
- 1:N관계 = 게시물 하나에 댓글 0개 or N개 달릴 수 있음.
- E-R 다이어그램은 그림일뿐, 물리DB(테이블)은 ERD기준으로 생성가능
- PK를 AI(Auto Increment 자동증가)로 만들기: 오라클(sequence 객체로 기능구현), MySQL은 (AI라는 필드속성으로 처리)
- 부자의 관계는 부모의 PK를 기준, 자식에게는 FK(Foreign Key)로 관계를 맺습니다.
- 게시판타입이 notice, gallery 2가지가 존재한다면~
- ~게시물은  notice, gallery 게시판이 아니면,개발자가 예외처리를 하지 않아도 다른 게시판으로 등록하는 상황이 발생되지 않게됨 <Relation 관계가 필요한 이유>
- 데이터 무결성을 유지하는 역할(FK관계)
- ERD에서 Relation 생성: 게시판타입 테이블(=부)과 게시물관리 테이블(=자)의 관계를 생성
- 핃드 데이터형3 : Timestamp(시간도장): 년월일시분초밀리초, Date: 년월일
- 필드 데이터형2 : CLOB(CHAR LONG BYTE) 글 내용이 많을 때 사용하는 데이터 형태
- 필드 데이터형: VARCHAR2 (2바이트를 1글자-한글), VARCHAR(1바이트 1글자-영문전용)
- 필드구성 : PK(primary key = 기본키,고유키,주키) = 테이블영역에서 고유한ID를 말함 (중복되지 않는 값)
- PK예(개인을 식별할 수 있는값): 로그인id, 개인 이메일주소, 주민번호등등, 게시판타입 
- 테이블구성 : 필드(열, column) = 테이블의 멤버변수(자바VO(*value object) 클래스의 멤버변수)
- RDBMS : Relation DataBase Management System(관계형 데이터베이스 관리시스템)
- 오라클 : 테이블스페이스(TableSpace) = 스키마(Schema) = 데이터베이스(DB - MS-SQL)
- 지난주 금요일날, 오라클 웹용 관리프로그램에서 XE라는 테이블스페이슬를 XE사용자로 추가했음
- Entity Relation Diagram (객체 관계 다이어그램) :Entity 테이블을 명시
- 데이터 모델 : Model Object를 그림으로 형상화 시킨것. 데이터베이스를 말합니다.
- MVC 스프링 프로젝트에서 M이 Model 이고, 스프링 프로젝트 구성중에 데이터베이스(DB)를 가리킴.
- 참고로 V(View)는 JSP를, C(Controller)는 클래스를 말합니다.

####20210531(월) 작업내역
- sql쿼리를 3가지로 분류:
- 초기DDL문: Data Definition Language. 데이터 정의언어 Create table문
- 유지DCL문: Data Control Language. 데이터 제어언어 Commit, rollback
- 개발DML문: Data Manufacture Language.데이터 조작언어 CRUD 쿼리.
- 토드(SQL디벨로퍼와 비슷한 상용 프로그램): 버튼하나로도 포워드엔지니어링이 가능.
- 무료인 SQL디벨로퍼에서는 버튼X, DDL문을 실행해서 포워드엔지니어링을 처리.
- 참고로, Mysql용 워크벤치는 무료지만 버튼으로 포워드 엔지니어링이 가능.
- ERD그림 만든것을 물리테이블로 만드는것: Forward 엔지니어링
- 데이터 딕셔너리를 모델과 동기화: 자료사전을 DB테이블과 동기화
- 데이터 딕셔너리는 메타 데이터와 동일합니다.: 콘텐츠X, 구조, 구성정보만 존재
- 물리테이블을 ERD그림으로 만드는것: Reverse 엔지니어링(기존 DB분석시 시용)
- 스프링시큐리티 2단계(구현예정임): 1.로그인인증(ENABLED) - AUTHENTICATION 2. 권한체크(LEVELS) - AUTHORITY (관리자는 admin URL접근가능, 일반회원은 사용자 홈페이지에서 공지사항수정X 겔러리만 사용가능, 비회원은 글쓰기X adminX 관리자X)
- 1:N 관계? 게시물1개에 댓글 여러개. 카테고리 하나에 게시물 여러개.
- E-R다이어그램은 그림일 뿐, 물리DB(테이블)은 ERD기준으로 생성가능.
- PK를 AI(Auto Increment 자동증가)로 만들기: 오라클(시퀸스객체로 기능구현),Mysql(AI라는 필드속성으로 처리)
- ERD에서 Relation생성: 게시판타입테이블(부)과 게시물관리테이블(자)의 관계를 생성
- 부자의 관계는 부모의 PK를 기준, 자녀에게는 PK가 FK(Foreign Key)로 관계를 맺음.
- 게시판타입: notice, gallery 2가지가 존재한다면,
- Relation관계가 필요한 이유: 공지사항이나 겔러리 게시판이 아니면, 개발자가 예외처리를 하지 않아도 다른 게시판으로 등록되는 상황이 발생되지 않게함.
- 데이터 무결성을 유지하는 역할.(외래키관계)
- 필드 데이터형3: timestamp(시간도장) 년월일시분초밀리초까지, Date 년월일까지
- 필드 데이터형2: CLOB(CHAR LONG BYTE) 글 내용이 많을 때 사용하는 데이터 형태
- 필드 데이터형: VARCHAR2(2바이트를 1글자=한글), VARCHAR(1바이트 1글자=영어)
- 테이블구성: 필드(컬럼,열) = 테이블의 멤버변수(자바vo클래스의 멤버변수)
- 필드 구성시, PK(PrimaryKey): 주키,기본키,고유키 = 테이블 영역에서 고유한 ID를 말함.(중복되지 않는 값.)
- PK(개인을 식별할 수 있는 값): 로그인id, 주민번호, 개인이메일 등 
- RDBMS: RelationDataManagementSystem 관계형 데이터베이스 관리 시스템
- 오라클: 테이블스페이스(TableSpace) = 스키마(Scheme:Mysql) = 데이터베이스(DB:MS-SQL)
- 지난주 금요일, 오라클 웹용 관리 프로그램에서 XE라는 테이블스페이스를 XE사용자로 추가했음
- EntityRelationDiagram(ERD-객체관계그램): Entity = 테이블
- 데이터모델: Model Object를 형상화시킨 것을 모델이라고 함. 데이터베이스를 말함.
- MVC 스프링 프로젝트에서 M=Model, 스프링프로젝트 구성 중 DB를 가르킨다. V=View(jsp), C=Controller(클래스)
- 암호는 apmsetup으로 통일
- 4장 패키지 예외처리 실습.
- 스프링프로젝트 ERD 제작 후 세부UI디자인 적용하기.

####20210601(화) 작업내역
- 언어를 배우는 순서: 주석 > 디버그방법 > 변수 > 메소드(함수) > 클래스 > MVC모델
- 디버그하는 방법: 자바(System.out 이용해서 콘솔창에 출력), 스프링(logger 사용해서 디버그 내용 출력)
- 스프링이 관리하는 클래스(빈)의 종류 3가지.(사용법은 개발자가 만드는 클래스명 상단에 입력)
- 1. @Controller 클래스빈 : 라우터역할 메소드를 만듭니다. (라우터역할의 클래스기능)
- 2. @Service 클래스빈 : 비지니스로직 메소드를 만듭니다.(개발자위주의 외부클래스 기능)
- 3. @Repository 클래스빈: Model처리 메소드를 만듭니다.(DB핸들링 외부클래스 사용)
- 위3가지의 @를 사용하는 클래스는 DI(객체생성-의존성주입), AOP(관점지향), IoC(제어의 역전-자동메모리관리) 사용 가능.
- 4장 패키지와 예외처리 실습.
- 프론트개발자가 작업한 결과를 백엔드 개발자가 확인.
- 일괄바꾸기 1. "/home -> "/resources/home
- 일괄바꾸기 2. '/home -> '/resources/home
- VS code 작업한 html을 이클립스에 배치를 합니다.(프론트개발자가 작업한결과를 백개발자가 받아서 배치)
- resources 폴더에 static컨텐츠(html,css,js,font) 배치.
- ERD기준으로 게시판UI 마무리합니다.-board_write.html 부터 시작
- 오늘부터는 VS code -> 이클립스에서 작업합니다.
- 관리자단 AdminLTE적용-스프링시간 선택 후 아래 작업진행예정
- (회원관리CRUD-jsp, 게시판생성관리CRUD-jsp)
- html을 분해(1개의 페이지를 2개로 분해,1.header.jsp(메뉴를공통), 2.footer.jsp(공통))
- 이클립스로 작업한 html 내용을 -> resources 폴더(admin,home,root파일까지)로 배치
- 스프링 작업의 시작

#### 20210602(수) 작업
- 예외처리하는 목적: 에러상황에서도 프로그램이 중단되지 않도록 하는 것이 주목적(에러상황을 세련되게 넘기게 처리)
- 스프링에서는 개발자가 처리하는 100개의 코딩중에서 2~3개 정도이고, 대부분은 throws Exception 스프링으로 넘김.(예외의 전파)
- thread(쓰레드) : 실행되는 프로그램을 명시, 1개의 프로그램에는 보통 1개의 쓰레드가 실행(우리소스는 여기까지), 특이한 경우 한 개의 프로그램에 여러개의 쓰레드의 실행이 필요한 경우(http 웹 네트워크 프로그램인 경우 - 게시물에서 다운로드버튼클릭시 다운로드  100G 쓰레드 실행되면서, 리스트버튼을 클릭했을 때 목록보기 쓰레드가 동시에 실행 -안드로이드 앱에서 필요함)가 있음 
- 예외처리 : Throwable 오브젝트가 실행시 에러가 발생하면, 예외(내용)를 보낼 때 보낼 때 사용하는 클래스 Throwable클래스 입니다.
- 스프링앱에서는 예외(에러)정보를 스프링이 처리합니다.
- 예외처리 : 에러발생시 프로그램이 중지되는 것을 방지하고, 계속 프로그램을 사용할 수 있도록 처리하는 방법= Exception
- 패키지는 관련기능을 한곳에 모아서 개발자가 관리하기 편하게 하기 위해서 구분한 이름(폴더명)
- Controller 클래스 + home/index.jsp 한 쌍입니다. 그래서, 컨트롤러 클래스 변수를 index.jsp에서 사용가능하게 됩니다.
- 안드로이드 앱 = Activity(java) + 레이아웃.xml(화면) 한 쌍
- C#닷넷 = test.aspx.cs(프로그램) + test.aspx(화면) 한 쌍
- 일반홈페이지(cafe24) URL직접접근이 가능(보안위험 높음)
- MVC 웹 프로그램 차이점- URL 직접접근이 불가(보안위험 낮음) - 관송서, 대학, 은행 , 주사용
- MVC 프로젝트에도 직접접근이 가능한 resources 폴더 직접접근이 가능 - static 콘텐츠(html,css,js)를 모아 놓은 폴더 views폴더 jsp는 직접접근이 불가능
- views 폴더처럼 직접접근이 불가능한 컨텐츠는 Contorller(router)로 접근함
- view/hom/index.jsp 엑박처리, 분해는 관리자단 실습 후 작업합니다.
- 개발순서: ERD제작 -> html제작 -> jsp제작(현재: 관리자단 작업 후 사용자단 /관리자단 파일 : 사용자단 파일 = 10 : 5)
- admin 폴더 이후에는 분해 (OK) Junit 실습 후 작업합니다.
- Junit(Java Unit Test): 자바 단위 테스트(서비스 프로그램만들기 전 prototype 시제품 제작) -Junit CRUD 작업 후, 본격작업 시작
- 로거의 레벨: DEBUG > INFO > WARN > ERROR > FATAL
- 개발할 때 : DEBUG 로거레벨을 설정
- 납품할 때 : WARN 으로 로거레벨을 변경.
- admin 부터 프로그램 작업 실작예정
- 4장 패키와 예외처리 실습 후 오라클 CRUD 실습

#### 20210603(목) 작업
- 작업비중 : 관리자단(70%) - 사용자단(30% 관리자단 로직이 들어감)
- 스프링에서 오라클연동 순서 2가지
- 1. jdbc(Java DataBase Connection) 확장모듈 pom에 추가
- 2. 오라클 접속 드라이버 확장모듈을 pom에 추가하지않고 직접 jar파일에 추가할 예정
- root-context.xml 파일에 오라클 커넥션 빈(스프링 클래스)을 추가합니다
- 스프링이 관리하는 클래스를 추가하는 방법 2-1 : @Controller, @Repository, @Service, @Component
- 스프링이 관리하는 클래스를 추가하는 방법 2-2 : *-context.xml에서 빈(bean)을 추가하면, 스프링 클래스가 됨
- JUnit 테스트: 오라클 연동한 후 회원관리 부분 CRUD 테스트 진행예정
- 오라클 04장부터 CRUD 실습예정
- admin 회원관리(jsp디자인) 부터 프로그램 작업 시작예정

#### 20210604(금) 작업
- 오라클일 때: localhost:1521/XE [접속URL끝의  XE는 서비스ID명] -> XE, XE2 스키마2개(DB2개)가 존재합니다.
- 오라클은 사용자명이 테이블스페이스명(DB명)입니다. 사용자(XE2)워크스페이스를 추가하면 테이블스페이스(DB)가 새로 만들어집니다. 
  (구버전은 데스크탑프로그램-> 지금은 웹프로그램 사용자추가/ DB(테이블스페이스)추가)
- MySQL(MariaDB): localhost:3306/XE [접속URL끝의 XE는 스키마명(DB명)]
- junit 테스트시 sql에러를 디버그하는 방법은 jdbc드라이버 -> log4jdbc드라이버 바꾸면, sql에러가 나오게됩니댜.
- junit에서는 sql에러가 보입니다. 콘솔창에는 보이지 않습니다. 콘솔창에서 sql로그상황이 나오게 하는 드라이버를 pom.xml에 추가합니다. 
- 오라클: 07장 마무리 후 13장으로 바로 실습예정.(나머지는 아래 실습 후)
- 스프링: JUnit테스트: oldQueryTest메서드 실습 예정.
- 오라클: 08,09,10,11,12,14장 실습예정.
- 스프링: JUnit테스트: 회원관리부분 CRUD 테스트 진행예정.
- 오라클: 더미데이터 일괄등록 예정. 회원관리(100명), 게시판관리(공지사항50개,갤러리50개)
- 스프링: 관리자단, 회원관리부터 스프링 작업예정.

#### 20210607(월) 작업
- 오라클 DB관리 로그인 정보(application express 웹프로그램 이름): admin/apmsetup -> 암호요청변경 : Apmsetup1234!
- 오라클 기초이론 마무리: 시퀸스(스프링에서 사용- AI자동증가기능)
- JUnit으로 CRUD 실습. - 코딩시작 -

- 책 스프링 웹프로젝트는 개발 STS(스프링툴슈트) 툴. => 기반은 이클립스 기반을 확장
- 우리가 하는 책 스프링 웹프로젝트는 개발 전자정부표준프레임워크 개발 툴 => 기반은 이클립스 기반을 확장  
전자정부표준프레임워크를 커스터마이징(제외)

- 스프링에서는 마이바티스를 이용해서 쿼리를 관리하게 됩니다.
- 이전에는 자바코드(jsp코드)안에 sql쿼리를 만들어서 프로그램을 제작하였음. -> 문제점 발견(스파게티 코드)
- MVC로 분리 Model부분 쪽에 query를 분리하는 것이 마이바티스(mybatis) 형은 (ibatis) 지금 안씀 -> 마이바티스가 현재 사용됨
- 스프링(마이바티스)가 나오기 전에는 쿼리른 모아서 작업을 프로시저로 만들어서 작업을 했습니다. 
- 지금은 스프링으로 옮기지 못한 프로그램을 제외하고는 대부분 마이바티스로 쿼리를 관리합니다.
- 그래서, 마이바티스 셋팅을 합니다. 메이븐설정

- 마이바티스 추가 순서:
- 1. pom.xml에 의존성 추가
- 2. mybatis 설정파일 추가(쿼리를 저장할 위치지정, 파일명지정)

```
-- SQL Developer에서 system으로 로그인후 아래 쿼리로 XE2 사용을 완벽하게 지우기 (아래)
SELECT  * FROM all_users;
-- all_users 테이블X synonym(동의어)

-- 지워지지 않음 테이블이 아니기때문
DELECT FROM all_users WHERE username = 'XE2';

-- synonym = 테이블명이 사용하기 힘들정도로 길거나
-- 오라클은 스프링과 같은 방식 패키지명안에 함수
-- 프로시저(오라클 프로그램)를 만들 수 있습니다.
-- 패키지명이 길어서 사용시 개발자에게 부담이 됩니다.
-- 위상황을 해결하도록 만든 것이 synosym(동의어)임

DROP USER XE2 CASCADE;
-- XE 사용자를 지울 때, XE 사용자가 생성한 테이블까지 모두 지우는 명령
--  e.g. CSS (Cascade Style Sheet = 계층형 스타일 시트)
```

- 현업에서는 오픈소스인 MySQL (MariaDB)를 사용하실 기회가 더 많습니다. 오픈소스이기 때문에 - 개발자가 많은 편입니다.
- 오라클은 납품시 소프트웨어 비용이 산정이 되어서 사용하실 기회가 적지만, 개발자가 상대적으로 적은 편입니다.
- 보통은 학교, 시청, 기업체 전산실에 부탁을 해서 XE사용자를 추가해달라고 요청해서, 발급받은 계정으로 개발을 시작합니다.
- 오라클 기초이론 마무리: 시퀸스(스프리에서 사용 - AutoIncrement(AI) 자동증가기능)
- 만약 시퀸스(AI)기능이 없다면, 게시물 작성시 첫 번째, 두 번째 등등을 구별하는
- 숫자를 계속 추가하려면, 현재까지 저장된 게시물의 최고번호값(Max)을 구해서 +1을 해줘야 합니다 (개발자가 Insert시)
- DB에서 기본으로 위 MAX값을 구해서 +1 하는 로직을 만들게 됩니다. 이 기능이 Sequence(AI)입니다.
- 우리 스프링 프로젝트에서는 2개의 시퀸스를 만듭니다. (게시물 시퀸스 seq_bno, 댓글 시퀸스 seq_rno)
- synonym (긴 객체를 개발자가 타이핑하기 어려워서 만든 단축 이름) e.g. sys.dual -> dual
- 오라클: 더미데이터 일괄등록예정 (회원관리 100명 게시판관리) (공지사항 50개 갤러리 50개)
- 위 더미데이터는 프로시저(함수) 라는 DB프로그램 방식으로 추가합니다.

#### 20210608(화) 작업


> 페이징에 사용되는 변수(쿼리변수+VO변수)  
<queryStartNo, queryPerPageNum, page, perPageNum, startPage, endPage>  
검색에 사용되는 변수(쿼리변수만): 검색어(search_keyword), 검색조건(search_type)

```
-- SQL쿼리 페이징을 구현해서 변수로 삼을 것을 정의할 예정
-- PageVO의 멤버변수로 사용예정

SELECT TableB.* FROM
(
    SELECT ROWNUM AS RNUM, TableA.* FROM
    (
        SELECT * FROM tbl_member
        ORDER BY reg_date DESC
    ) TableA WHERE ROWNUM <= 1*10 + 10
) TableB
WHERE TableB.RNUM > 1*10
;

SELECT TableB.* FROM
(
    SELECT ROWNUM AS RNUM, TableA.* FROM
    (
        SELECT * FROM tbl_member
        ORDER BY reg_date DESC
    ) TableA WHERE ROWNUM <= (page*b) + b
) TableB WHERE TableB.RNUM > (page*b)
;
-- (1 2 3 4) 1번페이지 열람시 1번 페이지에 1~10번의 게시물이 나오고 그다음 2번째 페이지는 11~20번의 게시물이 출력됨
-- paging query에ㅐ서 필요한 변수는 2개
-- ((a*b)/b+1)*b -> (0/10+1)*10
-- 현재페이지수의 변수 page*b: 0*5, 1*5, 2*5...                    page*b == queryStartNo
-- 현재페이지 기준 보여줄 게시물 개수의 변수 b: 5, 10, 20...          b == queryPerPageNum
-- pageVO에서 필요한 추가변수 : page                                          
-- UI하단의 페이지 선택번호 출력할 때 사용하는 변수(아래)                              
-- perPageNum을 변수로 받아서 startPage, endPage 를 구해서 하단의 페이지 선택번호를 출력

-- 검색기능
SELECT TableB.* FROM
(
    SELECT ROWNUM AS RNUM, TableA.* FROM
    (
        SELECT * FROM tbl_member
        WHERE user_id LIKE '%admin%'
        OR user_name LIKE '%사용자8%'
        ORDER BY reg_date DESC
    ) TableA WHERE ROWNUM <= 0*5 + 5
) TableB
WHERE TableB.RNUM > 0*5
;

```
>

- 스프링코딩 작업순서 1부터6까지(아래)
- 1. ERD를 기준으로 VO클래스를 생성.
- M-V-C 사이에 데이터를 입출력하는 임시저장 공간(VO클래스-멤버변수+Get/Set메서드) 생성 
- 보통 ValueObject클래스는 DB테이블과 1:1로 매칭이 됩니다.
- 2. 매퍼쿼리(마이바티스사용)를 만듭니다.(VO사용해서쿼리생성).
- 3. DAO(데이터엑세스오브젝트,DTO)클래스를 생성(SqlSession사용쿼리실행).*오늘 Sql세션은 root-context에 빈으로 만들었습니다.(1개)
- IF 인터페이스는 만드는 목적 : 복잡한 구현 클래스를 간단하게 구조화 시켜서 개발자가 관리하기 편하게 정리하는 역할  
cf) 기사시험책에서는 캡슐(알약)화 구현과 관련이 있음 -> 캡슐안에 어떤약이 있는지 모르게 먹게하기 //   
*캡슐화 : 구현 내용을 몰라도, 이름만 보고 사용하게 하게 만든 것
- 스프링 부트(간단한 프로젝트)에서는 4번 Service클래스가 없이 바로 컨트롤러로 이동합니다.
- 4. Service(서비스)클래스생성(서비스에 쿼리결과를 담아 놓습니다.)(1개)
- 게시물을 등록하는 서비스 1개(tbl_board - DAO 1st, tbl_attach - DAO 2nd...)
- JUnit에서 위 작업한 내용을 CRUD 테스트
- 5. Controller(컨트롤러)클래스생성(서비스결과를 JSP로 보냅니다.)
- 6. JSP(View파일) 생성(컨트롤러의Model객체를 JSTL을 이용해 화면에 뿌려 줍니다.)

#### 20210609(수) 작업 
- 프로젝트를 진행 : 보유기술 30% + 신기술 70%(개발사도 모릅니다) - 9할 실패 (스타트업) // 보유기술 70%(솔루션이 있는 업체) + 신기술 30% (일반회사)
- PageVO.java 클래스 생성 마무리 OK
- JUnit에서 위 작업한 내용을기준으로 selectMember() 테스트진행  OK
- <![CDATA[ ]]> : 태그 안쪼겡 부등호를 사용하기 위해서 문자열 변환 태그를 사용
- 쿼리에서 변수와 문자열과 연결할 때는 +기호는 사용불가 콤마불가 --> || 쌍파이프라인기호로 연결시켜줌
- JUnit에서 회원관리 나머지 CRUD 테스트 진행
- jsp에 Controller를 이용해서  관리자단 회원관리화면 JSP 만들기 진행예정.

#### 20210610(목) 작업
- 수업전 내용 확인 합니다.(아래)
- 쿼리실습에서 .equals함수 사용에 대해서 설명할때,아래 isEmpty메서드와 착각해서 이야기 한 내용이 있어서 정정 합니다.
- 자바에서 객체가 공백 또는 비었는지 비교할때, 예를 들면, 우리프로젝트에서 첨부파일이 있는지 비교할때 아래 처럼 사용하지 않고
- if(save_file_name != null && "".equals(save_file_name))
- 다음처럼 짧게(널과공백체크를 한번에) 사용합니다.(아래)
- if(!save_file_name.isEmpty())
- =========================================
- GMT시간(그리니치 천문대 기준 - 표준시) - KST한국 시간과는 9시간 차이
- DB서버에 타임존 설정 Asia/Seoul로 되어 있으면, 그냥 사용합니다
- 위 처럼 타임존 설정이 없으면 GMT시간에 시차를 더해서 INSERT UPDATE 한국시간으로 사용합니다.
- 오라클일때 확인 :
	SELECT TO_CHAR(systimestamp + numtodsinterval( 9, 'HOUR' ), 'YYYY-MM-DD HH24:MI.SS.FF4')  from dual;
- Mysql(마리아dB)확인 :
	SELECT DATE_ADD(NOW(3), INTERVAL 9 HOUR);
- JUnit에서 회원관리 나머지 Read,Update 테스트 진행예정.
- 업데이트 실습은 회원암호를 스프링시큐리티5 암호화(1234 -> hash data)로 일괄변경 실습예정.
- 정방향 암호화 가능, 역방향 복호화는 불가능(JAVA용 스프링 시큐리티 암호화, DB용 MD5 암호화, ...)

```
BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
String userPwEncoder = passwordEncoder.encode(memberVO.getUser_pw());
memberVO.setUser_pw(userPwEncoder);
```
- 컨트롤러를 이용해서 관리자단 회원관리화면 JSP 만들기 진행예정.
- JUnit 마치고, 관리자단 회원관리(CRUD) jsp까지는 작업합니다. 이후 앞에 내용을 참조해서 계속 확장해 나가는 작업이 이어집니다.

#### 20210611(금) 작업
- JSTL : Java Standard Tag Language (Library)
- taglib : uri(유니폼 리소스 ID - 고유값 - 지구 전체의 고유값의 위치 - 식별값) > url (링크 경로)
- prefix (접두어)
- 나머지 관리자 회원관리 CRUD 화면 JSP처리 insert(X) view>update

- 정방향(앞으로) : 매퍼쿼리>DAO>Service>[JUnit]컨트롤러 클래스>JSP
- 역방향(클래스간 이동) : 정방향으로 개발한 것 검증용만 사용
- 수업전 관리자단 회원관리 페이징처리에서 컨트롤러와 calcPage()메소드

- 수업전 관리자단 회원관리 페이징처리에서 컨트롤러와 calcPage()메서드의 관계 간단하게 확인하겠습니다.
- 관리자단 회원목록 처리 마무리(1.페이징및 검색기능구현).
- model을 이용해서 결과를 JSP로 구현.(2.JSP화면은 표준언어인 JSTL로 구현)
- [공지]06-17 목요일(4교시) UI 디자인 시험 있습니다.(화면기획서XLS제작, 화면설계서PPT제출)
- 그래서, 다음주 월요일 점심시간 피곤할떄 현재 프로젝트를 정리하는 문서작업 시간을 갖겠습니다.
- 나머지 관리자 회원관리 CRUD 화면 JSP처리.
- 관리자단 게시판 생성관리 CRUD 처리.(3.AOP기능구현).
- 관리자단 게시물관리 CRUD 처리(4.파일업로드구현,5.트랜잭션구현).
- 관리자단 댓글 CRUD 처리(6.RestAPI기능구현).
- 관리자단 왼쪽메뉴 UI 메뉴 고정시키기(7.jQuery로 구현).
- 사용자단 로그인 화면 JSP로 만들기.
- 로그인처리 및 관리자 권한체크 기능 추가(8.스프링시큐리티구현).
- 사용자단 회원가입, 수정, 탈퇴 JSP기능 추가.
- 사용자단 게시판 CRUD 처리.
- 사용자단 댓글 CRUD 처리.
- 헤로쿠 클라우드에 배포(9.클라우드 배포구현).
- 사용자단 네이버아이디로그인 처리(10.외부RestAPI구현).

#### 20210614(월) 작업
- 수업 전 관리자 회원관리 view화면구현 마무리OK.
- multipart(첨부파일기능) 라는 폼태그 전송방식을 추가 -> commons.fileupload 외부모듈필수(pom.xml에서 의존성을 추가합니다.)
- 위 외부모듈을 스프링 빈으로 등록합니다.(servlet-context.xml 하단에 추가)
- CRUD에서 multipart를 사용한다면, 리졸브(resolve-해석기) 스프링빈이 필요

- 데이터변수를 전송할 떄 GET(URL 쿼리스트링)으로 전송받으면, 타 도메인에서도 GET으로 접근이 가능합니다.
- 쉽게 말하면 , 다른 도메인 (서버)에서 GET은 검색만 가능, 입력/수정/삭제 불가능
- 단, 데이터변수를 POST(숨김)으로 전송받으면, 타 도메인에서는 접근 불가능합니다.
- 웹 프로그램은 보안떄문에 외부도메인에서 커늩롤러에서 지정한 GET방식의 URL로 접근해서 데이터출력이가능
- 단, 입력수정삭제 기능은 자바컨트롤러클래스 GET방식으로 못하고, POST방식으로 지정 -> 다른도메인(서버)에서 회원가입을 못하도록 서버단에서 에러를 발생시킴
- 나머지 관리자 회원관리 CRUD 화면 JSP구현 update(OK) delete(OK)
- GET : Insert (외부 사이트 입력폼에서 입력가능) - queryString으로 데이터 전송 url?key=value&ekey2=value2
- POST : Insert (외부 사이트 입력폼에서 입력불가능, 같은 사이트의 입력폼에서만 가능) hidden string으로 데이터 전송

#### 20210615(화) 작업
- 관리자단 회원관리 수정 암호 수정 잘 되는지 확인OK.
- 회원관리 CRUD 화면 JSP구현 update(OK),delete(OK),insert(OK)
- [공지]06-17 목요일(4교시) UI 디자인 시험 있습니다.(화면기획서XLS제작, 화면설계서PPT제출용)

#### 20210616(수) 작업
- [공지]06-17 목요일(4교시) UI 디자인 시험 있습니다.(화면기획서XLS제작, 화면설계서PPT제출용) 확인 후 수업진행.
- 관리자단 게시판 생성관리 CRUD 처리.(3.AOP기능구현).
- 10~20년 지금까지 변하지 않는 것은 변수값의 흐름은 변함이 없음
- 시작은 VO -> mapper쿼리 -> DAO클래스 생성 (스프링빈 설정)->Service클래스 생성
- 관리자단 게시물관리 CRUD 처리(4.파일업로드구현,5.트랜잭션구현).
- 게시판 생성관리VO파일, Mapper파일, DAO파일(+ 인터페이스), Service파일(+ 인터페이스) 작성

#### 20210617(목) 작업
- [복습] 스프링의 주요기능 : IoC(제어의 역전 : 객체의 관리를 스프링이 대신해줌) DI(의존성 주입 @Inject) 
- 관리자단 왼쪽메뉴에 게시판 종류가 실시간으로 출력이 되어야 하는데, 지금은 게시판 생성관리 목록 페이지에서만 보임 (문제점)
- 위 문제를 해결하는 방식으로 AOP기능을 사용합니다.
- Spring AOP구현은 3가지 방식이 있습니다 
- @Aspect, @ControllerAdvice, intercept tag를 사용해서 AOP구현
- AOP용어 : 관점지향 = 프로그램 전체의 영향을 주는 공통의 기능을 적용하는 패턴 기법
- AOP용어 : Advice(충고 - 간섭) : 프로세스작업 중간에 필요한 기능을 중간에 끼워넣는 것을 어드바이스라고 함
- Advice : Point Cut(간섭, 필요한 기능을 끼워넣는 시점, @Before(발생하기전에) @After(발생한후에) @Around(발생전후))
- @ControllerAdvice 실행조건 : 컨트롤러 클래스의 메소드에만 Advice(간섭) 적용됨
- @Aspect 장점 : 특정클래스의 특정메소드 실행시(point cut) 자동 실행되는 메소드를 지정이 가능
- @Aspect 실행조건 : @Controller 이외의  @Repository(DAO클래스) @Service(서비스클래스)에도 Advice가능
- 게시판종류 출력 : @ControllerAdvice (게시판 생성관리 CRUD 작업시 실습)
- 검색시 pageVO를 유지하듯이 board_type 값을 계속 유지하는 기능 @Aspect (게시물관리 CRUD 작업시 실습 적용할 예정)
- 보안 - 로그인 체크, 권한 체크시 : intercept(스프링시큐리티)태그를 사용  (로그인 기능, 권한체크기능 구현시 실습)
- intercept 태그는 스프링시큐리티에서 관리
- 관리자단 게시판 생성관리 리스트 페이지 OK
- 관리자단 게시판 생성관리 CRUD 마무리 처리 (3. 스프링의 AOP기능구현)
- UI디자인 과제물 제출 4교시
- 게시물관리 시작 : 다중게시판 = 1개의 페이지로 공지사항, 갤러리, Q&A...
- 정방향으로 개발시작 VO제작 -> mapper query 제작

#### 20210618(금) 작업
- 검색처리는 멤버쿼리에서 작성한 내용 붙여넣고, 다중게시판용 필드조회조건 board_type 추가예정 
- 관리자단 게시물관리 CRUD 처리(4.파일업로드구현,5.트랜잭션구현).
- 게시물관리 시작: 다중게시판? 1개 페이지로 board_type 변수를 이용해서 공지사항,겔러리,QnA... 구별해서 사용.(쿼리스트링이 길어져서 @Aspect로 사용)
- 정방향으로 개발시작.VO제작.->매퍼쿼리제작. -> DAO제작 -> Service제작
- 트랜잭션 상황 
- 상황1 : 2사람 이상이 동시에 글을 쓴다. 모두 첨부파일을 추가하는 상황 
- 사람1 : insertBoard -> bno(101)생성 -> 첨부파일 insertAttach -> 게시물생성 bno필요(이 때 102를 가져다가 사용함)
- 사람2 :                                     insertBoard -> bno(102) ->...  
- 해결책1 : @Transantional을 insertBoard메소드로 감싸주면, 간단하게 해결
- 해결책2 : insertBoard 쿼리에 return 값을 bno로 받아서 insertAttach처리
- @Service까지는 DB(테이블) CRUD합니다.
- 그러면, 첨부팡리은 @Controller에서 업로드/다운로드 로직 여기처리 @Controller 메소드부분이 난잡해짐

#### 20210621(월) 작업
- 스프링 시큐리티: 로그인정보가 발생=세션, 즉, 로그인정보(세션)이 없으면, 홈페이지로 가도록 처리
- 관리자단 게시물관리 CRUD 처리(4. 파일업로드 구현,  5. 트랜잭션구현)
- @Service 클래스 마무리
- 트랜잭션 : 여러개의 메소드를 1개 처럼 처리하게 구현 어노테이션 - 목적 : 데이터무결성유지
- 1단어로 표시 : All or NotAll (모두 실행되던지, 에러발생 모두 실행이 되지 않던지)
- root-context와 servlet-context설정파일에 트랜잭션과 파일업로드 설정처리
- @Controller 클래스 추가 (파일업로드/다운로드 구현) > jsp 화면처리
- @Service에 트랜잭션 기능 추가
- AOP기능중 Aspect기능의 설정은 servlet-context.xml에 위치해야합니다.
- 관리자단 댓글관리 CRUD 처리(6. RestAPI 서버구현, JUnit대신에 부메랑(크롬확장프로그램)으로 테스트)

#### 20210622(화) 작업
- 정방향으로 개발시작 VO제작 ->매퍼쿼리제작 -> DAO클래스 제작 -> Service클래스제작 ->Controller+jsp
- 위 내용중 게시물 관리에서 CRUD 컨트롤러 + jsp 처리(4. 파일업로드구현)
- 작업순서 : RUD -> C
- R : readBoard(서비스)참조 -> board_view(컨트롤러) 작업예정
- 에러상황 : ie11 이하계열에서 검색 후 페이지 선택시 400에러 발생 (크롬계열은 문제없음)

#### 20210623(수) 작업.
- 시큐어코딩 방지메서드: <(O|o)bject... -> &lt;object (목적은 코딩태그를 특수문자로 변경 하는 메서드)
- 실행되지 않는 코드가 생성됨.
- 세션 사용법: 겟(Get),셋(Set),삭제(Remove)하는 방법
- 세션 생성법: session.setAttribute("세션변수명","값");//로그인시 세션변수 생성.
- 세션 값불러오기: session.getAttribute("세션변수명");
- 세션 삭제하기: session.removeAttribute("세션변수명");//변수삭제
- 전체세션삭제하기: session.invalidate();//전체 세션변수명을 삭제 = 세션초기화 = 로그아웃시 사용.
- 수업전 작업예정: ie11이하계열에서 한글 검색 후 페이지 선택시 400에러발생(크롬계열은 문제없음)-AOP로처리.

#### 20210624(목) 작업
- file.getBytes() 설명 포함 board_update메서드 리뷰 후 수업진행.
- 작업순서: CRUD -> UC 작업.
- update: updateBoard(서비스)참조 -> board_update(컨트롤러)작업+jsp작업
- 업데이트 이후엔 파일업로드 구현 후 /download 컨트롤러 실습예정.
- 관리자단 댓글관리 CRUD 처리(6.RestAPI서버구현,JUnit대신에 크롬부메랑으로 테스트)

#### 20210625(금) 작업
- Pull이 안되는 원인: 로컬 이클립스에서 commit할 것이 남아있으면 PULL 안됨. 해결책은: 이클립스에서 커밋 후 다시 PULL로 해결.
- 게시물관리 Create 작업 마무리.
- 고전CRUD 와 RestFull(API)방식 차이점: 고전(화면이 이동하면서 CRUD처리), Rest방식-대세코딩(1개화면에서 CRUD처리)
- 관리자단 댓글관리 CRUD 처리(6.RestAPI서버구현,JUnit대신에 크롬부메랑으로 테스트)
- 댓글 VO제작->매퍼쿼리제작->DAO클래스제작->Service클래스제작->@RestController클래스제작->크롬부메랑테스트(JUnit테스트대신)->JSP제작(1페이지CRUD처리Ajax이용)
- 스프링시큐리티 로그인및 권한체크 설정 후 사용자단 로그인 구현 예정.(관리자단 끝 이면서, 사용자단 시작)
- 사용자단 회원가입, 수정, 탈퇴 JSP기능 추가예정.

#### 20210628(월) 작업
- 댓글 VO제작->매퍼쿼리제작->DAO클래스제작->Service클래스제작/여기까지-
- ----------------------------------------------------
- @RestController 클래스제작 : 일반 컨트롤러와 다르게 반환값이 body로 출력이 됨
- 크롬부메랑테스트(JUnit테스트대신)->JSP제작(1페이지 CRUD처리 Ajax이용)
- 네트워크로 자료를 전송하는 방식: SOAP방식(구버전), REST방식(신버전 HTTP사용 헤더에 자료를 담아보내는 방식)
- Endpoint: (마이크로 서비스에서 사용하는 단어)  RestAPI로 구현되고, 요청하는 URL을 Endpoint라고 합니다.
- 마이크로서비스 = 서비스를 쪼갬, 기존 컨트롤러를 모두 RestController(Rest Gateway)로 변경이 필요
- Endpoint (URL주소)에는 데이터를 전송할 때, queryStr으로 보내지 않는 방식이 트랜드
- e.g. 옛날방식 reply/reply_list?bno=변수값&page=변수값.. => @RequestParam 어노테이션으로 받음
- e.g. 최근방식 /reply/reply_list/{bno변수값}/{page변수값}.. => @PathVariable 어노테이션으로 받음
- 결과, /reply/reply/59/1 (목적은 검색에 쉽게 노출시키기 위해서)
- 스프링시큐리티 로그인및 권한체크 설정 후 사용자단 로그인 구현 예정.(관리자단 끝 이면서, 사용자단 시작)
- 사용자단 회원가입, 수정, 탈퇴 JSP기능 추가예정.

#### 20210629(화) 작업.
- json데이터(1개레코드=K:V무제한형태)가 자바의 List데이터(1개레코드=K:V제한형)와 대부분 같음. 틀린점은 K:V 형태는 같으나 V값이 무제한,제한
- 게시물 상세보기 페이지에는 
- 게시물관련내용: 컨트롤러에서 보낸 model객체에 담긴 변수값을 jps사용.
- 댓글 관련내용: Rest컨트롤러에서 보낸 ResponseEntity객체에 담긴 변수값을 jsp사용.
- RestAPI가 주로 사용되는 곳은: 1페이지로 서비스가 이루어지는 곳에서 주로 RestAPI를 사용
- 데이터를 시각화하는 페이지에 주로사용: 구글맵,네이버맵기반의 데이터를 시각화해서 수익창출 서비스.
- RestAPI가 스프링과 노드js 연동하면 + 구글맵, RestAPI실시간으로 결과공유할 수 있게 만든것.
- 수업전 아래 내용 확인 후 진도 나갈 예정 입니다.
- reply컨트롤러에서 requestMapping 밸류값 넣을때 절대경로인 /로 시작하시는 것이 맞습니다.
- Rest컨트롤러에서 CRUD중 Delete마무리OK.
- jsp에서 1페이지만 작업하면 끝 $.ajax를 이용해서 RestAPI서버 사용.
- $.ajax로 CRUD를 구현할때는 전송값은 json데이터(submit으로 않보냄)로 보내고(form태그가 필요없음),:submit은 폼태그가 있을때만 작동되는 브라우저 내장 명령입니다.
- , 받은때는 List(json),CUD(문자열)
- 댓글 RUD는 모달(팝업)창에서 작업시작.

#### 20210630(수) 작업.
- 댓글 Delete 구현 후 마무리OK.
- 스프링시큐리티 로그인및 권한체크 설정 후 사용자단 로그인 구현 예정.(관리자단 끝 이면서, 사용자단 시작): 사용자단 로그인 / 로그아웃 기능 처리.
- 사용자단 회원가입, 수정, 탈퇴 JSP기능 추가예정.

#### 20210701(목) 작업
- 어제 작업한 security-context를 데이터 변수값 이동기준을 다시 설명
- 수업 시작전 깃허브 암호정책 변경으로 토큰사용하는 방법 공유
- 람다식사용예 : https://github.com/miniplugin/SQLite-kimilguk/blob/master/app/src/main/java/com/human/sqlite_kimilguk/MainActivity.java
- 어제 시큐리티적용 부분 확인(web.xml에서 누락된 부분 모두 추가)

```
<!-- 스프링 시큐리티때문에 필터(걸러주는)추가 -->
<filter>
	<filter-name>springSecurityFilterChain</filter-name>
	<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
<filter-mapping>
	<filter-name>springSecurityFilterChain</filter-name>
	<url-pattern>/*</url-pattern>
</filter-mapping>
```
- 어제 시큐리티 context 누락된 부분 추가(security-context.xml)

```
<security:authentication-provider>
	<security:password-encoder ref="passwordEncoder" />
</security:authentication-provider>
<!-- 위 쿼리에서 사용할 패스워드 암호화 id passwordEncoder 빈 클래스를 생성(아래) -->
<bean id="passwordEncoder" class="org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder" />
```
- 스프링시큐리티 로그인및 권한체크 설정 후 사용자단 로그인 구현 예정.(관리자단 끝 이면서, 사용자단 시작): 사용자단 로그인 / 로그아웃 기능 처리OK.

#### 20210702(금) 작업.
- 수정/탈퇴(마이페이지) JSP기능 추가 마무리OK.
- 사용자단 회원가입 작업OK.
- form폼에서 name은 VO/매퍼쿼리 필드명동일, id는 선택해서 jsp(UI)단에서 제어(j쿼리)할때 사용.
- 사용자단 에러발생시 이쁘게 보이게 화면처리.
- error_spring.jsp 만듭니다.
- 위 jsp를 에러발생시(Exception) 무조건 나오게 처리: AOP중 @ControllerAdvice로 구현합니다.
- 위 어드바이스컨트롤러에서 에러메세지를 캐치해서 jsp에러페이지로 보내서, 에레메세지를 이쁘게 확인합니다.
- 404에러는 컨트롤러에서 발생되지 않습니다. 그래서, 별도파일을 만들어야 합니다.
- 톰캣서버에서 발생되는 에러코드404이기 때문에 web.xml에서 설정을 추가합니다.
- 404코드가 발생시 error_404.jsp와 바인딩되는 설정입니다.
- 홈컨트롤러에서 Get /home/error/error_404경로추가
- -----------------------------------------
- 헤로쿠 클라우드에 배포준비예정.
- 헤로쿠 클라우드는 미국의 회사로서 컨테이너를 제공하는 회사
- 컨테이너는 리눅스OS>톰캣WAS>자바JVM>스프링>컨테이너에 포함됨 기본.
- 외부 서버는(DB) Add on이라는 이름으로 사용가능
- 무료: PostgeresDB(조건), 마리아DB(신용카드등록필수)
- 유료: Mysql(유료)
- HsqlDB로 연동해서 헤로쿠에 배포예정. http://hsqldb.org/
- 100% Java Database: 임베디드DB, 메모리DB, 서버를 설치할 필요 Hsql이라는 Maven모듈만 있으면가능
- 프로토타입 작업시 주로 사용.(특징, 쿼리는 Mysql과 99% 동일)

#### 20210705(월) 작업예정
- Deploy에서 에러 : No Web processes running
- 현재 프로젝트에 클라우등요 설정파일이 필요 = 헤로쿠에서 Profile 확장자없는 설정파일이 필요
- 위 Procfile에서 web processes running 시키는 라인이 추가 되어야 함.
- 우리 프로젝트에서 클라우드용 설정파일이 필요 - 헤로쿠에서 Profile 확장자없는 설정파일이 필요
- 위 Procfile에서 web processes running 시키는 라인이 추가되어야 함
- 스프링에서 작업해서 배포한다는 의미 : ALL or Not ALL
- PHP에서는 작업한 개별파일 1개씩 수정해서 올리는 방식이 아님 (워드프레스, 그누보드가 아님)
- 스프링은 모든파일을 컴파일해서 패키징(war파일)한 후, 업로드합니다

- Heroku Cloud에서 App생성
- 우리 프로젝트에 HsqlDB를 생성 (Maven에서 Hsql모듈을 업데이트하면, 사용가능)
- 오라클은 로컬에서 개발, HsqlDB는 Heroku Cloud용을 개발할 수 있도록 root-context.xml에서 설정예정
- 현재까지 작업한 소스를 (자기 성함)kimsanghoon-spring5.herokuapp.com으로 배포할 예정
- 아래 3가지가 root-context.xml에 추가됩니다.
- 1. hsql용 jdbc드라이버를 스프링빈으로 생성하기
- 2. DB생성 스크립트 실행
- 3. DB매니저 실행하기
- 관리자단 대시보드 작업예정
- 사용자단 게시물관리 CRUD 작업예정
- 사용자단 메인페이지(대시보드) 작업예정