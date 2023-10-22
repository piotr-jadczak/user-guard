package com.pj.userguard.client.exception;

public class DefaultClientConfigurationNotFound extends IllegalArgumentException {

    public DefaultClientConfigurationNotFound() {
        super("Default client configuration not found");
    }
}
