Drop table person;
DROP SEQUENCE seq_person_id;

--person 테이블 만들기 ( Primary key 설정, Not Null 설정 )
create table person (
    person_id number(5),
    name VARCHAR2(30) not null,
    hp VARCHAR2(20),
    company VARCHAR2(20),
    PRIMARY KEY (person_id)
);

-- Sequence 만들기  (프라이머리 키-person_id가 1씩 증가하게끔)
create SEQUENCE seq_person_id
INCREMENT BY 1 
START WITH 1;

-- Insert 문 
INSERT INTO person VALUES(seq_person_id.nextval, '이효리', '010-1111-1111', '02-1111-1111');
INSERT INTO person VALUES(seq_person_id.nextval, '정우성', '010-2222-2222', '02-2222-2222');
INSERT INTO person VALUES(seq_person_id.nextval, '유재석', '010-3333-3333', '02-3333-3333');
INSERT INTO person VALUES(seq_person_id.nextval, '이정재', '010-4444-4444', '02-4444-4444');
INSERT INTO person VALUES(seq_person_id.nextval, '서장훈', '010-5555-5555', '02-5555-5555');


--Select 문
select person_id,
       name,
       hp,
       company
from person
order by person_id asc;


--Update 문
update person
set hp = '010-9999-9999',
    company = '02-9999-9999';
    
--Delete 문
DELETE FROM person
WHERE person_id = 5; 