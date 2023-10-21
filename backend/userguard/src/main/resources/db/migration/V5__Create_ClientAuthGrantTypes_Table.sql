CREATE TABLE client_auth_grant_types (
   id BIGSERIAL PRIMARY KEY,
   method VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE client_configurations_auth_grant_types (
   client_configuration_id BIGINT NOT NULL,
   client_auth_grant_type_id BIGINT NOT NULL
);

ALTER TABLE client_configurations_auth_grant_types
   ADD CONSTRAINT fk_client_configurations_auth_grant_types__client_configuration_id
   FOREIGN KEY (client_configuration_id)
   REFERENCES client_configurations (id);

ALTER TABLE client_configurations_auth_grant_types
   ADD CONSTRAINT fk_client_configurations_auth_grant_types__client_auth_grant_type_id
   FOREIGN KEY (client_auth_grant_type_id)
   REFERENCES client_auth_grant_types (id);