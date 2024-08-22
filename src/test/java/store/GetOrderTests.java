package store;

import org.dvekas.model.store.Order;
import org.testng.annotations.Test;

public class GetOrderTests extends OrderBaseTests {

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

}
