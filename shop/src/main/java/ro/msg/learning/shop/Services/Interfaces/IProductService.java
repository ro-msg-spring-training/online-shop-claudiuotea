package ro.msg.learning.shop.Services.Interfaces;

import ro.msg.learning.shop.Models.Product;

import java.math.BigDecimal;
import java.util.Optional;

public interface IProductService {
    Optional<Product> findProduct(int productId);

    void createProduct(String name, String description, BigDecimal price, double weight, int productCategoryId, int supplierId, String imageUrl);


    void deleteProduct(int productId);

    Iterable<Product> readAllProducts();
}
