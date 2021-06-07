-- 20장 Sequence 시퀸스

-- 19장 사용자 추가
-- 오라클 데스크탑 (X)
-- SQL+PLUS (X)
-- 대신 CreateWorkSpace = 오라클11g express의 Get_Started 바로가기 파일(http://127.0.0.1:9000/apex/f?p=4950) 사용

-- 15장 데이터 무결성 제약조건(constraint)
-- NOT NULL(빈 값이 있으면 안됨), UNIQUE (중복이 되면 안됨) [PK는 자동으로 NOT NULL, UNIQUE 제약조건이 붙음 INDEX도 자동생성 검색시 중요]
-- FK(Foreign Key): 외래키 부자 관계


-- 14장 transaction DB단에서 사용하지 않고, 스프링단에서 트랜잭션을 사용할 예정
-- (DCL문)commit과 rollback (e.g. DML문: select insert, update, delete)
-- 스프링단에서 트랜잭션을 사용 @Transactional 인터페이스 사용

-- 12장 테이블 구조생성(CREATE),변경(ALTER),삭제(DROP)
-- ERD (Entity Relation Diagram)을 포워드엔지니어링으로 물리적 테이블을 생성했었음

CREATE TABLE "XE"."TBL_MEMBER_DEL"
(
USER_ID VARCHAR(50) PRIMARY KEY,
USER_PW VARCHAR(255),
USER_NAME VARCHAR(50),
EMAIL VARCHAR(255),
POINT NUMBER(11),
ENABLED NUMBER(1),
LEVELS VARCHAR(255),
REG_DATE TIMESTAMP,
UPDATE_DATE TIMESTAMP
);
-- ALTER 테이블로 필드명 변경(아래)
-- DEPT02 테이블의 deptno 숫자 2자리 때문에 에러가 남 -> 4자리고 크기를 변경
-- 바꾸기전에 확인해보기
DESC dept;

ALTER TABLE dept MODIFY(deptno NUMBER(4));

DESC tbl_member_del;

ALTER TABLE tbl_member_del RENAME COLUMN email TO user_email;

DROP TABLE tbl_member_del;


-- 11장 서브쿼리
-- 단일행 서브쿼리 필드값을 비교할 때, 비교하는 값이 단일인 값
-- 다중행 서브쿼리 테이블값을 SELECT쿼리로 생성(레코드값)


-- 10장 테이블 조인 2개의 테이블을 연결해서 결과를 구하는(e.g. 외래키 관계)
-- 댓글갯수 구할 때~
-- CARTESIAN PRODUCT JOIN (합&곱집합 게시물10개, 댓글100개 => 110개~1000개 언저리로 나옴 나옴) // 안쓰는 조인임
-- INNER JOIN 제일 많이 사용합니다.(교집합)
-- 아래 조인방식이 오라클방식임
SELECT e.*, d.* FROM emp e, dept d WHERE e.deptno = d.deptno;

SELECT * FROM emp, dept WHERE emp.deptno = dept.deptno; 

-- 표준쿼리(ANSI)방식 (아래)
SELECT * FROM emp e JOIN dept d 
ON e.deptno = d.deptno;

-- 표준방식
SELECT d.dname , e.* FROM emp e JOIN dept d  -- JOIN == INNER JOIN JOIN의 DEFAULT값이 INNER JOIN임
ON e.deptno = d.deptno WHERE e.ename = 'SCOTT';
-- 오라클방식 주로 표준방식 채택
SELECT d.dname, e.* FROM emp e, dept d 
WHERE e.deptno = d.deptno AND e.ename = 'SCOTT';

-- 조인과 그룹을 이용해서 댓글카운터까지 출력하는 게시판리스트 만들기
SELECT bod.bno, title, count(*) as reply_count , writer, bod.reg_date, view_count
FROM tbl_board BOD
INNER JOIN tbl_reply REP ON BOD.bno = REP.bno
GROUP BY bod.bno, title, writer, bod.reg_date, view_count
ORDER BY bod.bno;

--9장 패스 (레포트용 함수사용)
--8장 함수 ( count, upper, lower, to_char, round...)

-- 부서별 연봉의 합계를 구해서 제일 급여가 많이 지출되는 부서(아래)
-- 자바코딩에서는 소문자로 통일합니다. select
-- DB셋팅에서 대소문자구분해서 사용할지, 구분하지 않을 지 셋팅
SELECT B.* FROM (
SELECT deptno, SUM(sal) AS dept_sal FROM emp GROUP BY deptno
) B ORDER BY dept_sal DESC;

SELECT deptno, SUM(sal) AS dept_sal FROM emp GROUP BY deptno ORDER BY dept_sal DESC;

-- HAVING은 GROUP BY의 조건문을 적습니다.
-- 부서별 평균 연봉이 2000인 부서의 번호와 부서별 평균 연봉

SELECT deptno, ROUND(AVG(sal)) AS avg_sal FROM emp GROUP BY deptno HAVING ROUND(AVG(sal)) > 2000;



-- round함수는 반올림 함수 소수점 기준으로함 "*음수는 앞으로 양수는 뒤로*" round(10.05, 2) -> 10.1 // round(1700, -3) -> 2000
SELECT ename, round(sal, -3) FROM emp;

SELECT SUM(sal) FROM emp;
SELECT round(AVG(sal)) FROM emp;
SELECT MIN(sal), MIN(sal), MAX(sal)-MIN(sal) FROM emp;


SELECT A.ename AS "최소급여자", A.sal AS "연봉" FROM emp A WHERE A.sal = (SELECT MIN(B.sal) FROM emp B);

-- 아래 쿼리는 사원중에 평균연봉보다 많이/적게 받는 사람의 숫자
SELECT COUNT(*) FROM emp A 
WHERE sal >= (SELECT round(AVG(B.sal)) FROM emp B);

SELECT COUNT(*) FROM emp A 
WHERE sal <= (SELECT round(AVG(B.sal)) FROM emp B);




--DDL문(create; alter; drop;), DCL문(commit; rollback;)
--DML문(Data Manufacture Language) insert,update,delete
--insert문:테이블에 새로운 레코드(row)를 추가
CREATE TABLE dept02 AS SELECT * FROM dept WHERE 1=0;
--위 쿼리는 테이블을 복제하는 명령
--위처럼 쿼리를 실행 dept테이블과 구조와 내용이 똑같은 테이블생성
--where 조건이 붙으면, 구조는 같으나 내용은 빈 테이블이 생성
INSERT INTO dept02(
--필드명
deptno, dname, loc
)VALUES(
10, '개발부서', '천안'
--바인딩값
);
--DCL명령어 인 커밋이 필수입니다.
COMMIT;--데이터베이스쿼리직접입력한 결과는 반드시 커밋을 해줘야지만
--실제 저장이 됩니다. 커밋을 하지않으면, 여기만 보이고, 다른곳은X
SELECT * FROM dept02 ORDER BY deptno ;   

--DELETE는 레코드를 한 줄을 지우는 명령
DELETE FROM dept02; -- 이렇게 사용하면 모든 레코드가 삭제됨 주의요망
DELETE FROM dept02 WHERE deptno >= 0; -- 조건을 만족하는 레코드 삭제 where 반드시 포함

-- DROP TABLE 테이블명 : 테이블 자체를 물리적으로 삭제
DROP TABLE dept02; -- DROP TALBE 커밋없이 바로 적용됨 -> DDL문은 커밋이 필요없음

CREATE TABLE emp01 AS SELECT * FROM emp;

SELECT * FROM emp01;

--UPDATE 테이블명 SET 필드명 = '바꿀값'; WHERE 조건이 없으면 모두 바뀜 WHERE empno='특정 ID'
UPDATE emp01 SET ename = '홍길동' WHERE empno = 7839;

ROLLBACK; -- DCL문 롤백은 마지막 커밋 바로전까지로 되돌립니다.

UPDATE emp01 SET sal = sal*1.1; -- 모든직원연봉을 10%인상

UPDATE emp01 SET hiredate = sysdate;

-- MERGE 표준쿼리(ANSI쿼리)가 아니라서 건너뜀