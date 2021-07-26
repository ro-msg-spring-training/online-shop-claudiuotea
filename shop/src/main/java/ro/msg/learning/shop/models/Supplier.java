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
public class Supplier extends BaseEntity<Integer>{
   private String name;
}
