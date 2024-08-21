package org.dvekas.controller.REST;

import lombok.Getter;

@Getter
public enum ContentTypeEnum {
    JSON("application/json"),
    FORM_DATA("application/x-www-form-urlencoded");

    private final String contentTypeString;

    ContentTypeEnum(String contentTypeString) {
        this.contentTypeString = contentTypeString;
    }
}