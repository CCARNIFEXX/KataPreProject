# Заполнение БД

```mysql
INSERT INTO test.roles (name, authorities)
VALUES ('ROLE_ADMIN', 'create,read,update,delete');
INSERT INTO test.roles (name, authorities)
VALUES ('ROLE_USER', 'read');

INSERT INTO test.users (id, age, email, password, username, enabled)
VALUES (1, 42, 'user@exam.com', '$2a$10$PvlEeIEbB8QrBmMrWTcWCu67g.PmWKtNDvJr7yFSakeqGHeWypmEa', 'user', 1);
INSERT INTO test.users (id, age, email, password, username, enabled)
VALUES (2, 43, 'admin@exam.com', '$2a$10$PvlEeIEbB8QrBmMrWTcWCu67g.PmWKtNDvJr7yFSakeqGHeWypmEa', 'admin', 1);

INSERT INTO test.users_roles (user_id, roles_name)
VALUES (2, 'ROLE_ADMIN');
INSERT INTO test.users_roles (user_id, roles_name)
VALUES (1, 'ROLE_USER');
INSERT INTO test.users_roles (user_id, roles_name)
VALUES (2, 'ROLE_USER');

create view test.authorities as
select `u`.`username` AS `username`, `ur`.`roles_name` AS `authority`
from (`test`.`users_roles` `ur` join `test`.`users` `u` on ((`ur`.`user_id` = `u`.`id`)));
```