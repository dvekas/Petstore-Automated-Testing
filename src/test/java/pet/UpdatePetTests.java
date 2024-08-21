package pet;

import org.apache.http.HttpStatus;
import org.dvekas.model.APIResponse;
import org.dvekas.model.pet.Pet;
import org.dvekas.model.pet.PetStatusEnum;
import org.testng.annotations.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdatePetTests extends PetTestBase {

    /**
     * GIVEN - A Pet exists in the database
     * WHEN - Trying to change the Pet
     * THEN - The correct Pet is changed, as expected
     */
    @Test
    void updateExistingPetSuccessfulTest() {
        LOG.info("Running: updateExistingPetSuccessfulTest");

        Pet createdPet = petRequestHandler.createNewPet(petToBeCreated);
        petToBeCreated.setStatus(PetStatusEnum.sold);
        Pet updatedPet = petRequestHandler.updatePet(petToBeCreated);

        assertThat(updatedPet)
                .as("Pet Information Update Positive Test")
                .withFailMessage("Updating the Pet is unsuccessful")
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(petToBeCreated);

        assertThat(updatedPet.getStatus())
                .as("Pet Information Update Positive Test")
                .withFailMessage("Updating the Pet is unsuccessful")
                .isNotEqualTo(createdPet.getStatus());
    }

    /**
     * GIVEN - A Pet exists in the database
     * WHEN - Trying to upload an image to the Pet
     * THEN - The image is uploaded successfully
     */
    @Test
    void updateExistingPetWithImageSuccessfulTest() {
        Pet createdPet = petRequestHandler.createNewPet(petToBeCreated);
        File file = new File("src/test/resources/testdata/pictures/dogmeat_picture.jpg");
        String additionalMetaData = "picture";


        APIResponse response = petRequestHandler.uploadPictureToPet(createdPet.getId(), file, additionalMetaData);

        assertThat(response.getMessage())
                .as("Picture Upload Positive Test")
                .withFailMessage("Uploading picture is unsuccessful.")
                .contains(file.getName());

        assertThat(response.getMessage())
                .as("Picture Upload Positive Test")
                .withFailMessage("Uploading picture is unsuccessful.")
                .contains(additionalMetaData);
    }

    /**
     * GIVEN - A Pet exists in the database
     * WHEN - Trying to change the Pet name, status
     * THEN - The correct Pet is changed, as expected
     */
    @Test
    void updateExistingPetWithPOSTSuccessfulTest() {
        LOG.info("Running: updateExistingPetWithPOSTSuccessfulTest");

        Pet createdPet = petRequestHandler.createNewPet(petToBeCreated);
        createdPet.setName(PetStatusEnum.sold.getStatusName());
        createdPet.setStatus(PetStatusEnum.sold);

        APIResponse response = petRequestHandler.updatePetWithFormData(createdPet);
        Pet updatedPet = petRequestHandler.getPetByID(createdPet.getId());

        assertThat(response.getCode())
                .as("Pet Information Update Positive Test")
                .withFailMessage("Updating the Pet is unsuccessful")
                        .isEqualTo(HttpStatus.SC_OK);

        assertThat(response.getMessage())
                .as("Pet Information Update Positive Test")
                .withFailMessage("Updating the Pet is unsuccessful")
                .isEqualTo(createdPet.getId());

        assertThat(updatedPet)
                .as("Pet Information Update Positive Test")
                .withFailMessage("Updating the Pet is unsuccessful")
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(createdPet);
    }

    @Test
    void updateExistingPetWithPOSTUnsuccessfulTest() {
        LOG.info("Running: updateExistingPetWithPOSTUnsuccessfulTest");

        APIResponse response = petRequestHandler.failToUpdatePetWithFormData(String.valueOf(generateRandomNumber()));

        assertThat(response.getCode())
                .as("Pet Information Update Negative Test")
                .isEqualTo(HttpStatus.SC_NOT_FOUND);

        assertThat(response.getMessage())
                .as("Pet Information Update Negative Test")
                .isEqualTo("not found");
    }

}
