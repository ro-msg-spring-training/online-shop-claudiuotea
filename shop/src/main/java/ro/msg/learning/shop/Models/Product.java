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
@EqualsAndHashCode
public class Product extends BaseEntity<Integer>{
    @NotBlank(message = "Name must be completed")
    private String Name;
    @NotBlank(message = "Description must be completed")
    private String Description;
    @Positive
    private BigDecimal Price;
    @Positive
    private Double Weight;

    @ManyToOne
    @JoinColumn(name = "productcategory")
    private ProductCategory ProductCategory;

    @ManyToOne
    @JoinColumn(name = "supplier")
    private Supplier Supplier;

    @NotBlank(message = "ImageUrl must be completed")
    @Column(name = "imageurl")
    private String ImageUrl;

}
