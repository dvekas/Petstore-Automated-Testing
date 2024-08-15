package org.dvekas.model.pet;

public enum PetStatusEnum {
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

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
