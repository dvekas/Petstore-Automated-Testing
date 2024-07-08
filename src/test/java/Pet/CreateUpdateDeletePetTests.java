package Pet;

import org.jozsef.daniel.vekas.controller.Pet.PetRequests;
import org.jozsef.daniel.vekas.model.factories.PetFactory;
import org.jozsef.daniel.vekas.model.pet.Pet;
import org.jozsef.daniel.vekas.model.pet.PetStatusEnum;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateUpdateDeletePetTests {

    PetStatusEnum baseStatus = PetStatusEnum.AVAILABLE;
    Pet pet;

    @BeforeTest
    void initialize() {
        pet = PetFactory.getPet("Barkspawn", baseStatus);
    }

    @Test
    void createPet() {
        Pet createdPet = new PetRequests().createNewPet(pet);
        System.out.println(createdPet.getName());
    }

}
