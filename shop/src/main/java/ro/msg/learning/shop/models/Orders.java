package ro.msg.learning.shop.models;

import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Orders extends BaseEntity<Integer>{
    @ManyToOne
    @JoinColumn(name= "shipped_from")
    private Location shippedFrom;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

}
