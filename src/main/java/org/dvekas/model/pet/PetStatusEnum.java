package org.dvekas.model.pet;

import lombok.Getter;

@Getter
public enum PetStatusEnum {
    available("available"),
    pending("pending"),
    sold("sold");

    private final String statusName;

    PetStatusEnum(String status) {
        statusName = status;
    }

}
