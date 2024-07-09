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
    @Test
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
    @Test
    void createNewOrderUnsuccessfulTest() {
        System.out.println("Running: createNewOrderUnsuccessfulTest");
        orderToCreate.setId("ERROR");
        APIRespone response = orderRequestHandler.failToCreateOrder(orderToCreate);

        assertThat(response.getCode()).as("Order Creation Negative Test").isEqualTo(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        assertThat(response.getMessage()).as("Order Creation Negative Test").isEqualTo("something bad happened");
        testSuccessfulConsoleMessage();
    }

    /**
     * GIVEN - An Order exist in the database
     * WHEN - Requesting the Order via an API call
     * THEN - The correct order is downloaded
     */
    @Test
    void getOrderSuccessfulTest() {
        System.out.println("Running: getOrderSuccessfulTest");
        Order createdOrder = orderRequestHandler.createNewOrder(orderToCreate);

        getOrderByIDAndAssertResponse(createdOrder.getId());
    }

    /**
     * WHEN - Requesting an Order with a non-existent ID via an API call
     * THEN - Error message is returned
     */
    @Test
    void getOrderUnsuccessfulTest() {
        System.out.println("Running: getOrderUnsuccessfulTest");

        getNonExistentOrderByIDAndAssertResponse(orderToCreate.getId());
    }

    /**
     * GIVEN - Order exists in the database
     * WHEN - Trying to delete the Order
     * THEN - The correct Order is deleted
     */
    @Test
    void deleteOrderSuccessfulTest() {
        System.out.println("Running: deleteOrderSuccessfulTest");
        Order createdOrder = orderRequestHandler.createNewOrder(orderToCreate);

        APIRespone response = orderRequestHandler.deleteOrder(createdOrder);
        assertThat(response.getCode()).as("Order deletion Positive Test").isEqualTo(HttpStatus.SC_OK);
        assertThat(response.getMessage()).as("Order deletion Positive Test").isEqualTo(createdOrder.getId());
        getNonExistentOrderByIDAndAssertResponse(createdOrder.getId());
    }

    /**
     * WHEN - Trying to delete a non-existent order
     * THEN - The correct Error message is returned
     */
    @Test
    void deleteOrderUnsuccessfulTest() {
        System.out.println("Running: deleteOrderUnsuccessfulTest");

        APIRespone response = orderRequestHandler.failToDeleteOrder(orderToCreate.getId());
        assertThat(response.getCode()).as("Order deletion Positive Test").isEqualTo(HttpStatus.SC_NOT_FOUND);
        assertThat(response.getMessage()).as("Order deletion Positive Test").isEqualTo("Order Not Found");
    }

    private void getOrderByIDAndAssertResponse(String orderID) {
        Order requestedOrder = orderRequestHandler.getOrderByID(orderID);

        assertThat(requestedOrder).usingRecursiveComparison().isEqualTo(orderToCreate);
        testSuccessfulConsoleMessage();
    }

    private void getNonExistentOrderByIDAndAssertResponse(String orderID) {
        APIRespone response = orderRequestHandler.getNonExistentOrderByID(orderID);

        assertThat(response.getCode()).isEqualTo(1);
        assertThat(response.getMessage()).isEqualTo("Order not found");
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
