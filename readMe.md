# Заполнение БД

```mysql

INSERT INTO test.users (id, age, email, password, username) VALUES (1, 50, 'user@exam.com', '41ebaec9f7cffdfb6fd140ac93c7181f', 'userer');
INSERT INTO test.users (id, age, email, password, username) VALUES (2, 43, 'admin@exam.com', '$2a$10$PvlEeIEbB8QrBmMrWTcWCu67g.PmWKtNDvJr7yFSakeqGHeWypmEa', 'admin');

INSERT INTO test.roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO test.roles (id, name) VALUES (2, 'ROLE_USER');


INSERT INTO test.users_roles (user_id, roles_id) VALUES (2, 1);
INSERT INTO test.users_roles (user_id, roles_id) VALUES (1, 2);
```