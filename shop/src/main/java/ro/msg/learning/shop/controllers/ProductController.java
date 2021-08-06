package ro.msg.learning.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.models.Product;
import ro.msg.learning.shop.services.interfaces.IProductService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {
    @Autowired
    private final IProductService productService;

    @GetMapping(value = "/products/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.findProduct(id);
    }

    @PostMapping(value = "/products")
    public Product addProduct(@RequestBody ProductDTO product) {
        return productService.createProduct(product);
    }

    @PutMapping(value = "/products/{id}")
    public Product updateProduct(@RequestBody ProductDTO product, @PathVariable int id) {
        product.setId(id);
        return productService.updateProduct(product);
    }

    @GetMapping(value = "/products")
    public List<ProductDTO> readALlProducts() {
        return productService.readAllProducts();
    }

    @DeleteMapping(value = "/products/{id}")
    void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}
