package ro.msg.learning.shop.Repositories.interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.models.OrderDetail;

public interface IOrderDetailRepo extends CrudRepository<OrderDetail, Integer> {
}
