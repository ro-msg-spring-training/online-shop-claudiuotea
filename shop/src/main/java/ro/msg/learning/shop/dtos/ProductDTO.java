package ro.msg.learning.shop.dtos;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProductDTO {
    Integer id;
    Integer categoryId;
    String name;
    String description;
    String categoryName;
    String categoryDescription;
    Integer supplierId;
    BigDecimal price;
    Double weight;
    String imageUrl;
}
