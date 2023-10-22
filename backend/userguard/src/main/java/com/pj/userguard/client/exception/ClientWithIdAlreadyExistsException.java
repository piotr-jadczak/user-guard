package com.pj.userguard.client.exception;

import com.pj.userguard.client.field.UniqueId;

public class ClientWithIdAlreadyExistsException extends IllegalArgumentException {

    public ClientWithIdAlreadyExistsException(UniqueId uniqueId) {
        super(String.format("Client with ID: %s already exists", uniqueId.getUniqueId()));
    }
}
