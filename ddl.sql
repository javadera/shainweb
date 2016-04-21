   -- ロールマスタテーブル --
   CREATE TABLE role_master
   (
     role_id serial NOT NULL,
     role_name character varying(50) NOT NULL DEFAULT 'N/A'::character varying,
     CONSTRAINT role_master_pkey PRIMARY KEY (role_id),
     CONSTRAINT role_master_role_name_key UNIQUE (role_name)
   )
   WITH (
     OIDS=FALSE
   );
   ALTER TABLE role_master
     OWNER TO postgres;

   INSERT INTO role_master(role_name) VALUES('USER');
   INSERT INTO role_master(role_name) VALUES('ADMIN');
   COMMIT;


   -- 社員テーブル --
   CREATE TABLE employee
   (
     shain_id character varying(20) NOT NULL,
     family_name character varying(50) NOT NULL DEFAULT 'N/A'::character varying,
     family_name_kana character varying(50) NOT NULL DEFAULT 'N/A'::character varying,
     given_name character varying(50) NOT NULL DEFAULT 'N/A'::character varying,
     given_name_kana character varying(50) NOT NULL DEFAULT 'N/A'::character varying,
     email character varying(50) NOT NULL DEFAULT 'N/A'::character varying,
     phone_number character varying(50) NOT NULL DEFAULT 'N/A'::character varying,
     cellphone_number character varying(50) NOT NULL DEFAULT 'N/A'::character varying,
     address character varying(300) NOT NULL DEFAULT 'N/A'::character varying,
     join_date date NOT NULL DEFAULT ('now'::text)::date,
     quit_date date NOT NULL DEFAULT to_date('9999/12/31'::text, 'YYYY/MM/DD'::text),
     password character varying (255) NOT NULL DEFAULT 'N/A'::character varying,
     role_id integer NOT NULL DEFAULT 1,
     delete_flag integer NOT NULL DEFAULT 0,
     CONSTRAINT employee_pkey PRIMARY KEY (shain_id),
     CONSTRAINT employee_email_key UNIQUE (email),
     CONSTRAINT employee_chk_deleteflag CHECK (delete_flag = ANY (ARRAY[0, 1])),
     CONSTRAINT employee_fk_roleid FOREIGN KEY (role_id) REFERENCES role_master(role_id) ON UPDATE CASCADE ON DELETE SET DEFAULT
   )
   WITH (
     OIDS=FALSE
   );
   ALTER TABLE employee
     OWNER TO postgres;
