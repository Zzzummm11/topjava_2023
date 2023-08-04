DROP INDEX IF EXISTS idx_unique_meals_user_datetime;
DROP INDEX IF EXISTS idx_meals_id_user_id;

DELETE
FROM meals;

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

INSERT INTO meals (date_time, description, calories, user_id)
VALUES ('2023-01-29 10:00:00', 'Admin breakfast', 500, 100001),
       ('2023-01-30 13:00:00', 'Admin lunch', 1000, 100001),
       ('2023-01-30 20:00:00', 'Admin dinner', 500, 100001),
       ('2023-01-31 00:00:00', 'Border meals', 100, 100001),
       ('2023-01-31 10:00:00', 'Admin breakfast', 1000, 100001),
       ('2023-01-31 13:00:00', 'Admin lunch', 500, 100001),
       ('2023-02-01 10:00:00', 'Admin breakfast', 410, 100001);

CREATE UNIQUE INDEX idx_unique_meals_user_datetime ON meals (user_id, date_time);

CREATE INDEX idx_meals_id_user_id ON meals (id, user_id);