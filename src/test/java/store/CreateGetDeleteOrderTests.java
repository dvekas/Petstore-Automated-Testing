package store;

import org.jozsef.daniel.vekas.controller.store.OrderRequests;
import org.jozsef.daniel.vekas.model.factories.store.OrderBuilder;
import org.jozsef.daniel.vekas.model.store.Order;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateGetDeleteOrderTests {

    Order orderToCreate;
    OrderRequests orderRequestHandler;

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

    @Test (priority = 1)
    void createNewOrderSuccessfulTest() {
        System.out.println("Running: createNewOrderSuccessfulTest");
        Order createdOrder = orderRequestHandler.createNewOrder(orderToCreate);

        assertThat(createdOrder).as("Order Creation Positive Test").usingRecursiveComparison().isEqualTo(orderToCreate);
        getOrderByIDAndAssertResponse(createdOrder.getId());
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
