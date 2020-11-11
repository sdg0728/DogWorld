
/* Drop Tables */

DROP TABLE card CASCADE CONSTRAINTS;
DROP TABLE diary CASCADE CONSTRAINTS;
DROP TABLE diet CASCADE CONSTRAINTS;
DROP TABLE friends CASCADE CONSTRAINTS;
DROP TABLE purchase CASCADE CONSTRAINTS;
DROP TABLE review CASCADE CONSTRAINTS;
DROP TABLE goods CASCADE CONSTRAINTS;
DROP TABLE pet CASCADE CONSTRAINTS;
DROP TABLE post_like CASCADE CONSTRAINTS;
DROP TABLE p_comment CASCADE CONSTRAINTS;
DROP TABLE post CASCADE CONSTRAINTS;
DROP TABLE schedule CASCADE CONSTRAINTS;
DROP TABLE user_info CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE card
(
	card_num varchar2(20) UNIQUE,
	cvc number,
	card_lim_year number,
	card_lim_month number,
	password number,
	user_id varchar2(20) NOT NULL
);


CREATE TABLE diary
(
	-- 다이어리 번호
	diary_no number NOT NULL,
	title varchar2(100) NOT NULL,
	-- 다이어리 날짜
	regdate date NOT NULL,
	content clob NOT NULL,
	-- 다이어리의 이미지 
	img varchar2(100),
	user_id varchar2(20) NOT NULL,
	PRIMARY KEY (diary_no)
);


CREATE TABLE diet
(
	weight number NOT NULL,
	regdate date NOT NULL,
	pet_id number NOT NULL
);


CREATE TABLE friends
(
	follower varchar2(20) NOT NULL,
	following varchar2(20) NOT NULL
);


CREATE TABLE goods
(
	goods_id number NOT NULL,
	name varchar2(100) NOT NULL,
	price number NOT NULL,
	detail clob,
	detail_img varchar2(100),
	img varchar2(100) NOT NULL,
	-- 사료, 간식, 옷, 미용용품
	category number,
	count number NOT NULL,
	PRIMARY KEY (goods_id)
);


CREATE TABLE pet
(
	pet_id number NOT NULL,
	name varchar2(20) NOT NULL,
	age number,
	kind varchar2(20),
	img varchar2(100),
	user_id varchar2(20) NOT NULL,
	PRIMARY KEY (pet_id)
);


CREATE TABLE post
(
	post_id number NOT NULL,
	content clob,
	img varchar2(100),
	postdate timestamp DEFAULT sysdate,
	PRIMARY KEY (post_id)
);


CREATE TABLE post_like
(
	post_id number NOT NULL,
	user_id varchar2(20) NOT NULL,
	UNIQUE (post_id, user_id)
);


CREATE TABLE purchase
(
	user_id varchar2(20) NOT NULL,
	goods_id number NOT NULL,
	name varchar2(20) NOT NULL,
	deliver varchar2(100) NOT NULL,
	phone varchar2(20) NOT NULL,
	buydate timestamp DEFAULT sysdate,
	p_check number NOT NULL
);


CREATE TABLE p_comment
(
	content clob,
	regdate timestamp,
	post_id number NOT NULL,
	user_id varchar2(20) NOT NULL
);


CREATE TABLE review
(
	user_id varchar2(20) NOT NULL,
	goods_id number NOT NULL,
	content clob,
	image varchar2(100),
	score number,
	regdate date DEFAULT sysdate,
	UNIQUE (user_id, goods_id)
);


CREATE TABLE schedule
(
	-- 스케줄 번호
	-- 
	schedule_no number NOT NULL,
	content clob,
	-- 스케줄 시작 시간
	start_date timestamp NOT NULL,
	-- 스케줄의 마지막 시간
	end_date timestamp NOT NULL,
	-- 스케줄 완료 체그
	s_check number DEFAULT 0,
	user_id varchar2(20) NOT NULL,
	PRIMARY KEY (schedule_no)
);


CREATE TABLE user_info
(
	user_id varchar2(20) NOT NULL,
	password varchar2(20) NOT NULL,
	name varchar2(100) NOT NULL,
	address varchar2(100) NOT NULL,
	email varchar2(100) NOT NULL UNIQUE,
	phone varchar2(20) NOT NULL,
	PRIMARY KEY (user_id)
);



/* Create Foreign Keys */

ALTER TABLE purchase
	ADD FOREIGN KEY (goods_id)
	REFERENCES goods (goods_id)
;


ALTER TABLE review
	ADD FOREIGN KEY (goods_id)
	REFERENCES goods (goods_id)
;


ALTER TABLE diet
	ADD FOREIGN KEY (pet_id)
	REFERENCES pet (pet_id)
;


ALTER TABLE post_like
	ADD FOREIGN KEY (post_id)
	REFERENCES post (post_id)
;


ALTER TABLE p_comment
	ADD FOREIGN KEY (post_id)
	REFERENCES post (post_id)
;


ALTER TABLE card
	ADD FOREIGN KEY (user_id)
	REFERENCES user_info (user_id)
;


ALTER TABLE diary
	ADD FOREIGN KEY (user_id)
	REFERENCES user_info (user_id)
;


