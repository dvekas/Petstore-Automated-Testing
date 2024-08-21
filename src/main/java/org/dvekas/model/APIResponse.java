package org.dvekas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class APIResponse {
    int code;
    String type;
    String message;

}
