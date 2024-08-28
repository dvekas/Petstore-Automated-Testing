package store;

import org.dvekas.controller.pet.PetRequests;
import org.dvekas.model.pet.PetStatusEnum;
import org.dvekas.model.store.Order;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestOrderTests extends OrderTestBase {

    /**
     * GIVEN - An Order exist in the database
     * WHEN - Requesting the Order via an API call
     * THEN - The correct order is downloaded
     */
    @Test
    void getOrderSuccessfulTest() {
        LOG.info("Running: getOrderSuccessfulTest");

        Order createdOrder = orderRequestHandler.createNewOrder(orderToCreate);

        getOrderByIDAndAssertResponse(createdOrder.getId());
    }

    /**
     * WHEN - Requesting an Order with a non-existent ID via an API call
     * THEN - Error message is returned
     */
    @Test
    void getOrderUnsuccessfulTest() {
        LOG.info("Running: getOrderUnsuccessfulTest");

        getNonExistentOrderByIDAndAssertResponse(String.valueOf(generateRandomID()));
    }

    /**
     * WHEN - Requesting a map of pet statuses to quantities
     * THEN - The requested map is returned, and contains the correct statuses, quantities
     */
    @Test
    void getInventorySuccessfulTest() {
        LOG.info("Running: getInventorySuccessfulTest");

        PetRequests petRequestHandler = new PetRequests();
        Map<String,Integer> expectedInventory = new HashMap<>();
        PetStatusEnum.stream().forEach(statusEnum -> {
            expectedInventory.put(statusEnum.getStatusName(), petRequestHandler.getPetsByStatus(statusEnum).size());
        });

        Map<String,Integer> inventory = orderRequestHandler.getInventoryList();

        assertThat(inventory.entrySet().containsAll(expectedInventory.entrySet()))
                .as("Downloading Inventory State Positive Test")
                .isTrue();
    }

}