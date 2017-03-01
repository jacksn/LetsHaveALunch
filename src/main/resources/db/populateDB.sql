DELETE FROM votes;
DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

-- admin
INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni');

-- password
INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (name) VALUES
  ('Charley G`s Seafood Grill'),      -- 100002
  ('Cibo`s Bistro & Pizzeria');       -- 100003

INSERT INTO menus (RESTAURANT_ID, DATETIME, DISH, PRICE) VALUES
  (100002, now(), 'Grilled Apricot & Basil Horse', 92.77),            -- 100004
  (100002, now(), 'Slow-Cooked Ginger Rabbit', 80.85),                -- 100005
  (100002, now(), 'Thermal-Cooked Saffron & Shallot Salmon', 23),     -- 100006
  (100003, now(), 'Smoked Mint & Berry Crocodile', 80.86),            -- 100007
  (100003, now(), 'Cooked Dark Beer Winter Greens', 95.40),           -- 100008
  (100003, now(), 'Marinated Fennel & Lemon Forest Mushrooms', 69.13);-- 100009