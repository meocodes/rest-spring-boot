package codes.meo.restspringboot.store;

import org.springframework.stereotype.Component;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class StoreController implements StoreApi {

    private final static ConcurrentMap<Long, Order> ORDERS = new ConcurrentHashMap<>();

    public Order getOrderById(Long orderId) {

        if (orderId == null) {
            throw new BadRequestException();
        }

        if (!ORDERS.containsKey(orderId)) {
            throw new NotFoundException();
        }

        return ORDERS.get(orderId);
    }

    public Order placeOrder(Order order) {

        if (order == null) {
            throw new BadRequestException();
        }

        Long id = nextId();
        ORDERS.put(id, order.id(id));
        return order;
    }

    public void deleteOrder(Long orderId) {

        if (orderId == null) {
            throw new BadRequestException();
        }

        if (!ORDERS.containsKey(orderId)) {
            throw new NotFoundException();
        }

        ORDERS.remove(orderId);
    }

    private Long nextId() {

        if (ORDERS.isEmpty()) {
            return 10000L;
        }

        return Collections.max(ORDERS.keySet()) + 1;
    }
}