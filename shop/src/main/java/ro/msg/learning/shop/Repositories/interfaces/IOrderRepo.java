package ro.msg.learning.shop.Repositories.interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.models.Orders;

public interface IOrderRepo extends CrudRepository<Orders,Integer> {
}
