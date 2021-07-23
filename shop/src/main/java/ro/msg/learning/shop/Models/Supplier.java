package ro.msg.learning.shop.Models;

import lombok.*;

import javax.persistence.Id;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Supplier extends BaseEntity<Integer>{
   private String Name;
}
