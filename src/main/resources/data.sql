INSERT INTO user(user_email) VALUES ('user1@mail.com');
INSERT INTO user(user_email) VALUES ('user2@mail.com');
INSERT INTO user(user_email) VALUES ('user3@mail.com');

INSERT INTO feature(feature_name) VALUES ('Feature A');
INSERT INTO feature(feature_name) VALUES ('Feature B');
INSERT INTO feature(feature_name) VALUES ('Feature C');

INSERT INTO access(feature_id, user_id) VALUES (1, 1);
INSERT INTO access(feature_id, user_id) VALUES (2, 2);
INSERT INTO access(feature_id, user_id) VALUES (3, 3);
