package store;

import org.dvekas.model.pet.PetStatusEnum;
import org.dvekas.model.store.Order;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestOrderTests extends OrderBaseTests {

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
     * THEN - The requested map is returned, and contains the correct statuses
     */
    @Test
    void getInventorySuccessfulTest() {
        LOG.info("Running: getInventorySuccessfulTest");

        List<String> petStatusList = Stream.of(PetStatusEnum.values())
                                        .map(PetStatusEnum::name)
                                        .toList();
        Map<String,Integer> inventory = orderRequestHandler.getInventoryList();

        assertThat(inventory.keySet().containsAll(petStatusList)).isTrue();
    }

}
