package ro.msg.learning.shop.services.interfaces;

import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Optional<Product> findProduct(int productId);


    Product createProduct(ProductDTO productDTO);

    ProductDTO convertToDTO(Product p);

    Product convertToEntity(ProductDTO p);

    void deleteProduct(int productId);

    List<ProductDTO> readAllProducts();

    Product updateProduct(ProductDTO productDTO);
}
