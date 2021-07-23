package ro.msg.learning.shop.Models;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "productcategory")
public class ProductCategory extends BaseEntity<Integer>{
    private String Name;
    private String Description;

}
