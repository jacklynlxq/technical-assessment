INSERT INTO user(user_email) VALUES ('user1@mail.com');
INSERT INTO user(user_email) VALUES ('user2@mail.com');
INSERT INTO user(user_email) VALUES ('user3@mail.com');

INSERT INTO feature(feature_name) VALUES ('Feature A');
INSERT INTO feature(feature_name) VALUES ('Feature B');
INSERT INTO feature(feature_name) VALUES ('Feature C');

INSERT INTO access(feature_id, user_id, access) VALUES (1, 1, true);
INSERT INTO access(feature_id, user_id, access) VALUES (2, 1, false);
INSERT INTO access(feature_id, user_id, access) VALUES (3, 1, false);

INSERT INTO access(feature_id, user_id, access) VALUES (1, 2, false);
INSERT INTO access(feature_id, user_id, access) VALUES (2, 2, true);
INSERT INTO access(feature_id, user_id, access) VALUES (3, 2, false);

INSERT INTO access(feature_id, user_id, access) VALUES (1, 3, false);
INSERT INTO access(feature_id, user_id, access) VALUES (2, 3, false);
INSERT INTO access(feature_id, user_id, access) VALUES (3, 3, true);
