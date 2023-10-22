CREATE TABLE clients (
   id BIGSERIAL PRIMARY KEY,
   created_by VARCHAR(255),
   created_date TIMESTAMP NOT NULL,
   last_modified_by VARCHAR(255),
   last_modified_date TIMESTAMP,
   unique_id VARCHAR UNIQUE NOT NULL,
   client_id VARCHAR UNIQUE NOT NULL,
   client_id_issued_at TIMESTAMP NOT NULL,
   client_secret VARCHAR NOT NULL,
   client_secret_expires_at TIMESTAMP NOT NULL,
   client_name VARCHAR NOT NULL,
   configuration_id BIGINT NOT NULL
);

ALTER TABLE clients
   ADD CONSTRAINT fk_clients__configuration_id
   FOREIGN KEY (configuration_id)
   REFERENCES client_configurations (id);