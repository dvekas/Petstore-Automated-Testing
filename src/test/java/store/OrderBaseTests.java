package store;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dvekas.controller.REST.RequestHandler;
import org.dvekas.controller.store.OrderRequests;
import org.dvekas.model.APIResponse;
import org.dvekas.model.store.Order;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderBaseTests {

    protected static final Logger LOG = LogManager.getLogger(RequestHandler.class);
    protected static final String orderYamlFilePath = "src/test/resources/testData/yaml/TestOrderData.yaml";

    Order orderToCreate;
    OrderRequests orderRequestHandler;

    /**
     * Creates data that is needed to the tests
     */
    @BeforeMethod
    void setUpTestData() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        try {
            orderToCreate = mapper.readValue(new File(orderYamlFilePath), Order.class);
            orderToCreate.setPetId(generateRandomID());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        orderRequestHandler = new OrderRequests();
    }

    /**
     * Downloads an Order (via ID) from the API and asserts if it is created as planned.
     *
     * @param orderID ID of the order to be tested.
     */
    protected void getOrderByIDAndAssertResponse(String orderID) {
        Order requestedOrder = orderRequestHandler.getOrderByID(orderID);

        assertThat(requestedOrder)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(orderToCreate);
    }

    /**
     * Requests an Order with ain incorrect ID.
     *
     * @param orderID ID for a non-existent order.
     */
    protected void getNonExistentOrderByIDAndAssertResponse(String orderID) {
        APIResponse response = orderRequestHandler.getNonExistentOrderByID(orderID);

        assertThat(response.getCode()).isEqualTo(1);
        assertThat(response.getMessage()).isEqualTo("Order not found");
    }

    /**
     * Generates a random number, between 1000 and 90000, to serve as ID.
     *
     * @return Generated number
     */
    protected static int generateRandomID() {
        Random rnd = new Random();
        return 1000 + rnd.nextInt(90000);
    }

}
