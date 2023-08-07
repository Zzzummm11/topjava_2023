DELETE
FROM user_role;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('Guest', 'guest@gmail.com', 'guest');

INSERT INTO user_role (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meal (date_time, description, calories, user_id)
VALUES ('2023-01-29 10:00:00', 'Admin breakfast', 500, 100001),
       ('2023-01-30 13:00:00', 'Admin lunch', 1000, 100001),
       ('2023-01-30 20:00:00', 'Admin dinner', 500, 100001),
       ('2023-01-31 00:00:00', 'Border meals', 100, 100001),
       ('2023-01-31 10:00:00', 'Admin breakfast', 1000, 100001),
       ('2023-01-31 13:00:00', 'Admin lunch', 500, 100001),
       ('2023-02-01 10:00:00', 'Admin breakfast', 410, 100001),
       ('2023-01-30 10:00:00', 'User breakfast', 120, 100000),
       ('2023-01-30 13:00:00', 'User lunch', 300, 100000),
       ('2023-01-30 20:00:00', 'User dinner', 200, 100000);