package store;

import org.apache.http.HttpStatus;
import org.dvekas.model.APIResponse;
import org.dvekas.model.store.Order;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteOrderTests extends OrderBaseTests {

    /**
     * GIVEN - Order exists in the database
     * WHEN - Trying to delete the Order
     * THEN - The correct Order is deleted
     */
    @Test
    void deleteOrderSuccessfulTest() {
        LOG.info("Running: deleteOrderSuccessfulTest");
        Order createdOrder = orderRequestHandler.createNewOrder(orderToCreate);

        APIResponse response = orderRequestHandler.deleteOrder(createdOrder);
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
        LOG.info("Running: deleteOrderUnsuccessfulTest");

        APIResponse response = orderRequestHandler.failToDeleteOrder(String.valueOf(generateRandomID()));
        assertThat(response.getCode()).as("Order deletion Positive Test").isEqualTo(HttpStatus.SC_NOT_FOUND);
        assertThat(response.getMessage()).as("Order deletion Positive Test").isEqualTo("Order Not Found");
    }

}
