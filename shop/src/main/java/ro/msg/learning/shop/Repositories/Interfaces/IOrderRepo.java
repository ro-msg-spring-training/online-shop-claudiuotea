package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.OrderTable;

public interface IOrderRepo extends CrudRepository<OrderTable,Integer> {
}
