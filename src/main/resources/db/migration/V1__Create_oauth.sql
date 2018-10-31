create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

INSERT INTO oauth_client_details
(client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES 
('clientId', '', 'secret', 'read', 'password,authorization_code,refresh_token', '', '', NULL, NULL, '{}', '');
 
create table oauth_client_token (
  token_id VARCHAR(255),
  token VARCHAR(255),
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table oauth_access_token (
  token_id VARCHAR(255),
  token VARCHAR(255),
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication VARCHAR(255),
  refresh_token VARCHAR(255)
);
 
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token VARCHAR(255),
  authentication VARCHAR(255)
);
 
create table oauth_code (
  code VARCHAR(255), authentication VARCHAR(255)
);
 
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
 
create table users (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255),
  password VARCHAR(255),
  enabled BOOLEAN,
  create_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_date_time TIMESTAMP NULL
);

INSERT INTO users (`username`, `password`, `enabled`, `create_date_time`)
 VALUES ('morrice256', '$2a$10$JBQHS4B0Tx742rbfDh1q0urbPgZHDIxLqI7RKQjakY2YVuXa168Ey', true, now());

