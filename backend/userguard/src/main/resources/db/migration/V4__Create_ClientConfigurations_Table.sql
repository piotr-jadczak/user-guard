CREATE TABLE client_configurations (
   id BIGSERIAL PRIMARY KEY,
   is_default BOOLEAN NOT NULL,
   created_by VARCHAR(255),
   created_date TIMESTAMP NOT NULL,
   last_modified_by VARCHAR(255),
   last_modified_date TIMESTAMP,
   require_proof_key BOOLEAN,
   require_auth_consent BOOLEAN,
   jwk_set_url VARCHAR(255),
   signing_algorithm VARCHAR(255),
   auth_code_seconds_to_live BIGINT,
   access_token_seconds_to_live BIGINT,
   access_token_format VARCHAR(255),
   device_code_seconds_to_live BIGINT,
   reuse_refresh_tokens BOOLEAN,
   refresh_token_seconds_to_live BIGINT,
   token_signature_algorithm VARCHAR(255)
);