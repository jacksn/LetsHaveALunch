DELETE FROM votes;
DELETE FROM menu_items;
DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM dishes;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni'),   -- 100000, pass: password
  ('Admin', 'admin@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju'); -- 100001, pass: admin

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER',  100000), -- User
  ('ROLE_ADMIN', 100001), -- Admin
  ('ROLE_USER',  100001); -- Admin

INSERT INTO restaurants (name) VALUES
  ('Charley G`s Seafood Grill'),      -- 100002
  ('Cibo`s Bistro & Pizzeria');       -- 100003

INSERT INTO dishes (NAME) VALUES
  ('Basted Paprika Pheasant'),                  -- 100004
  ('Breaded Sour & Cream Quail'),               -- 100005
  ('Cooked Dark Beer Winter Greens'),           -- 100006
  ('Grilled Apricot & Basil Horse'),            -- 100007
  ('Marinated Fennel & Lemon Forest Mushrooms'),-- 100008
  ('Slow-Cooked Ginger Rabbit'),                -- 100009
  ('Smoked Mint & Berry Crocodile'),            -- 100010
  ('Thermal-Cooked Saffron & Shallot Salmon');  -- 100011

INSERT INTO menus (restaurant_id, date ) VALUES
  (100002, '2017-02-01'),   -- 100012
  (100003, '2017-02-01'),   -- 100013

  (100002, '2017-02-02'),   -- 100014
  (100003, '2017-02-02');   -- 100015

INSERT INTO menu_items (menu_id, dish_id, price) VALUES
  (100012, 100004, 9.77),
  (100012, 100005, 8.85),
  (100013, 100006, 13),
  (100013, 100007, 8.86),

  (100014, 100008, 9.40),
  (100014, 100009, 6.13),
  (100015, 100010, 6.15),
  (100015, 100011, 8.35);

INSERT INTO votes (date, user_id, restaurant_id) VALUES
  ('2017-02-01', 100000, 100002), -- 100016 User  Restaurant_100002 Charley G`s Seafood Grill
  ('2017-02-01', 100001, 100003), -- 100017 Admin Restaurant_100003 Cibo`s Bistro & Pizzeria
  ('2017-02-02', 100000, 100003), -- 100018 User  Restaurant_100003 Cibo`s Bistro & Pizzeria
  ('2017-02-02', 100001, 100003); -- 100019 Admin Restaurant_100003 Cibo`s Bistro & Pizzeria