DELETE FROM votes;
DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni'),   -- 100000, pass: admin
  ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju'); -- 100001, pass: password

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER',  100000), -- User
  ('ROLE_ADMIN', 100001), -- Admin
  ('ROLE_USER',  100001); -- Admin

INSERT INTO restaurants (name) VALUES
  ('Charley G`s Seafood Grill'),      -- 100002
  ('Cibo`s Bistro & Pizzeria');       -- 100003

INSERT INTO dishes (NAME) VALUES
  ('Grilled Apricot & Basil Horse'),            -- 100004
  ('Slow-Cooked Ginger Rabbit'),                -- 100005
  ('Thermal-Cooked Saffron & Shallot Salmon'),  -- 100006
  ('Smoked Mint & Berry Crocodile'),            -- 100007
  ('Cooked Dark Beer Winter Greens'),           -- 100008
  ('Marinated Fennel & Lemon Forest Mushrooms'),-- 100009
  ('Basted Paprika Pheasant'),                  -- 100010
  ('Breaded Sour & Cream Quail');               -- 100011

INSERT INTO menus (restaurant_id, date, dish_id, price) VALUES
  (100002, '2017-02-01', 100004, 9.77),   -- 100012
  (100002, '2017-02-01', 100005, 8.85),   -- 100013
  (100003, '2017-02-01', 100006, 13),     -- 100014
  (100003, '2017-02-01', 100007, 8.86),   -- 100015

  (100002, '2017-02-02', 100008, 9.40),   -- 100016
  (100002, '2017-02-02', 100009, 6.13),   -- 100017
  (100003, '2017-02-02', 100010, 6.15),   -- 100018
  (100003, '2017-02-02', 100011, 8.35);   -- 100019

INSERT INTO votes (date, user_id, restaurant_id) VALUES
  ('2017-02-01', 100000, 100002), -- 100020 User  Restaurant_100002
  ('2017-02-01', 100001, 100003), -- 100021 Admin Restaurant_100003
  ('2017-02-02', 100000, 100003), -- 100022 User  Restaurant_100003
  ('2017-02-02', 100001, 100003); -- 100023 Admin Restaurant_100002