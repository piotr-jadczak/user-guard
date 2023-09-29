CREATE TABLE roles (
    id serial PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE user_role (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL
);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role__user_id
    FOREIGN KEY (user_id)
    REFERENCES users (id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role__role_id
    FOREIGN KEY (role_id)
    REFERENCES roles (id);