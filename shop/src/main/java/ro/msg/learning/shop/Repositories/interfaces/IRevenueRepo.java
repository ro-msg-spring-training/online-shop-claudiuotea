package ro.msg.learning.shop.Repositories.interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.models.Revenue;

public interface IRevenueRepo extends CrudRepository<Revenue,Integer> {
}
