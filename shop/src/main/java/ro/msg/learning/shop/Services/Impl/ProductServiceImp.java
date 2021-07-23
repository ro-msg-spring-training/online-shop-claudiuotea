package ro.msg.learning.shop.Services.Impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTOs.ProductDTO;
import ro.msg.learning.shop.Models.Product;
import ro.msg.learning.shop.Models.ProductCategory;
import ro.msg.learning.shop.Models.Supplier;
import ro.msg.learning.shop.Repositories.Interfaces.IProductCategoryRepo;
import ro.msg.learning.shop.Repositories.Interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.Interfaces.ISupplierRepo;
import ro.msg.learning.shop.Services.Interfaces.IProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImp implements IProductService {
    private IProductRepo productRepo;
    private IProductCategoryRepo productCategoryRepo;
    private ISupplierRepo supplierRepo;
    private ModelMapper modelMapper;

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


    //Using modelMapper for conversions
    @Override
    public ProductDTO convertToDTO(Product p){
        ProductDTO productDTO = modelMapper.map(p,ProductDTO.class);
        return productDTO;
    }

    @Override
    public Product convertToEntity(ProductDTO p){
        Product pr = modelMapper.map(p,Product.class);
        return pr;
    }

    @Override
    public void deleteProduct(int productId) {
        productRepo.deleteById(productId);
    }

    @Override
    public List<ProductDTO> readAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepo.findAll().forEach(p->products.add(p));
        return products.stream().map(x->this.convertToDTO(x)).collect(Collectors.toList());
    }

    @Override
    public void updateProduct(int id, String description, BigDecimal price, Double weight, String imageurl, Integer supplier) {
        Supplier s = supplierRepo.findById(supplier).orElseThrow(()->new RuntimeException("Supplier doesn't exist!"));
        Product p = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product doesn't exist!"));
        p.setDescription(description);
        p.setPrice(price);
        p.setWeight(weight);
        p.setImageUrl(imageurl);
        p.setSupplier(s);
        productRepo.save(p);
    }
}
