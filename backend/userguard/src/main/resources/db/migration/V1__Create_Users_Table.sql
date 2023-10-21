CREATE TABLE users (
   id BIGSERIAL PRIMARY KEY,
   username VARCHAR(255) UNIQUE NOT NULL,
   password VARCHAR(255) NOT NULL,
   email VARCHAR(255) UNIQUE NOT NULL,
   is_locked BOOLEAN NOT NULL,
   is_enabled BOOLEAN NOT NULL,
   is_expired BOOLEAN NOT NULL,
   is_credentials_expired BOOLEAN NOT NULL,
   created_by VARCHAR(255),
   created_date DATE NOT NULL,
   last_modified_by VARCHAR(255),
   last_modified_date TIMESTAMP
);