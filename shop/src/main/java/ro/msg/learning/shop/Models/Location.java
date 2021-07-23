package ro.msg.learning.shop.Models;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Location extends BaseEntity<Integer>{
    private String Name;
    @ManyToOne
    private Address Address;

}
