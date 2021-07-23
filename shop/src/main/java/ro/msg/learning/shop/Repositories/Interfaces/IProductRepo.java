package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.Product;

public interface IProductRepo extends CrudRepository<Product,Integer> {
}
