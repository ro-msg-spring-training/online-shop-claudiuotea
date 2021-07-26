package ro.msg.learning.shop.Repositories.interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.models.Product;

public interface IProductRepo extends CrudRepository<Product,Integer> {
}
