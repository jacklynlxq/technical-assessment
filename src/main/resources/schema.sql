SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS feature;
DROP TABLE IF EXISTS access;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE user (
     id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
     user_email VARCHAR(128) NOT NULL UNIQUE,
     updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (id)
);

CREATE TABLE feature (
      id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
      feature_name VARCHAR(128) NOT NULL UNIQUE,
      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (id)
);

CREATE TABLE access  (
     id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
     feature_id BIGINT NOT NULL,
     user_id BIGINT NOT NULL,
     access BOOLEAN NOT NULL DEFAULT false,
     updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     UNIQUE KEY (user_id, feature_id),
     FOREIGN KEY (feature_id) REFERENCES feature(id),
     FOREIGN KEY (user_id) REFERENCES user(id)
);