package ro.msg.learning.shop.models;

import lombok.*;

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
