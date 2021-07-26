package ro.msg.learning.shop.Repositories.interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.models.Customer;

public interface ICustomerRepo extends CrudRepository<Customer,Integer> {
}
