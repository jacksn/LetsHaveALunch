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
  ('Cibo`s Bistro & Pizzeria'),       -- 100003
  ('Cowboy Grub Restaurant'),         -- 100004
  ('Daily Grind Cafe & Dessertery'),  -- 100005
  ('Doc`s Pizzeria');                 -- 100006

INSERT INTO menus (RESTAURANT_ID, DATETIME, DISH, PRICE) VALUES
  (100002, now(), 'Grilled Apricot & Basil Horse', 92.77),            -- 100007
  (100002, now(), 'Slow-Cooked Ginger Rabbit', 80.85),                -- 100008
  (100002, now(), 'Thermal-Cooked Saffron & Shallot Salmon', 23),     -- 100009
  (100002, now(), 'Smoked Mint & Berry Crocodile', 80.86),            -- 100010
  (100002, now(), 'Cooked Dark Beer Winter Greens', 95.40),           -- 100011
  (100002, now(), 'Marinated Fennel & Lemon Forest Mushrooms', 69.13),
  (100003, now(), 'Lime and Chestnut Pie', 99.76),
  (100003, now(), 'Pecan and Avocado Custard', 79.17),
  (100003, now(), 'Cherry Genoise', 73.43),
  (100003, now(), 'Saffron Toast', 34),
  (100003, now(), 'Cooked Chestnuts & Pheasant', 43.33),
  (100003, now(), 'Deep-Fried Dark Ale Rabbit', 84.10),
  (100003, now(), 'Stuffed Chestnuts & Alligator', 97.78),
  (100003, now(), 'Smoked Oregano Tuna', 65.44),
  (100004, now(), 'Steamed Confit of Bake', 18.54),
  (100004, now(), 'Roasted Orange & Mustard Stuffed Bread', 86.31),
  (100004, now(), 'Milk Chocolate and Caramel Tart', 57.36),
  (100004, now(), 'Lime and Gooseberry Toast', 67.15),
  (100004, now(), 'Melon Pud', 23.96),
  (100004, now(), 'Nutmeg Gingerbread', 32),
  (100004, now(), 'Breaded Dark Beer Pigeon', 62.08),
  (100005, now(), 'Roasted Lime-Coated Boar', 23.87),
  (100005, now(), 'Roasted Lemon Shrimps', 0.70),
  (100005, now(), 'Steamed Onions & Cream Alligator', 72.36),
  (100005, now(), 'Seared Sweet `n Sour Tart', 25.99),
  (100005, now(), 'Roasted Blueberry & Mushroom Nut Mix', 52.77),
  (100005, now(), 'Coffee and Rum Bombe', 82.54),
  (100005, now(), 'Strawberry and Kiwi Candy', 79.70),
  (100005, now(), 'Passion Fruit Sorbet', 69.90),
  (100006, now(), 'Almond Surprise', 45.88),
  (100006, now(), 'Infused Lime & Ginger Pork', 98.37),
  (100006, now(), 'Broasted Peas & Mushroom Beef', 57.23),
  (100006, now(), 'Barbecued Beets & Lemon Fish', 21.30),
  (100006, now(), 'Grilled Hazelnut Snapper', 45.53),
  (100006, now(), 'Pickled Sour Bruschetta', 26.89),
  (100006, now(), 'Barbecued Lime-Coated Roll', 78.72),
  (100006, now(), 'Grapefruit and Lime Pie', 44.08),
  (100006, now(), 'Saffron and Papaya Steamed Pudding', 92.45),
  (100006, now(), 'Ginger Bread', 45.82),
  (100006, now(), 'Pistachio Bombe', 60.85);