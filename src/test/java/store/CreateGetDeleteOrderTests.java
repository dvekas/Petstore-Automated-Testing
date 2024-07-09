package store;

import org.apache.http.HttpStatus;
import org.jozsef.daniel.vekas.controller.store.OrderRequests;
import org.jozsef.daniel.vekas.model.APIRespone;
import org.jozsef.daniel.vekas.model.factories.store.OrderBuilder;
import org.jozsef.daniel.vekas.model.store.Order;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateGetDeleteOrderTests {

    Order orderToCreate;
    OrderRequests orderRequestHandler;

    /**
     * Creates data that is needed to the tests
     */
    @BeforeMethod
    void setUpTestData() {
        orderToCreate = new OrderBuilder()
                .id(String.valueOf(generateRandomID()))
                .petId(generateRandomID())
                .quantity(100)
                .shipDate("2024-09-09T15:06:48.008+0000")
                .status("placed")
                .complete(false)
                .build();

        orderRequestHandler = new OrderRequests();
    }

    /**
     * WHEN - Trying to create a new Order, via an API call
     * THEN - Creating it is successful with correct data
     */
    @Test (priority = 1)
    void createNewOrderSuccessfulTest() {
        System.out.println("Running: createNewOrderSuccessfulTest");
        Order createdOrder = orderRequestHandler.createNewOrder(orderToCreate);

        assertThat(createdOrder).as("Order Creation Positive Test").usingRecursiveComparison().isEqualTo(orderToCreate);
        getOrderByIDAndAssertResponse(createdOrder.getId());
    }

    /**
     * WHEN - Trying to create a new Order, via an API call, with faulty data
     * THEN - Creating it is unsuccessful
     */
    @Test (priority = 2)
    void createNewOrderUnsuccessfulTest() {
        System.out.println("Running: createNewOrderUnsuccessfulTest");
        orderToCreate.setId("ERROR");
        APIRespone response = orderRequestHandler.failToCreateOrder(orderToCreate);

        assertThat(response.getCode()).as("Order Creation Negative Test").isEqualTo(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        assertThat(response.getMessage()).as("Order Creation Negative Test").isEqualTo("something bad happened");
        testSuccessfulConsoleMessage();
    }

    private void getOrderByIDAndAssertResponse(String orderID) {
        Order requestedOrder = orderRequestHandler.getOrderByID(orderID);

        assertThat(requestedOrder).usingRecursiveComparison().isEqualTo(orderToCreate);
        testSuccessfulConsoleMessage();
    }

    /**
     * Generates a random number, between 1000 and 90000, to serve as ID.
     *
     * @return Generated number
     */
    private static int generateRandomID() {
        Random rnd = new Random();
        return 1000 + rnd.nextInt(90000);
    }

    private void testSuccessfulConsoleMessage() {
        System.out.println("Test successful");
        System.out.println("----------------------\n");
    }
}
