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

INSERT INTO menus (RESTAURANT_ID, DATE, DISH, PRICE) VALUES
  (100002, '2017-02-01', 'Grilled Apricot & Basil Horse', 9.77),             -- 100004
  (100002, '2017-02-01', 'Slow-Cooked Ginger Rabbit', 8.85),                 -- 100005
  (100003, '2017-02-01', 'Thermal-Cooked Saffron & Shallot Salmon', 13),     -- 100006
  (100003, '2017-02-01', 'Smoked Mint & Berry Crocodile', 8.86),             -- 100007

  (100002, '2017-02-02', 'Cooked Dark Beer Winter Greens', 9.40),            -- 100008
  (100002, '2017-02-02', 'Marinated Fennel & Lemon Forest Mushrooms', 6.13), -- 100009
  (100003, '2017-02-02', 'Basted Paprika Pheasant', 6.15),                   -- 100010
  (100003, '2017-02-02', 'Breaded Sour & Cream Quail', 8.35);                -- 100011

INSERT INTO votes (DATE, USER_ID, RESTAURANT_ID) VALUES
  ('2017-02-01', 100000, 100002), -- 100012 User  Restaurant_100002
  ('2017-02-01', 100001, 100003), -- 100013 Admin Restaurant_100003
  ('2017-02-02', 100000, 100003), -- 100014 User  Restaurant_100003
  ('2017-02-02', 100001, 100003); -- 100015 Admin Restaurant_100002