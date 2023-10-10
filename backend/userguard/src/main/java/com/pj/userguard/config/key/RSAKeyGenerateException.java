package com.pj.userguard.config.key;

class RSAKeyGenerateException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "Unable to generate RSA keys";

    RSAKeyGenerateException(Throwable cause) {
        super(EXCEPTION_MESSAGE, cause);
    }
}
