package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.Revenue;

public interface IRevenueRepo extends CrudRepository<Revenue,Integer> {
}
