INSERT INTO employees(given_name, family_name, date_of_birth, email, username, password) VALUES ('Williams', 'Parker', '2001-09-20','williams@gmail.com','will123','$Will2409');
INSERT INTO employees(given_name, family_name, date_of_birth, email, username, password) VALUES ('Marry', 'Sky', '1980-09-30','marry@gmail.com','marry123','$Marry2409');
INSERT INTO employees(given_name, family_name, date_of_birth, email, username, password) VALUES ('Harry', 'Robinson', '1984-09-01','harry@gmail.com','harry123','$Harry2409');
INSERT INTO employees(given_name, family_name, date_of_birth, email, username, password) VALUES ('Claire', 'Anderson', '1995-09-15','clairre@gmail.com','claire123','$Claire2409');

INSERT INTO users(username, password, admin) VALUES('admin','{bcrypt}$2a$10$3a6SVfvwobo5CoFIVyBXauGbe0sfYIfEqhmpAkMSl0XPVjT4fSOtS',true)

--CREATE TABLE users (
--  id int(11) NOT NULL ,
--  username varchar(45) NOT NULL,
--  password varchar(64) NOT NULL,
--  PRIMARY KEY (id)
--);


--CREATE TABLE users_roles (
--  user_id int(11) NOT NULL,
--  role_id int(11) NOT NULL,
--  name varchar(45) NOT NULL,
--
--  CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES roles (role_id),
--  CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users (user_id)
--);
--
--
--INSERT INTO users_roles (name) VALUES ('USER');
--INSERT INTO users_roles (name) VALUES ('ADMIN');
