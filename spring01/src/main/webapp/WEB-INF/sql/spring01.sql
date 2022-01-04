-- 회원 테이블
create table tbl_member(
  	userid varchar2(50) primary key,
  	userpw varchar2(50) not null,
  	username varchar2(50) not null,
  	email varchar2(100),
  	regdate timestamp not null default sysdate,
  	updatedate timestamp not null default sysdate
);

-- 특정 회원 정보보기
select * from tbl_member
where userid = 'user01';

-- 회원 전체 목록
select * from tbl_member
order by userid;

-- 회원 정보 수정
update tbl_member set
	userpw = '5678',
	username = '사용자001',
	email = 'user001@email.com',
	updatedate = sysdate
where userid = 'user01';

rollback;

-- 회원 정보 삭제
delete from tbl_member
where userid = 'user01';

-- 로그인
select * from tbl_member
where userid = 'hong2'
and userpw = '1234';

-- 게시판 테이블
create table tbl_board(
	bno number primary key, -- 글번호(seq_bno)
	title varchar2(200) not null, -- 글제목
	content varchar2(4000), -- 글내용
	userid varchar2(50) references tbl_member(userid), -- 작성자아이디
	regdate timestamp default sysdate, -- 작성일
	viewcnt number default 0 -- 조회수 
);

-- 글번호 자동생성을 위한 시퀀스
create sequence seq_bno;

-- 게시판 테이블에 게시글 2개 삽입
insert into tbl_board(bno, title, content, userid)
values(seq_bno.nextval, '제목1', '내용1', 'user01');

insert into tbl_board(bno, title, content, userid)
values(seq_bno.nextval, '제목2', '내용2', 'user01');

commit;

-- 최신글이 먼저 나오도록 정렬
select * from tbl_board
order by bno desc;

-- 게시판 테이블의 데이터 삭제
truncate table tbl_board;
-- 시퀀스 삭제 후 다시 생성
drop sequence seq_bno;
create sequence seq_bno;

-- 데이터 500개 생성
begin
	for i in 1..500 loop
		insert into tbl_board(bno, title, content, userid)
		values(	seq_bno.nextval, 
				'제목' || i,
				'내용' || i,
				'user01');
	end loop;
	
end;
/

select * from tbl_board
order by bno desc;

select count(*) from tbl_board;

commit;

-- 10개만 가져오기
select * from 
		(select rownum rnum, a.* from 
				(select * from tbl_board
				 order by bno desc) a)
where rnum between 1 and 10;

-- 조회수 증가
update tbl_board set
	viewcnt = viewcnt + 1
where bno = ?

-- 검색
select * from tbl_board
where title like '%제목4%'
order by bno desc

-- 답글 관련 컬럼 추가
alter table tbl_board
add re_group number default 0; -- 글그룹

alter table tbl_board
add re_seq number default 0; -- 같은 글그룹내에서 몇번째(순서)

alter table tbl_board
add re_level number default 0; -- 원글,답글,답답글(들여쓰기용)

-- 기존 데이터의 글그룹을 원글(글번호)로 변경
update tbl_board set
	re_group = bno;
	
commit;

-- 답글 데이터 추가
insert into tbl_board 
	(bno, title, content, userid, re_group, re_seq, re_level)
values
	(seq_bno.nextval, 're:제목 501', '답글내용', 'user02', 501, 1, 1);


select * from tbl_board
order by bno desc;

select * from tbl_board
order by re_group desc, re_seq asc;

insert into tbl_board 
	(bno, title, content, userid, re_group, re_seq, re_level)
values
	(seq_bno.nextval, 're:제목 500', '답글내용', 'user02', 500, 1, 1);
	
commit;

-- 기존 글에 대해서 re_seq 변경
update tbl_board set
	re_seq = re_seq + 1
where re_group = ?
and re_seq > ?

-- 댓글 테이블
create table tbl_comment(
	cno number primary key,
	bno number references tbl_board(bno),
	userid varchar2(50) references tbl_member(userid),
	content varchar2(100) not null,
	regdate timestamp default sysdate
);

-- 댓글번호용 시퀀스
create sequence seq_cno;

-- 댓글 테스트용 데이터 추가
insert into tbl_comment(cno, bno, userid, content)
values(seq_cno.nextval, 523, 'user01', '댓글1');
insert into tbl_comment(cno, bno, userid, content)
values(seq_cno.nextval, 523, 'user01', '댓글2');
commit;
select * from tbl_comment;

-- 댓글 삭제
delete from tbl_comment
where cno = 1;

-- 댓글 수정
update tbl_comment set
	content = ?,
	userid = ?
where cno = ?

-- 쪽지 테이블
create table tbl_message(
	mno number primary key,
	sender varchar2(50) references tbl_member(userid),
	receiver varchar2(50) references tbl_member(userid),
	message varchar2(200) not null,
	senddate timestamp default sysdate,
	opendate timestamp
);

-- 쪽지번호 시퀀스
create sequence seq_mno;

-- 쪽지 테스트 데이터
insert into tbl_message(mno, sender, receiver, message)
values(seq_mno.nextval, 'user01', 'user02', 'hello');

insert into tbl_message(mno, sender, receiver, message)
values(seq_mno.nextval, 'user02', 'user01', 'hi~~');

select * from tbl_message;

commit;

-- 쪽지를 보내면 보낸사람에게 포인트 +10점
-- 쪽지를 읽으면 읽은사람에게 포인트 +5점

-- 회원 테이블에 포인트 컬럼 추가
alter table tbl_member 
add (mpoint number default 0 );

-- 포인트 카테고리
-- 1001:쪽지보내기, 1002:쪽지읽기
create table tbl_point_cate(
	point_code varchar2(4) primary key,
	point_desc varchar2(20) not null
);

insert into tbl_point_cate(point_code, point_desc)
values('1001', '쪽지보내기');
insert into tbl_point_cate(point_code, point_desc)
values('1002', '쪽지읽기');
commit;

select * from tbl_point_cate;

-- 포인트 테이블
create table tbl_point(
	pno number primary key,
	userid varchar2(50) references tbl_member(userid),
	point_code varchar2(4) references tbl_point_cate(point_code),
	point_score number default 0,
	point_date timestamp default sysdate
);

create sequence seq_pno;

-- user01 -> user02 메시지 보내기
-- tbl_message에 추가
-- tbl_member에 user01에게 포인트 +10
-- tbl_point에 포인트 내역 추가

update tbl_member set
	mpoint = 0;
truncate table tbl_message;
truncate table tbl_point;

-- MessageService 테스트
select * from tbl_message;
select * from tbl_member where userid ='user01';
select * from tbl_point;

-- board 테이블에 comment_cnt 컬럼 추가
-- select count(*) from tbl_comment
-- where bno = 500;

alter table tbl_board 
add (comment_cnt number default 0);

-- 기존에 등록된 댓글 삭제
truncate table tbl_comment;

update tbl_board set
	comment_cnt = comment_cnt + 1
where bno = 529;

select * from tbl_board
where bno = 529;

-- 첨부파일 테이블
create table tbl_attach(
	file_name varchar2(100) primary key,
	bno number references tbl_board(bno)
);

-- 첨부파일 등록 확인
select * from tbl_attach
where bno = 530;

-- 글 삭제
delete from tbl_board
where bno = 530;

-- 해당 글의 첨부파일 데이터
delete from tbl_attach
where bno = 530;

-- /upload 에 저장된 파일 삭제
select * from tbl_attach
where bno = bno;

select count(*) from tbl_member
where userid = 'hong';









