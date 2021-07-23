package ro.msg.learning.shop.Services.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.Models.Product;
import ro.msg.learning.shop.Models.ProductCategory;
import ro.msg.learning.shop.Models.Supplier;
import ro.msg.learning.shop.Repositories.Interfaces.IProductCategoryRepo;
import ro.msg.learning.shop.Repositories.Interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.Interfaces.ISupplierRepo;
import ro.msg.learning.shop.Services.Interfaces.IProductService;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IProductServiceImp implements IProductService {
    private IProductRepo productRepo;
    private IProductCategoryRepo productCategoryRepo;
    private ISupplierRepo supplierRepo;

    @Override
    public Optional<Product> findProduct(int productId) {
        return productRepo.findById(productId);
    }

    @Override
    public void createProduct(String name, String description, BigDecimal price, double weight, int productCategoryId, int supplierId, String imageUrl) {
        ProductCategory pc = productCategoryRepo.findById(productCategoryId).orElseThrow(() -> new RuntimeException("Product doesn't exist"));
        Supplier s = supplierRepo.findById(supplierId).orElseThrow(() -> new RuntimeException("Supplier doesn't exist"));
        Product p = new Product(name,description,price,weight,pc,s,imageUrl);
        productRepo.save(p);
    }

    @Override
    public void deleteProduct(int productId) {
        productRepo.deleteById(productId);
    }

    @Override
    public Iterable<Product> readAllProducts() {
        return productRepo.findAll();
    }
}
