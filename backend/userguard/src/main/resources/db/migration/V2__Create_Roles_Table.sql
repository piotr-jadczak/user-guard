CREATE TABLE roles (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE users_roles (
   user_id BIGINT NOT NULL,
   role_id BIGINT NOT NULL
);

ALTER TABLE users_roles
   ADD CONSTRAINT fk_users_roles__user_id
   FOREIGN KEY (user_id)
   REFERENCES users (id);

ALTER TABLE users_roles
   ADD CONSTRAINT fk_users_roles__role_id
   FOREIGN KEY (role_id)
   REFERENCES roles (id);