ALTER TABLE friends
	ADD FOREIGN KEY (following)
	REFERENCES user_info (user_id)
;


ALTER TABLE friends
	ADD FOREIGN KEY (follower)
	REFERENCES user_info (user_id)
;


ALTER TABLE pet
	ADD FOREIGN KEY (user_id)
	REFERENCES user_info (user_id)
;


ALTER TABLE post_like
	ADD FOREIGN KEY (user_id)
	REFERENCES user_info (user_id)
;


ALTER TABLE purchase
	ADD FOREIGN KEY (user_id)
	REFERENCES user_info (user_id)
;


ALTER TABLE p_comment
	ADD FOREIGN KEY (user_id)
	REFERENCES user_info (user_id)
;


ALTER TABLE review
	ADD FOREIGN KEY (user_id)
	REFERENCES user_info (user_id)
;


ALTER TABLE schedule
	ADD FOREIGN KEY (user_id)
	REFERENCES user_info (user_id)
;



/* Comments */

COMMENT ON COLUMN diary.diary_no IS '다이어리 번호';
COMMENT ON COLUMN diary.regdate IS '다이어리 날짜';
COMMENT ON COLUMN diary.img IS '다이어리의 이미지 ';
COMMENT ON COLUMN goods.category IS '사료, 간식, 옷, 미용용품';
COMMENT ON COLUMN schedule.schedule_no IS '스케줄 번호
';
COMMENT ON COLUMN schedule.start_date IS '스케줄 시작 시간';
COMMENT ON COLUMN schedule.end_date IS '스케줄의 마지막 시간';
COMMENT ON COLUMN schedule.s_check IS '스케줄 완료 체그';

ALTER TABLE USER_INFO MODIFY address varchar2(200);

CREATE SEQUENCE PET_SEQ;

CREATE SEQUENCE DIARY_SEQ;
CREATE SEQUENCE SCHEDULE_SEQ;

SELECT * FROM USER_INFO;
SELECT * FROM DIARY;
SELECT * FROM SCHEDULE;

SELECT * FROM pet;

SELECT * FROM user_info WHERE EMAIL = 'aaa123@naver.com';

UPDATE user_info SET EMAIL = 'sopsop111@naver.com' WHERE user_id = 'aaa123';

SELECT * FROM user_info WHERE user_id  = 'aaa123' AND email = 'aaa123@naver.com' AND phone = '01011112222';

SELECT * FROM diet ORDER BY REGDATE;

SELECT * FROM diet WHERE pet_id = 21 ORDER BY REGDATE;

SELECT WEIGHT, REGDATE, PET_ID FROM(SELECT * FROM DIET WHERE pet_id= 21 ORDER BY regdate DESC) WHERE ROWNUM = 1;

SELECT * FROM user_info WHERE user_id = 'ddd234';

DELETE FROM user_info WHERE user_id = 'bbb123' AND password = '1234';

DELETE FROM DIET WHERE (DIET.WEIGHT, DIET.REGDATE, DIET.PET_ID ) = (SELECT WEIGHT, REGDATE, PET_ID FROM(SELECT * FROM DIET WHERE pet_id= 21 ORDER BY regdate DESC) WHERE ROWNUM = 1);

INSERT INTO diet (WEIGHT, REGDATE, PET_ID) VALUES (6, '2020-6-1', 21); 

INSERT INTO diet (WEIGHT, REGDATE, PET_ID) VALUES (6, '2020-6-1', 28);
INSERT INTO diet (WEIGHT, REGDATE, PET_ID) VALUES (9, '2020-7-1', 28);
INSERT INTO diet (WEIGHT, REGDATE, PET_ID) VALUES (10, '2020-5-1', 28);
INSERT INTO diet (WEIGHT, REGDATE, PET_ID) VALUES (12, '2020-4-1', 28);
INSERT INTO diet (WEIGHT, REGDATE, PET_ID) VALUES (13, '2020-3-1', 28);
INSERT INTO diet (WEIGHT, REGDATE, PET_ID) VALUES (10, '2020-2-1', 28);
INSERT INTO diet (WEIGHT, REGDATE, PET_ID) VALUES (8, '2020-9-1', 28);
INSERT INTO diet (WEIGHT, REGDATE, PET_ID) VALUES (10, '2020-10-1', 28);


SELECT * FROM SCHEDULE WHERE user_id = 'aaa123' AND S_CHECK = 0 AND TO_CHAR(TRUNC(SYSDATE), 'YYYYMMDD') >= TO_CHAR(START_DATE,'YYYYMMDD') AND TO_CHAR(TRUNC(SYSDATE), 'YYYYMMDD HH24MISS') < TO_CHAR(END_DATE,'YYYYMMDD HH24MISS') ;

-- HH24MISS

DELETE FROM user_info WHERE user_id = 'aaa123';
DELETE FROM pet WHERE user_id = 'aaa123';
DELETE FROM SCHEDULE WHERE user_id = 'aaa123';

SELECT * FROM pet WHERE user_id = 'aaa123';

SELECT * FROM diary WHERE title = 'ㅇㄷㅇㄷ';
DELETE FROM diary WHERE title = 'ㅇㄷㅇㄷ';
SELECT user_id FROM user_info WHERE user_id = 'aaa123';

