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
public class StockId implements Serializable {
    @Column(name = "product_id")
    private int productId;
    @Column(name = "location_id")
    private int locationId;
}
