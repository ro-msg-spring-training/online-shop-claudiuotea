package ro.msg.learning.shop.Models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OrderDetailId implements Serializable{
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "product_id")
    private int productId;

}
