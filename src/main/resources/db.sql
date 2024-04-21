-- 회원 테이블
create table company_member(
    member_idx       int PRIMARY KEY AUTO_INCREMENT,
    member_id      varchar(20),
    member_pw     varchar(20),
    member_name   varchar(20),
    member_email   varchar(100),
    member_email_receive   int,
    member_pw_question   int,
    member_pw_answer   varchar(100),
    member_gender   varchar(10),
    member_birth_date      date,
    member_join_date      date DEFAULT (current_date)
);

-- 관리자 테이블
create table company_member_admin(
    member_idx       int PRIMARY KEY AUTO_INCREMENT,
    member_id      varchar(20),
    member_pw     varchar(20),
    member_name   varchar(20),
    member_email   varchar(100),
    member_join_date      date DEFAULT (current_date)
);

-- 공지사항 테이블
create table company_notice(
    notice_idx      int PRIMARY KEY AUTO_INCREMENT,
    notice_title    varchar(100),
    notice_content  varchar(2000),
    notice_member_id     varchar(20),
    notice_date     date DEFAULT (current_date)
);

-- 홍보자로(뉴스) 테이블
create table company_news(
    news_idx      int PRIMARY KEY AUTO_INCREMENT,
    news_title    varchar(100),
    news_content  varchar(2000),
    news_member_id     varchar(20),
    news_date     date DEFAULT (current_date)
);

-- 채용정보 테이블
create table company_job(
    job_idx     int PRIMARY KEY AUTO_INCREMENT,
    job_title    varchar(100),
    job_content  varchar(2000),
    job_member_id     varchar(20),
    job_date     date DEFAULT (current_date)
);

-- faq 테이블
create table company_faq(
    faq_idx      int PRIMARY KEY AUTO_INCREMENT,
    faq_title    varchar(100),
    faq_content  varchar(2000)
);

-- 1:1 문의 테이블
create table company_one2one(
    one2one_idx     int PRIMARY KEY AUTO_INCREMENT,
    one2one_name     varchar(20), -- 고객이름
    one2one_phone    varchar(20),
    one2one_email     varchar(100),
    one2one_address   varchar(200),
    one2one_title    varchar(100),
    one2one_content  varchar(2000),
    one2one_date     date DEFAULT (current_date)
);

-- 1:1 문의 답글
create table company_one2one_reply(
    one2one_reply_idx      int PRIMARY KEY AUTO_INCREMENT,
    one2one_reply_content  varchar(2000),
    one2one_reply_name     varchar(20), -- 답글단사람
    one2one_reply_date     date DEFAULT (current_date),
    one2one_reply_one2one_idx      int -- 문의글의 인덱스
);

-- 묻고답하기 (비밀글)
create table company_qna(
    qna_idx      int PRIMARY KEY AUTO_INCREMENT,
    qna_name     varchar(20),
    qna_pw       varchar(20),
    qna_title    varchar(100),
    qna_content  varchar(2000),
    qna_date     date DEFAULT (current_date)
);

-- 묻고답하기 (비밀글) 답글
create table company_qna_reply(
    qna_reply_idx      int PRIMARY KEY AUTO_INCREMENT,
    qna_reply_content  varchar(2000),
    qna_reply_name     varchar(20), -- 답글단사람
    qna_reply_date     date DEFAULT (current_date),
    qna_reply_qna_idx  int -- 문의글의 인덱스
);

-- 제품 목록
create table company_product(
    product_idx      int PRIMARY KEY AUTO_INCREMENT,
    product_name     varchar(20),
    product_content  varchar(2000),
    product_img      varchar(100),
    product_reg_name     varchar(20),
    product_date     date DEFAULT (current_date)
);





-- 주어진 SQL ////////////////////////////////////////////////////////////////////////


CREATE DATABASE IF NOT EXISTS testdb;

USE testdb;

drop table if exists company_member;

create table company_member(
    member_idx       int PRIMARY KEY AUTO_INCREMENT,
    member_id      varchar(20),
    member_pw     varchar(20),
    member_name   varchar(20),
    member_email   varchar(100),
    member_email_receive   int, -- 이메일 수신 여부입니다. 0이면 안받는다, 1이면 받는다. 입니다.
    member_pw_question   int,
    member_pw_answer   varchar(100),
    member_gender   varchar(10),
    member_birth_date      date,
    member_join_date      date DEFAULT (current_date)
);
DESC company_member;

insert into company_member(member_idx, member_id, member_pw, member_name, member_email, member_email_receive, member_pw_question, member_pw_answer, member_gender, member_birth_date, member_join_date)
values (0, 'hong', '1234', '홍길동', 'test@gmail.com', 0,0,0,'male','2000/01/01', now());

select * from company_member;


-- 관리자 회원테이블
drop table if exists company_member_admin;
-- member_email_receive	0: 비수신 1: 수신
create table company_member_admin(
    member_idx       int PRIMARY KEY AUTO_INCREMENT,
    member_id      varchar(20),
    member_pw     varchar(20),
    member_name   varchar(20),
    member_email   varchar(100),
    member_join_date      date DEFAULT (current_date)
);

DESC company_member_admin;

