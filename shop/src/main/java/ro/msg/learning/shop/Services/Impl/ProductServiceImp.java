package ro.msg.learning.shop.Services.Impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTOs.OrderDTO;
import ro.msg.learning.shop.DTOs.ProductDTO;
import ro.msg.learning.shop.Models.*;
import ro.msg.learning.shop.Repositories.Interfaces.*;
import ro.msg.learning.shop.Services.Interfaces.IProductService;
import ro.msg.learning.shop.Strategies.Impl.MostAbundantStrategy;
import ro.msg.learning.shop.Strategies.Impl.SingleLocationStrategy;
import ro.msg.learning.shop.Strategies.Interfaces.ILocationStrategy;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImp implements IProductService {
    private final IProductRepo productRepo;
    private final IProductCategoryRepo productCategoryRepo;
    private final ISupplierRepo supplierRepo;

    //TODO:use a class made by you instead of this
    private final ModelMapper modelMapper;

    //TODO:remove this
//    private final IStockRepo stockRepo;
//    private final ILocationRepo locationRepo;
//    @Qualifier("abundantStrategy")
//    private final ILocationStrategy locationStrategy;

    @Override
    public void test(){
        //Stock s = stockRepo.findByProductAndLocation(productRepo.findById(1).get(),locationRepo.findById(1).get());
        //System.out.println(s.getQuantity());
//        HashMap<Integer,Integer> prods = new HashMap<>();
//        prods.put(2,100000);
//        prods.put(3,1);
//        prods.put(1,11);
//        locationStrategy.getLocations(prods).forEach(s-> System.out.println(s.getProduct().getId()+"--"+s.getLocation().getId()+"--"+s.getQuantity()));

//        HashMap<Integer,Integer> prods = new HashMap<>();
//        prods.put(2,10);
//        prods.put(3,1);
//        prods.put(1,11);
//        OrderDTO order = new OrderDTO(Timestamp.valueOf(LocalDateTime.now()),"Bucharest","omania","Tara","Da",prods);
//        locationStrategy.getLocations(order.getProducts()).forEach(s-> System.out.println(s.getProduct().getId()+"--"+s.getLocation().getId()+"--"+s.getQuantity()));
    }

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

        //TODO: use builder from LOMBOK here
        p.setDescription(description);
        p.setPrice(price);
        p.setWeight(weight);
        p.setImageUrl(imageurl);
        p.setSupplier(s);
        productRepo.save(p);
    }

}
