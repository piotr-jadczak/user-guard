package com.pj.userguard.client.field;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum RedirectUriType {
    POST_LOGIN,
    POST_LOGOUT
}
