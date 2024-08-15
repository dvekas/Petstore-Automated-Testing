package org.dvekas.model.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Pet {

    @JsonProperty("id")
    String id;
    @JsonProperty("category")
    Category category;
    @JsonProperty("name")
    String name;
    @JsonProperty("photoUrls")
    List<String> photoUrls;
    @JsonProperty("tags")
    List<Tag> tags;
    @JsonProperty("status")
    PetStatusEnum status;

}
