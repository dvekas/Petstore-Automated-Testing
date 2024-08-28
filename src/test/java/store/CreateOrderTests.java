package store;

import org.apache.http.HttpStatus;
import org.dvekas.model.APIResponse;
import org.dvekas.model.store.Order;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateOrderTests extends OrderTestBase {

    /**
     * WHEN - Trying to create a new Order, via an API call
     * THEN - Creating it is successful with correct data
     */
    @Test
    void createNewOrderSuccessfulTest() {
        LOG.info("Running: createNewOrderSuccessfulTest");

        Order createdOrder = orderRequestHandler.createNewOrder(orderToCreate);

        assertThat(createdOrder)
                .as("Order Creation Positive Test")
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(orderToCreate);

        getOrderByIDAndAssertResponse(createdOrder.getId());
    }

    /**
     * WHEN - Trying to create a new Order, via an API call, with faulty data
     * THEN - Creating it is unsuccessful
     */
    @Test
    void createNewOrderUnsuccessfulTest() {
        LOG.info("Running: createNewOrderUnsuccessfulTest");

        orderToCreate.setId("ERROR");
        APIResponse response = orderRequestHandler.failToCreateOrder(orderToCreate);

        assertThat(response.getCode())
                .as("Order Creation Negative Test")
                .isEqualTo(HttpStatus.SC_INTERNAL_SERVER_ERROR);

        assertThat(response.getMessage())
                .as("Order Creation Negative Test")
                .isEqualTo("something bad happened");
    }

}