package codes.meo.restspringboot.store;

import codes.meo.restspringboot.BaseSystemTest;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.HTML;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

/**
 * System tests for {@link StoreApi}.
 */
public class StoreApiSystemTest extends BaseSystemTest {

    @Test
    public void postGetDeleteOrder() {

        Order order = new Order();

        Order postOrder = given().body(order).contentType(JSON)
                .when().post("store/orders")
                .then().statusCode(200).extract().as(Order.class);
        assertThat(postOrder.getId()).isNotNull();

        Order getOrder = when().get("store/orders/{id}", postOrder.getId())
                .then().statusCode(200).extract().as(Order.class);
        assertThat(getOrder).isEqualTo(postOrder);

        when().delete("store/orders/{id}", postOrder.getId()).then().statusCode(204);
        when().get("store/orders/{id}", postOrder.getId()).then().statusCode(404);
    }

    @Test
    public void getOrderByIdNotFound() {
        when().get("store/orders/{id}", Long.MAX_VALUE)
                .then().statusCode(404)
                .body("errors", hasSize(1))
                .body("errors[0].code", equalTo("132"))
                .body("errors[0].message", equalTo("The order '9223372036854775807' could not be found."));
    }

    @Test
    public void getOrderByIdLowerThanMinimum() {
        when().get("store/orders/{id}", 9999L)
                .then().statusCode(404)
                .body("errors", hasSize(1))
                .body("errors[0].code", equalTo("121"))
                .body("errors[0].message", equalTo("Please provide a valid order ID."));
    }

    @Test
    public void getStore() {
        when().get("store").then().statusCode(404).contentType(HTML);
    }

    @Test
    public void getStoreOrders() {
        when().get("store/orders").then().statusCode(405).contentType(HTML);
    }

    @Test
    public void deleteOrderByIdNotFound() {
        when().delete("store/orders/{id}", Long.MAX_VALUE)
                .then().statusCode(404)
                .body("errors", hasSize(1))
                .body("errors[0].code", equalTo("132"))
                .body("errors[0].message", equalTo("The order '9223372036854775807' could not be found."));
    }

    @Test
    public void deleteOrderByIdLowerThanMinimum() {
        when().delete("store/orders/{id}", 9999L)
                .then().statusCode(404)
                .body("errors", hasSize(1))
                .body("errors[0].code", equalTo("121"))
                .body("errors[0].message", equalTo("Please provide a valid order ID."));
    }
}