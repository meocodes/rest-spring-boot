package codes.meo.restspringboot.store;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

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
}