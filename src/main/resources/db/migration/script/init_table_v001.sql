--TABLE ROLE
CREATE TABLE t_role(
	id serial,
	role_code VARCHAR(5),
	role_name VARCHAR(12),
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_role ADD CONSTRAINT role_pk
	PRIMARY KEY(id);

--TABLE FILE
CREATE TABLE t_file(
	id serial,
	ext VARCHAR(5),
	file_name VARCHAR(15) NOT NULL,
	created_by INT,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_file ADD CONSTRAINT file_pk
	PRIMARY KEY(id);

--TABLE PROFILE
CREATE TABLE t_profile(
	id serial,
	photo_id INT,
	full_name VARCHAR(30),
	address VARCHAR(40),
	phone_no VARCHAR(15),
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_profile ADD CONSTRAINT profile_pk
	PRIMARY KEY(id);

ALTER TABLE t_profile ADD CONSTRAINT photo_id_fk
	FOREIGN KEY(photo_id)
	REFERENCES t_file(id);

ALTER TABLE t_profile ADD CONSTRAINT phone_no_bk
	UNIQUE(phone_no);

--TABLE USER
CREATE TABLE t_user(
	id serial,
	role_id INT NOT NULL,
	profile_id INT,
	user_email TEXT NOT NULL,
	user_password TEXT NOT NULL,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_user ADD CONSTRAINT user_pk
	PRIMARY KEY(id);

ALTER TABLE t_user ADD CONSTRAINT role_id_fk
	FOREIGN KEY(role_id)
	REFERENCES t_role(id);

ALTER TABLE t_user ADD CONSTRAINT profile_id_fk
	FOREIGN KEY(profile_id)
	REFERENCES t_profile(id);

--TABLE CLASS
CREATE TABLE t_classroom(
	id serial,
	classroom_name VARCHAR(30) NOT NULL,
	classroom_code VARCHAR(5) NOT NULL,
	teacher_id INT NOT NULL,
	classroom_photo_id INT NOT NULL,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_classroom ADD CONSTRAINT classroom_pk
	PRIMARY KEY(id);

ALTER TABLE t_classroom ADD CONSTRAINT classroom_code_bk
	UNIQUE(classroom_code);	

ALTER TABLE t_classroom ADD CONSTRAINT teacher_id_fk
	FOREIGN KEY(teacher_id)
	REFERENCES t_user(id);

ALTER TABLE t_classroom ADD CONSTRAINT classroom_photo_id_fk
	FOREIGN KEY(classroom_photo_id)
	REFERENCES t_file(id);

--TABLE CLASS_ENROLL
CREATE TABLE t_classroom_enroll(
	id serial,
	classroom_id INT NOT NULL,
	student_id INT NOT NULL,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_classroom_enroll ADD CONSTRAINT classroom_enroll_pk
	PRIMARY KEY(id);

ALTER TABLE t_classroom_enroll ADD CONSTRAINT classroom_id_fk
	FOREIGN KEY(classroom_id)
	REFERENCES t_classroom(id);

ALTER TABLE t_classroom_enroll ADD CONSTRAINT student_id_fk
	FOREIGN KEY(student_id)
	REFERENCES t_user(id);

--TABLE LEARNING 
CREATE TABLE t_elearning(
	id serial,
	elearning_name VARCHAR (30)NOT NULL,
	classroom_id INT NOT NULL,
	elearning_photo_id INT NOT NULL,
	start_date TIMESTAMP NOT NULL,
	end_date TIMESTAMP NOT NULL,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL

);

ALTER TABLE t_elearning ADD CONSTRAINT elearning_pk
	PRIMARY KEY(id);

ALTER TABLE t_elearning ADD CONSTRAINT classroom_id_fk
	FOREIGN KEY(classroom_id)
	REFERENCES t_classroom(id);

ALTER TABLE t_elearning ADD CONSTRAINT elearning_photo_id_fk
	FOREIGN KEY(elearning_photo_id)
	REFERENCES t_file(id);


--TABLE STUDENT_ATTENDANCE
CREATE TABLE t_student_attendance(
	id serial,
	student_id INT NOT NULL,
	elearning_id INT NOT NULL,
	attend_time TIMESTAMP NOT NULL,
	is_approved BOOLEAN NOT NULL,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_student_attendance ADD CONSTRAINT student_attendance_pk
	PRIMARY KEY(id);


ALTER TABLE t_student_attendance ADD CONSTRAINT student_id_fk
	FOREIGN KEY(student_id)
	REFERENCES t_user(id);

ALTER TABLE t_student_attendance ADD CONSTRAINT elearning_id_fk
	FOREIGN KEY(elearning_id)
	REFERENCES t_elearning(id);

--TABLE MATERIAL
CREATE TABLE t_material(
	id serial,
	elearning_id INT NOT NULL,
	material_text TEXT,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_material ADD CONSTRAINT material_pk
	PRIMARY KEY(id);

ALTER TABLE t_material ADD CONSTRAINT elearning_id_fk
	FOREIGN KEY(elearning_id)
	REFERENCES t_elearning(id);

--TABLE MATERIAL DETAIL
CREATE TABLE t_material_detail (
	id serial,
	material_id INT NOT NULL,
	material_file_id INT NOT NULL,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_material_detail ADD CONSTRAINT material_detail_pk
	PRIMARY KEY(id);

ALTER TABLE t_material_detail ADD CONSTRAINT material_id_fk
	FOREIGN KEY(material_id)
	REFERENCES t_material(id);

ALTER TABLE t_material_detail ADD CONSTRAINT material_file_id_fk
	FOREIGN KEY(material_file_id)
	REFERENCES t_file(id);


--TABLE FORUM
CREATE TABLE t_forum(
	id serial,
	forum_body VARCHAR (45) NOT NULL,
	forum_code VARCHAR(5) NOT NULL,
	forum_title VARCHAR(20) NOT NULL,
	elearning_id INT NOT NULL,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_forum ADD CONSTRAINT forum_pk
	PRIMARY KEY(id);

ALTER TABLE t_forum ADD CONSTRAINT forum_code_bk
	UNIQUE(forum_code);

ALTER TABLE t_forum ADD CONSTRAINT elearning_id_fk
	FOREIGN KEY(elearning_id)
	REFERENCES t_elearning(id);

--TABLE COMMENT
CREATE TABLE t_comment(
	id serial,
	forum_id INT NOT NULL,
	user_comment TEXT,
	user_id INT NOT NULL,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_comment ADD CONSTRAINT comment_pk
	PRIMARY KEY(id);

ALTER TABLE t_comment ADD CONSTRAINT forum_id_fk
	FOREIGN KEY(forum_id)
	REFERENCES t_forum(id);

ALTER TABLE t_comment ADD CONSTRAINT user_id_fk
	FOREIGN KEY(user_id)
	REFERENCES t_user(id);

--TABLE TASK
CREATE TABLE t_task(
	id serial,
	task_name VARCHAR(15) NOT NULL,
	elearning_id INT NOT NULL,
	task_start_time TIMESTAMP NOT NULL,
	task_end_time TIMESTAMP NOT NULL,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_task ADD CONSTRAINT task_pk
	PRIMARY KEY(id);

ALTER TABLE t_task ADD CONSTRAINT elearning_id_fk
	FOREIGN KEY(elearning_id)
	REFERENCES t_elearning(id);


--TABLE TASK_QUESTION_TYPE
CREATE TABLE t_question_type (
	id serial,
	question_type_code VARCHAR(5),
	question_type_name VARCHAR(13),
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_question_type ADD CONSTRAINT task_question_type_pk
	PRIMARY KEY(id);

--TABLE t_question
CREATE TABLE t_question (
	id serial, 
	task_id INT NOT NULL,
	question_type_id INT NOT NULL,
	question TEXT,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_question ADD CONSTRAINT question_pk
	PRIMARY KEY(id);

ALTER TABLE t_question ADD CONSTRAINT task_id_fk
	FOREIGN KEY(task_id)
	REFERENCES t_task(id);

ALTER TABLE t_question ADD CONSTRAINT question_type_id_fk
	FOREIGN KEY(question_type_id)
	REFERENCES t_question_type(id);


--TABLE QUESTION FILE
CREATE TABLE t_question_file (
	id serial,
	question_id INT NOT NULL,
	question_file_id INT NOT NULL,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_question_file ADD CONSTRAINT question_file_pk
	PRIMARY KEY(id);

ALTER TABLE t_question_file ADD CONSTRAINT question_id_fk
	FOREIGN KEY(question_id)
	REFERENCES t_question(id);

ALTER TABLE t_question_file ADD CONSTRAINT question_file_id_fk
	FOREIGN KEY(question_file_id)
	REFERENCES t_file(id);

--TABLE REVIEW
CREATE TABLE t_review(
	id serial,
	teacher_id INT NOT NULL,
	student_id INT NOT NULL,
	grade FLOAT,
	notes TEXT,
	task_id INT NOT NULL,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_review ADD CONSTRAINT review_pk
	PRIMARY KEY(id);

ALTER TABLE t_review ADD CONSTRAINT teacher_id_fk
	FOREIGN KEY(teacher_id)
	REFERENCES t_user(id);

ALTER TABLE t_review ADD CONSTRAINT student_id_fk
	FOREIGN KEY(student_id)
	REFERENCES t_user(id);

ALTER TABLE t_review ADD CONSTRAINT task_id_fk
	FOREIGN KEY(task_id)
	REFERENCES t_task(id);

--TABLE TASK_ANSWER
CREATE TABLE t_question_answer(
	id serial,
	question_id INT NOT NULL,	
	essay_answer TEXT,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_question_answer ADD CONSTRAINT question_answer_pk
	PRIMARY KEY(id);

ALTER TABLE t_question_answer ADD CONSTRAINT question_id_fk
	FOREIGN KEY(question_id)
	REFERENCES t_question(id);



--TABLE TASK_ANSWER_DETAIL
CREATE TABLE t_question_answer_detail (
	id serial,
	question_answer_id INT NOT NULL,
	question_answer_file_id INT NOT NULL,
	created_by INT NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by INT,
	updated_at TIMESTAMP,
	is_active BOOLEAN NOT NULL,
	ver int NOT NULL
);

ALTER TABLE t_question_answer_detail ADD CONSTRAINT t_question_answer_detail_pk
	PRIMARY KEY(id);

ALTER TABLE t_question_answer_detail ADD CONSTRAINT question_answer_id_fk
	FOREIGN KEY(question_answer_id)
	REFERENCES t_question_answer(id);

ALTER TABLE t_question_answer_detail ADD CONSTRAINT question_answer_file_id_fk
	FOREIGN KEY(question_answer_file_id)
	REFERENCES t_file(id);