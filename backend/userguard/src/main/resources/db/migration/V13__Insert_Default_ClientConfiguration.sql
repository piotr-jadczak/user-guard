INSERT INTO client_configurations (is_default, created_date)
VALUES ('true', now());

INSERT INTO client_configurations_auth_grant_types (client_configuration_id, client_auth_grant_type_id)
VALUES (
    (SELECT id FROM client_configurations WHERE is_default = true),
    (SELECT id FROM client_auth_grant_types WHERE method = 'AUTHORIZATION_CODE')
),
(
    (SELECT id FROM client_configurations WHERE is_default = true),
    (SELECT id FROM client_auth_grant_types WHERE method = 'REFRESH_TOKEN')
);

INSERT INTO client_configurations_auth_methods (client_configuration_id, client_auth_method_id)
VALUES (
    (SELECT id FROM client_configurations WHERE is_default = true),
    (SELECT id FROM client_auth_methods WHERE method = 'CLIENT_SECRET_BASIC')
);

INSERT INTO client_configurations_scopes (client_configuration_id, client_scope_id)
VALUES (
    (SELECT id FROM client_configurations WHERE is_default = true),
    (SELECT id FROM client_scopes WHERE scope = 'OPENID')
);