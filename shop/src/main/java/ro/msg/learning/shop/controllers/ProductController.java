package ro.msg.learning.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.exceptions.ProductNotFound;
import ro.msg.learning.shop.models.Product;
import ro.msg.learning.shop.services.interfaces.IProductService;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class ProductController {
    @Autowired
    private final IProductService productService;

    @GetMapping(value = "/products/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable int id){
        Optional<Product> p = productService.findProduct(id);
        if(p.isEmpty())
            throw new ProductNotFound();
        else return p.get();
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO product){
        try{
            Product created = productService.createProduct(product);
            return new ResponseEntity<>(created, HttpStatus.OK);
        }
        catch(RuntimeException ex){
            return new ResponseEntity<>(null,HttpStatus.CONFLICT);
        }
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDTO product,@PathVariable int id){
        try{
            product.setId(id);
            Product updated = productService.updateProduct(product);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        catch(RuntimeException ex){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/products")
    @ResponseBody
    public List<ProductDTO> readALlProducts(){
        return productService.readAllProducts();
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Success!");
    }

}
