package ro.msg.learning.shop.Models;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OrderDetail extends BaseEntity<OrderDetailId>{
    @ManyToOne
    @MapsId("order_id")
    private OrderTable OrderObj;
    @ManyToOne
    @MapsId("product_id")
    private Product Product;

}
