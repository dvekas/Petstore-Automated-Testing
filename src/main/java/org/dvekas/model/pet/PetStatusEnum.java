package org.dvekas.model.pet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PetStatusEnum {
    available("available"),
    pending("pending"),
    sold("sold");

    private final String statusName;

}
