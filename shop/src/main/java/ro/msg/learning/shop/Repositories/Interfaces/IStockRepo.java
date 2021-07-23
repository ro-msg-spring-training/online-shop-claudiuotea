package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.Stock;
import ro.msg.learning.shop.Models.StockId;

public interface IStockRepo extends CrudRepository<Stock, StockId> {
}
