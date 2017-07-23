package codes.meo.restspringboot.store;

import codes.meo.common.api.exception.ApiException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static codes.meo.restspringboot.store.StoreExceptionType.*;

@Component
public class StoreController implements StoreApi {

    private final static ConcurrentMap<Long, Order> ORDERS = new ConcurrentHashMap<>();
    private final long MIN_ORDER_ID = 10_000L;

    public Order getOrderById(Long orderId) {

        validate(orderId);

        if (!ORDERS.containsKey(orderId)) {
            throw new ApiException(ORDER_NOT_FOUND, String.valueOf(orderId));
        }

        return ORDERS.get(orderId);
    }

    public Order placeOrder(Order order) {

        if (order == null) {
            throw new ApiException(INVALID_ORDER);
        }

        Long id = nextId();
        ORDERS.put(id, order.id(id));
        return order;
    }

    public void deleteOrder(Long orderId) {

        validate(orderId);

        if (!ORDERS.containsKey(orderId)) {
            throw new ApiException(ORDER_NOT_FOUND, String.valueOf(orderId));
        }

        ORDERS.remove(orderId);
    }

    private Long nextId() {

        if (ORDERS.isEmpty()) {
            return MIN_ORDER_ID;
        }

        return Collections.max(ORDERS.keySet()) + 1;
    }

    private void validate(Long orderId) {
        if (orderId == null || orderId < MIN_ORDER_ID) {
            throw new ApiException(INVALID_ORDER_ID);
        }
    }
}