DROP TABLE votes IF EXISTS;
DROP TABLE menus IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE global_seq AS INTEGER START WITH 100000;

CREATE TABLE users
(
  id               INTEGER GENERATED BY DEFAULT AS SEQUENCE global_seq PRIMARY KEY,
  name             VARCHAR(255),
  email            VARCHAR(255)         NOT NULL,
  password         VARCHAR(255)         NOT NULL,
  registered       TIMESTAMP DEFAULT now(),
  enabled          BOOLEAN   DEFAULT TRUE,
  calories_per_day INTEGER DEFAULT 2000 NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY ( user_id ) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id          INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name        VARCHAR(255)  NOT NULL
);
CREATE UNIQUE INDEX restaurants_unique_name_idx ON restaurants (name);

CREATE TABLE menus
(
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  restaurant_id   INTEGER      NOT NULL,
  date            DATE         NOT NULL,
  dish            VARCHAR(255) NOT NULL,
  price           REAL         NOT NULL,
  FOREIGN KEY ( restaurant_id ) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  date            DATE    NOT NULL,
  user_id         INTEGER      NOT NULL,
  restaurant_id   INTEGER      NOT NULL,
  FOREIGN KEY ( user_id ) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY ( restaurant_id ) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_unique_date_user_restaurant_idx ON votes (date, user_id, restaurant_id);