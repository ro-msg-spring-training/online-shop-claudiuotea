package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.ProductCategory;

public interface IProductCategoryRepo extends CrudRepository<ProductCategory,Integer> {
}
