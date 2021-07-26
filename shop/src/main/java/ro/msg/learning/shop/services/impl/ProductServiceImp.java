package ro.msg.learning.shop.services.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.models.Product;
import ro.msg.learning.shop.models.ProductCategory;
import ro.msg.learning.shop.models.Supplier;
import ro.msg.learning.shop.Repositories.interfaces.IProductCategoryRepo;
import ro.msg.learning.shop.Repositories.interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.interfaces.ISupplierRepo;
import ro.msg.learning.shop.services.interfaces.IProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImp implements IProductService {
    private final IProductRepo productRepo;
    private final IProductCategoryRepo productCategoryRepo;
    private final ISupplierRepo supplierRepo;

    private final ModelMapper modelMapper;


    @Override
    public Optional<Product> findProduct(int productId) {
        return productRepo.findById(productId);
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        ProductCategory pc = productCategoryRepo.findById(productDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Product doesn't exist"));
        Supplier s = supplierRepo.findById(productDTO.getSupplierId()).orElseThrow(() -> new RuntimeException("Supplier doesn't exist"));
        Product p = new Product(productDTO.getName(),productDTO.getDescription(),productDTO.getPrice(),productDTO.getWeight(),pc,s,productDTO.getImageUrl());
        return productRepo.save(p);
    }


    //Using modelMapper for conversions
    @Override
    public ProductDTO convertToDTO(Product p){
        return modelMapper.map(p,ProductDTO.class);
    }

    @Override
    public Product convertToEntity(ProductDTO p){
        return modelMapper.map(p,Product.class);
    }

    @Override
    public void deleteProduct(int productId) {
        productRepo.deleteById(productId);
    }

    @Override
    public List<ProductDTO> readAllProducts() {


        List<Product> products = new ArrayList<>();
        productRepo.findAll().forEach(products::add);
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public Product updateProduct(ProductDTO productDTO) {
        Supplier s = supplierRepo.findById(productDTO.getSupplierId()).orElseThrow(()->new RuntimeException("Supplier doesn't exist!"));
        Product p = productRepo.findById(productDTO.getId()).orElseThrow(() -> new RuntimeException("Product doesn't exist!"));

        p.setDescription(productDTO.getDescription());
        p.setPrice(productDTO.getPrice());
        p.setWeight(productDTO.getWeight());
        p.setImageUrl(productDTO.getImageUrl());
        p.setSupplier(s);
        return productRepo.save(p);
    }

}
