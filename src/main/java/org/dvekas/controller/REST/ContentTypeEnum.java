package org.dvekas.controller.REST;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContentTypeEnum {
    JSON("application/json"),
    FORM_DATA("application/x-www-form-urlencoded");

    private final String contentTypeString;

}
