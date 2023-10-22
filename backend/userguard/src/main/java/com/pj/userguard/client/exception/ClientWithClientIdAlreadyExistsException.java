package com.pj.userguard.client.exception;

import com.pj.userguard.client.field.ClientId;

public class ClientWithClientIdAlreadyExistsException extends IllegalArgumentException {

    public ClientWithClientIdAlreadyExistsException(ClientId clientId) {
        super(String.format("Client with client ID: %s already exists", clientId.getClientId()));
    }
}
