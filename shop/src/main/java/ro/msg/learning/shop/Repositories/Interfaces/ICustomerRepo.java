package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.Customer;

public interface ICustomerRepo extends CrudRepository<Customer,Integer> {
}
