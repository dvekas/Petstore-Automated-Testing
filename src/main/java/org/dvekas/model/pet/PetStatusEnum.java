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

    /**
     * Returns a PetStatusEnum object, from the given string.
     *
     * @param status String to be translated into a PetStatusEnum object
     * @return The searched PetStatusEnum object or null
     */
    public static PetStatusEnum getEnumByString(String status){
        for(PetStatusEnum e : PetStatusEnum.values()){
            if(e.statusName.equals(status.toLowerCase())) return e;
        }
        return null;
    }

}
