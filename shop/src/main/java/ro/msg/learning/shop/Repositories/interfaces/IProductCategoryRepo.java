package ro.msg.learning.shop.Repositories.interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.models.ProductCategory;

public interface IProductCategoryRepo extends CrudRepository<ProductCategory,Integer> {
}
