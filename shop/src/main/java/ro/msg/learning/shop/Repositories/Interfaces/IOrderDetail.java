package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.OrderDetail;
import ro.msg.learning.shop.Models.OrderDetailId;

public interface IOrderDetail extends CrudRepository<OrderDetail, OrderDetailId> {
}
