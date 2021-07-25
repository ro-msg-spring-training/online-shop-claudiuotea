package ro.msg.learning.shop.Services.Interfaces;

import ro.msg.learning.shop.DTOs.ProductDTO;
import ro.msg.learning.shop.Models.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    void test();

    Optional<Product> findProduct(int productId);

    void createProduct(String name, String description, BigDecimal price, double weight, int productCategoryId, int supplierId, String imageUrl);


    ProductDTO convertToDTO(Product p);

    Product convertToEntity(ProductDTO p);

    void deleteProduct(int productId);

    List<ProductDTO> readAllProducts();

    void updateProduct(int id, String description, BigDecimal price, Double weight, String imageUrl, Integer supplierId);

}
