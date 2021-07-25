package ro.msg.learning.shop.Models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Product extends BaseEntity<Integer>{
    @NotBlank(message = "Name must be completed")
    private String name;
    @NotBlank(message = "Description must be completed")
    private String description;
    @Positive
    private BigDecimal price;
    @Positive
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "product_category")
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "supplier")
    private Supplier supplier;

    @NotBlank(message = "ImageUrl must be completed")
    @Column(name = "image_url")
    private String imageUrl;

}
