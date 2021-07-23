package ro.msg.learning.shop.Models;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OrderTable extends BaseEntity<Integer>{
    @ManyToOne
    private Location ShippedFrom;
    @ManyToOne
    private Customer Customer;
    private LocalDateTime CreatedAt;
    @ManyToOne
    private Address Adresss;

}
