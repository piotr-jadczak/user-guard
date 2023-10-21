CREATE TABLE client_scopes (
   id BIGSERIAL PRIMARY KEY,
   scope VARCHAR(255) UNIQUE NOT NULL,
);

CREATE TABLE client_configurations_scopes (
   client_configuration_id BIGINT NOT NULL,
   client_scope_id BIGINT NOT NULL
);

ALTER TABLE client_configurations_scopes
   ADD CONSTRAINT fk_client_configurations_scopes__client_configuration_id
   FOREIGN KEY (client_configuration_id)
   REFERENCES client_configurations (id);

ALTER TABLE client_configurations_auth_grant_types
   ADD CONSTRAINT fk_client_configurations_scopes__client_scope_id
   FOREIGN KEY (client_scope_id)
   REFERENCES client_scopes (id);