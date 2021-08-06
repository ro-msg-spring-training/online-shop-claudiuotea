package ro.msg.learning.shop.services.interfaces;

import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.models.Product;

import java.util.List;

public interface IProductService {
    Product findProduct(int productId);

    Product createProduct(ProductDTO productDTO);

    void deleteProduct(int productId);

    List<ProductDTO> readAllProducts();

    Product updateProduct(ProductDTO productDTO);
}
