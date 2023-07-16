--INSERT ROLE
INSERT INTO t_role (role_code,role_name,created_by,created_at,is_active,ver)VALUES
	('SPADM','Super Admin'	,1,'2023-06-23 09:00:00',true,1),
	('TCHR'	,'Teacher'		,1,'2023-06-23 09:00:00',true,1),
	('STDN'	,'Student'		,1,'2023-06-23 09:00:00',true,1);

--INSERT FILE
INSERT INTO t_file (ext,file_name,created_by,created_at,is_active,ver)VALUES
	('.jpg','profile_pic'		,1,'2023-06-23 09:00:00',true,1);

--INSERT PROFILE
INSERT INTO t_profile (photo_id,full_name,address,phone_no,created_by,created_at,is_active,ver)VALUES
	(1,'Santo Matthew','Bekasi','081113131',1,'2023-06-23 09:00:00',true,1);
	
--INSERT USER
INSERT INTO t_user (role_id,profile_id,user_email,user_password,created_by,created_at,is_active,ver)VALUES
	(1,1,'superadmin@gmail.com'	,'$2a$12$NyMFcYCcxfEVL1REJ7TclOww1DreW3htgC57stoKMAUpXx4snUjx.',1,'2023-06-23 09:00:00',true,1);

--INSERT TASK_QUESTION_TYPE
INSERT INTO t_question_type (question_type_code,question_type_name,created_by,created_at,is_active,ver)VALUES
	('ESSAY','Essay',2,'2023-06-23 09:00:00',true,1),
	('QFL','File',2,'2023-06-23 09:00:00',true,1);


