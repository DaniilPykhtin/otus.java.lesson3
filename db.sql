
create database pykhtin_dd;

create sequence pykhtin_dd.test_seq
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000
increment by 1
nocache;


create sequence pykhtin_dd.question_seq
minvalue 1
maxvalue 9999999999999999999999999999
start with 1000
increment by 1
nocache;

-- create table
create table pykhtin_dd.test_table
(
  test_id   number default "pykhtin_dd"."test_seq"."nextval" not null,
  test_name varchar2(256) not null
)
-- create/recreate primary, unique and foreign key constraints 
alter table pykhtin_dd.test_table
  add constraint pk_test_id primary key (test_id);


-- create table
create table pykhtin_dd.test_questions
(
  test_id       number not null,
  question_id   number default "pykhtin_dd"."question_seq"."nextval" not null,
  question_num  number not null,
  question_text varchar2(512) not null
)
-- create/recreate primary, unique and foreign key constraints 
alter table pykhtin_dd.test_questions
  add constraint pk_question_id primary key (question_id)
alter table pykhtin_dd.test_questions
  add constraint question_test_fk foreign key (test_id)
  references test_table (test_id) on delete cascade;
-- create/recreate indexes 
create index question_id_idx on pykhtin_dd.test_questions (test_id)

-- create table
create table pykhtin_dd.test_answers
(
  question_id number not null,
  answer_text varchar2(256) not null,
  answer_num  number(1) not null,
  is_correct  char(1) not null
)

-- create/recreate primary, unique and foreign key constraints 
alter table pykhtin_dd.test_answers
  add constraint answer_question_fk foreign key (question_id)
  references pykhtin_dd.test_questions (question_id) on delete cascade;
-- create/recreate check constraints 
alter table pykhtin_dd.test_answers
  add constraint is_correct_check
  check (is_correct in ('y', 'n'));
-- create/recreate indexes 
create index idx_question_id on pykhtin_dd.test_answers (question_id)