insert into company_member_admin
values (0, 'admin', '1234', '관리자', 'admin@gmail.com', now());

select * from company_member_admin;

SELECT COUNT(*) FROM company_member_admin WHERE member_id = 'admin' AND member_pw = '1234';

commit;
-- 공지사항,홍보자료,채용정보, FAQ(자주묻는질문)
-- 1:1문의, 묻고답하기(비밀글),

drop table if exists company_notice;
create table company_notice(
    notice_idx      int PRIMARY KEY AUTO_INCREMENT,
    notice_title    varchar(100),
    notice_content  varchar(2000),
    notice_member_id     varchar(20),
    notice_date     date DEFAULT (current_date)
);

insert into company_notice(notice_idx, notice_title, notice_content, notice_member_id, notice_date)
   values ( 0, '타이틀1', '내용입니다.1', 'admin', now()  );
insert into company_notice(notice_idx, notice_title, notice_content, notice_member_id, notice_date)
   values ( 0, '타이틀1', '내용입니다.1', 'admin', DATE_ADD(NOW(), INTERVAL 1 DAY)  );
insert into company_notice(notice_idx, notice_title, notice_content, notice_member_id, notice_date)
   values ( 0, '타이틀1', '내용입니다.1', 'admin', DATE_ADD(NOW(), INTERVAL 2 DAY)  );

select * from company_notice;

commit;

-- 홍보자료(뉴스)
drop table company_news;
create table company_news(
    news_idx      int PRIMARY KEY AUTO_INCREMENT,
    news_title    varchar(100),
    news_content  varchar(2000),
    news_member_id     varchar(20),
    news_date     date DEFAULT (current_date)
);

insert into company_news
   values ( 0, '뉴스제목1', '뉴스내용입니다.1', 'admin', now()  );

SELECT * FROM company_news;

-- 채용정보
drop table if exists company_job;
create table company_job(
    job_idx     int PRIMARY KEY AUTO_INCREMENT,
    job_title    varchar(100),
    job_content  varchar(2000),
    job_member_id     varchar(20),
    job_date     date DEFAULT (current_date)
);

insert into company_job
   values ( 0, '채용정보제목1', '채용정보내용입니다.1', 'admin', now()  );

SELECT * FROM company_job;

commit;

-- FAQ
drop table if exists company_faq;
create table company_faq(
    faq_idx      int PRIMARY KEY AUTO_INCREMENT,
    faq_title    varchar(100),
    faq_content  varchar(2000)
);

insert into company_faq values
		(0,'제목1','내용1');

select * from company_faq;

commit;

SELECT VERSION()

-- 1:1문의
drop table if exists company_one2one;
create table company_one2one(
    one2one_idx     int PRIMARY KEY AUTO_INCREMENT,
    one2one_name     varchar(20), -- 고객이름
    one2one_phone    varchar(20),
    one2one_email     varchar(100),
    one2one_address   varchar(200),
    one2one_title    varchar(100),
    one2one_content  varchar(2000),
    one2one_date     date DEFAULT (current_date)
);

insert into company_one2one values
		(0,'홍길동','010-1111-2222','hong@gmail.com','한양','제목1','내용1', now());

select * from company_one2one;

commit;

-- 1:1문의 답글

drop table if exists company_one2one_reply;
create table company_one2one_reply(
    one2one_reply_idx      int PRIMARY KEY AUTO_INCREMENT,
    one2one_reply_content  varchar(2000),
    one2one_reply_name     varchar(20), -- 답글단사람
    one2one_reply_date     date DEFAULT (current_date),
    one2one_reply_one2one_idx      int -- 문의글의 인덱스
);

insert into company_one2one_reply values
		(0,'1:1문의답글내용','admin', now(), 1);

select * from company_one2one_reply;

commit;

-- 묻고답하기(비밀글)
drop table if exists company_qna;
create table company_qna(
    qna_idx      int PRIMARY KEY AUTO_INCREMENT,
    qna_name     varchar(20),
    qna_pw       varchar(20),
    qna_title    varchar(100),
    qna_content  varchar(2000),
    qna_date     date DEFAULT (current_date)
);

insert into company_qna values
		(0,'홍길동','1234','제목1','내용1', now());

select * from company_qna;

commit;

-- 묻고답하기(비밀글) 답글

drop table if exists company_qna_reply;
create table company_qna_reply(
    qna_reply_idx      int PRIMARY KEY AUTO_INCREMENT,
    qna_reply_content  varchar(2000),
    qna_reply_name     varchar(20), -- 답글단사람
    qna_reply_date     date DEFAULT (current_date),
    qna_reply_qna_idx  int -- 문의글의 인덱스
);

insert into company_qna_reply values
		(0,'QNA문의 답글내용','admin', now(), 1);

select * from company_qna_reply;

commit;


-- 제품목록
drop table if exists company_product;
create table company_product(
    product_idx      int PRIMARY KEY AUTO_INCREMENT,
    product_name     varchar(20),
    product_content  varchar(2000),
    product_img      varchar(100),
    product_reg_name     varchar(20),
    product_date     date DEFAULT (current_date)
);

insert into company_product values
		(0,'제품이름1','제품설명1','/img/product/1.jpg','admin',now());

select * from company_product;

commit;