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
@ToString
@EqualsAndHashCode
public class ProductCategory extends BaseEntity<Integer>{
    private String name;
    private String description;

}
