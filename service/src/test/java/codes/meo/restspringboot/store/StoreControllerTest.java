package codes.meo.restspringboot.store;

import codes.meo.common.api.exception.ApiException;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Set;

import static codes.meo.common.api.exception.ApiExceptionMatcher.isApiException;
import static codes.meo.restspringboot.store.StoreExceptionType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * Unit tests for {@link StoreController}.
 */
public class StoreControllerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    StoreController controller;

    @Before
    public void setUp() {
        controller = new StoreController();
    }

    @Test
    public void placeOrder() {
        Order order = new Order();
        assertThat(order.getId()).isNull();
        assertThat(controller.placeOrder(order)).isSameAs(order);
        assertThat(order.getId()).isGreaterThan(9999L);
    }

    @Test
    public void placeMultipleOrders() {

        Order orderA = controller.placeOrder(new Order());
        Order orderB = controller.placeOrder(new Order());
        Order orderC = controller.placeOrder(new Order());

        assertThat(orderA.getId()).isGreaterThan(9999L);
        assertThat(orderB.getId()).isGreaterThan(9999L);
        assertThat(orderC.getId()).isGreaterThan(9999L);

        Set<Long> orderIds = new HashSet<>();
        assertThat(orderIds.add(orderA.getId())).as("non-unique order ID").isTrue();
        assertThat(orderIds.add(orderB.getId())).as("non-unique order ID").isTrue();
        assertThat(orderIds.add(orderC.getId())).as("non-unique order ID").isTrue();

    }

    @Test
    public void placeOrderNull() {
        thrown.expect(isApiException(INVALID_ORDER));
        controller.placeOrder(null);
    }

    @Test
    public void getOrderById() {
        Order order = controller.placeOrder(new Order());
        assertThat(controller.getOrderById(order.getId())).isSameAs(order);
    }

    @Test
    public void getOrderByIdNotFound() {
        Long orderId = Long.MAX_VALUE;
        thrown.expect(isApiException(ORDER_NOT_FOUND, String.valueOf(orderId)));
        controller.getOrderById(orderId);
    }

    @Test
    public void getOrderByIdNull() {
        thrown.expect(isApiException(INVALID_ORDER_ID));
        controller.getOrderById(null);
    }

    @Test
    public void getOrderByIdLowerThanMinimum() {
        thrown.expect(isApiException(INVALID_ORDER_ID));
        controller.getOrderById(9999L);
    }

    @Test
    public void deleteOrder() {
        Order order = controller.placeOrder(new Order());
        controller.deleteOrder(order.getId());
        assertOrderHasBeenDeleted(order.getId());
    }

    @Test
    public void deleteOrderNotFound() {
        Long orderId = Long.MAX_VALUE;
        thrown.expect(isApiException(ORDER_NOT_FOUND, String.valueOf(orderId)));
        controller.deleteOrder(orderId);
    }

    @Test
    public void deleteOrderNull() {
        thrown.expect(isApiException(INVALID_ORDER_ID));
        controller.deleteOrder(null);
    }

    @Test
    public void deleteOrderIdLowerThanMinimum() {
        thrown.expect(isApiException(INVALID_ORDER_ID));
        controller.deleteOrder(9999L);
    }

    private void assertOrderHasBeenDeleted(Long orderId) {
        try {
            controller.getOrderById(orderId);
            fail(String.format("Deleting order '%s' was not successful.", orderId));
        } catch (ApiException expected) {
            MatcherAssert.assertThat(expected, isApiException(ORDER_NOT_FOUND, String.valueOf(orderId)));
        }
    }
}