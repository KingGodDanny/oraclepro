Drop table person;
DROP SEQUENCE seq_person_id;

--person ���̺� ����� ( Primary key ����, Not Null ���� )
create table person (
    person_id number(5),
    name VARCHAR2(30) not null,
    hp VARCHAR2(20),
    company VARCHAR2(20),
    PRIMARY KEY (person_id)
);

-- Sequence �����  (�����̸Ӹ� Ű-person_id�� 1�� �����ϰԲ�)
create SEQUENCE seq_person_id
INCREMENT BY 1 
START WITH 1;

-- Insert �� 
INSERT INTO person VALUES(seq_person_id.nextval, '��ȿ��', '010-1111-1111', '02-1111-1111');
INSERT INTO person VALUES(seq_person_id.nextval, '���켺', '010-2222-2222', '02-2222-2222');
INSERT INTO person VALUES(seq_person_id.nextval, '���缮', '010-3333-3333', '02-3333-3333');
INSERT INTO person VALUES(seq_person_id.nextval, '������', '010-4444-4444', '02-4444-4444');
INSERT INTO person VALUES(seq_person_id.nextval, '������', '010-5555-5555', '02-5555-5555');


--Select ��
select person_id,
       name,
       hp,
       company
from person
order by person_id asc;


--Update ��
update person
set hp = '010-9999-9999',
    company = '02-9999-9999';
    
--Delete ��
DELETE FROM person
WHERE person_id = 5; 