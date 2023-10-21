CREATE TABLE client_auth_methods (
   id BIGSERIAL PRIMARY KEY,
   method VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE client_configurations_auth_methods (
   client_configuration_id BIGINT NOT NULL,
   client_auth_method_id BIGINT NOT NULL
);

ALTER TABLE client_configurations_auth_methods
   ADD CONSTRAINT fk_client_configurations_auth_methods__client_configuration_id
   FOREIGN KEY (client_configuration_id)
   REFERENCES client_configurations (id);

ALTER TABLE client_configurations_auth_grant_types
   ADD CONSTRAINT fk_client_configurations_auth_methods__client_auth_method_id
   FOREIGN KEY (client_auth_method_id)
   REFERENCES client_auth_methods (id);