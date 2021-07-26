package ro.msg.learning.shop.Repositories.interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.models.Location;
import ro.msg.learning.shop.models.Product;
import ro.msg.learning.shop.models.Stock;
import java.util.List;

public interface IStockRepo extends CrudRepository<Stock, Integer> {
    List<Stock> findStocksByProductAndQuantityGreaterThanEqual(Product product, int quantity);
    Stock findFirstByProductOrderByQuantityDesc(Product product);
    Stock findByProductAndLocation(Product product, Location location);
}
