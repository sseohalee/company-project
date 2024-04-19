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

