package ro.msg.learning.shop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.DTOs.AddProductDTO;
import ro.msg.learning.shop.Exceptions.ProductNotFound;
import ro.msg.learning.shop.Models.Product;
import ro.msg.learning.shop.Services.Interfaces.IProductService;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping(value = "/products/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable int id){
        Optional<Product> p = productService.findProduct(id);
        if(p.isEmpty())
            throw new ProductNotFound();
        else return p.get();
    }

    @PostMapping(value = "/products")
    public ResponseEntity<String> addProduct(@RequestBody AddProductDTO product){
        try{
            productService.createProduct(product.getName(),product.getDescription(),product.getPrice(),product.getWeight(),product.getCategoryId(),product.getSupplierId(),product.getImageUrl());
            return ResponseEntity.ok("Success!");
        }
        catch(RuntimeException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
