package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.Location;
import ro.msg.learning.shop.Models.Product;
import ro.msg.learning.shop.Models.Stock;
import java.util.List;

public interface IStockRepo extends CrudRepository<Stock, Integer> {
    List<Stock> findStocksByProductAndQuantityGreaterThanEqual(Product product, int quantity);
    Stock findFirstByProductOrderByQuantityDesc(Product product);
    Stock findByProductAndLocation(Product product, Location location);
}
