package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.Orders;

public interface IOrderRepo extends CrudRepository<Orders,Integer> {
}
