package org.jozsef.daniel.vekas.model.pet;

public enum PetStatusEnum {
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    private String statusName;

    PetStatusEnum(String status) {
        statusName = status;
    }

    public static PetStatusEnum getEnumByString(String status){
        for(PetStatusEnum e : PetStatusEnum.values()){
            if(e.statusName.equals(status)) return e;
        }
        return null;
    }
}
