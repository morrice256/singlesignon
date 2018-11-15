create table privileges (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  create_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_date_time TIMESTAMP NULL
);

create table roles (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  create_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_date_time TIMESTAMP NULL
);

create table roles_privileges (
	role_id INTEGER,
	privilege_id INTEGER,
	FOREIGN KEY (role_id) REFERENCES roles(id),
	FOREIGN KEY (privilege_id) REFERENCES privileges(id)
);

create table users_roles (
	role_id INTEGER,
	user_id INTEGER,
	FOREIGN KEY (role_id) REFERENCES roles(id),
	FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO roles (`name`, `create_date_time`)
 VALUES ('ROLE_ADMIN', now()), ('ROLE_USER', now());
 
 INSERT INTO privileges (`name`, `create_date_time`)
 VALUES ('READ_PRIVILEGE', now()), ('WRITE_PRIVILEGE', now());
 
 INSERT INTO roles_privileges (`role_id`, `privilege_id`)
 VALUES 
 ((SELECT id FROM roles WHERE name = 'ROLE_ADMIN' LIMIT 1), (SELECT id FROM privileges WHERE name = 'READ_PRIVILEGE' LIMIT 1)),
 ((SELECT id FROM roles WHERE name = 'ROLE_ADMIN' LIMIT 1), (SELECT id FROM privileges WHERE name = 'WRITE_PRIVILEGE' LIMIT 1)),
 ((SELECT id FROM roles WHERE name = 'ROLE_USER' LIMIT 1), (SELECT id FROM privileges WHERE name = 'READ_PRIVILEGE' LIMIT 1)),
 ((SELECT id FROM roles WHERE name = 'ROLE_USER' LIMIT 1), (SELECT id FROM privileges WHERE name = 'WRITE_PRIVILEGE' LIMIT 1));
