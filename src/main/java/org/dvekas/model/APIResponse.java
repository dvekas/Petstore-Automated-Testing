package org.dvekas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class APIResponse {

    @JsonProperty("code")
    int code;
    @JsonProperty("type")
    String type;
    @JsonProperty("message")
    String message;

}