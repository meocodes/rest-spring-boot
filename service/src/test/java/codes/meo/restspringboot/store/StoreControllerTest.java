package codes.meo.restspringboot.store;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * Unit tests for {@link StoreController}.
 */
public class StoreControllerTest {

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

    @Test(expected = BadRequestException.class)
    public void placeOrderNull() {
        controller.placeOrder(null);
    }

    @Test
    public void getOrderById() {
        Order order = controller.placeOrder(new Order());
        assertThat(controller.getOrderById(order.getId())).isSameAs(order);
    }

    @Test(expected = NotFoundException.class)
    public void getOrderByIdNotFound() {
        controller.getOrderById(9999L);
    }

    @Test(expected = BadRequestException.class)
    public void getOrderByIdNull() {
        controller.getOrderById(null);
    }

    @Test
    public void deleteOrder() {
        Order order = controller.placeOrder(new Order());
        controller.deleteOrder(order.getId());
        try {
            controller.getOrderById(order.getId());
            fail(String.format("Deleting order '%s' was not successful.", order.getId()));
        } catch (NotFoundException expected) {
        }
    }

    @Test(expected = NotFoundException.class)
    public void deleteOrderNotFound() {
        controller.deleteOrder(9999L);
    }

    @Test(expected = BadRequestException.class)
    public void deleteOrderNull() {
        controller.deleteOrder(null);
    }
}