package taco.data.jpa_implementations;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import taco.data.OrderRepository;
import taco.domain.Order;

import java.util.List;

public interface JpaOrderRepository extends CrudRepository<Order,Long>, OrderRepository {
}
