CREATE TABLE redirect_uris (
   id BIGSERIAL PRIMARY KEY,
   type VARCHAR(255) NOT NULL,
   uri VARCHAR NOT NULL,
   client_id BIGINT NOT NULL
);

ALTER TABLE redirect_uris
   ADD CONSTRAINT fk_redirect_uris__client_id
   FOREIGN KEY (client_id)
   REFERENCES clients (id);