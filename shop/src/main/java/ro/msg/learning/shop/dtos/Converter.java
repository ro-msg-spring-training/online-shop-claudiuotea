package ro.msg.learning.shop.dtos;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.Repositories.interfaces.IProductCategoryRepo;
import ro.msg.learning.shop.Repositories.interfaces.ISupplierRepo;
import ro.msg.learning.shop.models.Product;

@Component
@AllArgsConstructor
public class Converter {
    private final IProductCategoryRepo productCategoryRepo;
    private final ISupplierRepo supplierRepo;

    public ProductDTO productToProductDTO (Product prod){
        return ProductDTO.builder()
                .categoryId(prod.getProductCategory().getId())
                .name(prod.getName())
                .description(prod.getDescription())
                .categoryName(prod.getProductCategory().getName())
                .categoryDescription(prod.getProductCategory().getDescription())
                .supplierId(prod.getSupplier().getId())
                .price(prod.getPrice())
                .weight(prod.getWeight())
                .imageUrl(prod.getImageUrl())
                .build();
    }

    public Product productDtoToProduct(ProductDTO productDTO){
        return Product.builder()
                .name(productDTO.name)
                .description(productDTO.description)
                .price(productDTO.price)
                .weight(productDTO.weight)
                .productCategory(productCategoryRepo.findById(productDTO.categoryId).orElseThrow(() -> new RuntimeException("Product category doesn't exist!")))
                .supplier(supplierRepo.findById(productDTO.id).orElseThrow(()->new RuntimeException("Supplier not found!")))
                .imageUrl(productDTO.imageUrl)
                .build();
    }
}
