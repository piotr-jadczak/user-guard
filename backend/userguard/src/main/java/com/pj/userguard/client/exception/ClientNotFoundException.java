package com.pj.userguard.client.exception;

import com.pj.userguard.client.field.ClientId;
import com.pj.userguard.client.field.UniqueId;

public class ClientNotFoundException extends IllegalArgumentException {

    public ClientNotFoundException(UniqueId uniqueId) {
        super(String.format("Client with ID: %s not found", uniqueId.getUniqueId()));
    }

    public ClientNotFoundException(ClientId clientId) {
        super(String.format("Client with client ID: %s not found", clientId.getClientId()));
    }
}
