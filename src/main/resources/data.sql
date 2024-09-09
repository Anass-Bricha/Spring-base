-- pwd : password123

INSERT INTO users (username, password) VALUES ('anass', '$2a$10$KIX0j3V2oYk5ymb9Jt9G8epV5vq9FgKb5iXuk97lKoNm.vKw/CnQK');

INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_MANAGER');


INSERT INTO authority (name) VALUES ('READ_PRIVILEGE');
INSERT INTO authority (name) VALUES ('WRITE_PRIVILEGE');
INSERT INTO authority (name) VALUES ('DELETE_PRIVILEGE');


-- Assign authorities to ROLE_ADMIN
INSERT INTO role_authority (role_id, authority_id) VALUES (1, 1); -- READ_PRIVILEGE
INSERT INTO role_authority (role_id, authority_id) VALUES (1, 2); -- WRITE_PRIVILEGE
INSERT INTO role_authority (role_id, authority_id) VALUES (1, 3); -- DELETE_PRIVILEGE

-- Assign authorities to ROLE_USER
INSERT INTO role_authority (role_id, authority_id) VALUES (2, 1); -- READ_PRIVILEGE

-- Assign authorities to ROLE_MANAGER
INSERT INTO role_authority (role_id, authority_id) VALUES (3, 1); -- READ_PRIVILEGE
INSERT INTO role_authority (role_id, authority_id) VALUES (3, 2); -- WRITE_PRIVILEGE


INSERT INTO users (username, password, role_id) VALUES ('admin', '$2a$12$QayUgb668auXilAdC9hvxOJFFqBe/p5DmKsRdUJs16PHvM2rnznsy', 1);
INSERT INTO users (username, password, role_id) VALUES ('user', '$2a$12$h0HvzlKbb3CPywTHq0x6beMv7OdOVuSH3mY2p0awAL9/4gOYZwwcy', 2);
INSERT INTO users(username, password, role_id) VALUES ('manager', '$2b$12$AA9VVtYOeupCEglJuU9aiOa7LnM57XIkMWgWxiqS5j8RaU78Z3RI.', 3);

