package taco.data;

import taco.domain.Order;

public interface OrderRepository {

    public Order save(Order order);
}
