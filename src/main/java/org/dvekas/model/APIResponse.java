package org.dvekas.model;

import lombok.Getter;

@Getter
public class APIResponse {
    int code;
    String type;
    String message;

    public APIResponse(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

}
