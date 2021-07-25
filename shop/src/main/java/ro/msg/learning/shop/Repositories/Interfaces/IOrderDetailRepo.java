package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.OrderDetail;

public interface IOrderDetail extends CrudRepository<OrderDetail, Integer> {
}
