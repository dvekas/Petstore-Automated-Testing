package org.dvekas.model.pet;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum PetStatusEnum {
    available("available"),
    pending("pending"),
    sold("sold");

    private final String statusName;

    /**
     * Returns the values of the enum as a stream.
     *
     * @return The returned stream of values.
     */
    public static Stream<PetStatusEnum> stream() {
        return Stream.of(PetStatusEnum.values());
    }

}