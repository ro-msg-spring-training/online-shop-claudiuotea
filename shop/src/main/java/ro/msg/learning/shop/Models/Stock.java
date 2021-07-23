package ro.msg.learning.shop.Models;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Stock extends BaseEntity<Integer>{
    @ManyToOne
    @MapsId("product_id")
    private Product Product;
    @ManyToOne
    @MapsId("location_id")
    private Location Location;
    private int Quantity;

}
