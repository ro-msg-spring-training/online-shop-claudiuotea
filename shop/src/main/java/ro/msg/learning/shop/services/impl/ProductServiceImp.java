package ro.msg.learning.shop.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.Converter;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.exceptions.CreateResourceException;
import ro.msg.learning.shop.exceptions.ResourceNotFoundException;
import ro.msg.learning.shop.models.Product;
import ro.msg.learning.shop.models.ProductCategory;
import ro.msg.learning.shop.models.Supplier;
import ro.msg.learning.shop.Repositories.interfaces.IProductCategoryRepo;
import ro.msg.learning.shop.Repositories.interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.interfaces.ISupplierRepo;
import ro.msg.learning.shop.services.interfaces.IProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImp implements IProductService {
    private final IProductRepo productRepo;
    private final IProductCategoryRepo productCategoryRepo;
    private final ISupplierRepo supplierRepo;
    private final Converter dtoConverter;

    @Override
    public Product findProduct(int productId) {
        return productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " not found"));
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        ProductCategory pc = productCategoryRepo.findById(productDTO.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Product category with id " + productDTO.getCategoryId() + " not found!"));
        Supplier s = supplierRepo.findById(productDTO.getSupplierId()).orElseThrow(()->new ResourceNotFoundException("Supplier with id: " + productDTO.getSupplierId() + " not found!"));
        Product p = new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getPrice(), productDTO.getWeight(), pc, s, productDTO.getImageUrl());

        try {
            return productRepo.save(p);
        }
        catch(RuntimeException ex){
            throw new CreateResourceException("Cannot create entity because of unique constraints.");
        }
    }

    @Override
    public void deleteProduct(int productId) {
        try{
            productRepo.deleteById(productId);
        }
        catch(RuntimeException ex){
            throw new ResourceNotFoundException("No product with " + productId + " exists!");
        }
    }

    @Override
    public List<ProductDTO> readAllProducts() {
        List<Product> products = new ArrayList<>();
        productRepo.findAll().forEach(products::add);
        return products.stream().map(dtoConverter::productToProductDTO).collect(Collectors.toList());
    }

    @Override
    public Product updateProduct(ProductDTO productDTO) {
        Supplier s = supplierRepo.findById(productDTO.getSupplierId()).orElseThrow(() -> new RuntimeException("Supplier doesn't exist!"));
        Product p = productRepo.findById(productDTO.getId()).orElseThrow(() -> new RuntimeException("Product doesn't exist!"));

        p.setDescription(productDTO.getDescription());
        p.setPrice(productDTO.getPrice());
        p.setWeight(productDTO.getWeight());
        p.setImageUrl(productDTO.getImageUrl());
        p.setSupplier(s);
        return productRepo.save(p);
    }
}
