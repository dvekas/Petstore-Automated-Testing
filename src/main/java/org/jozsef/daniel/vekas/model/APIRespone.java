package org.jozsef.daniel.vekas.model;

public class APIRespone {
    int code;
    String type;
    String message;

    public APIRespone(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
