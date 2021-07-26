package ro.msg.learning.shop.models;

import lombok.*;

import javax.persistence.*;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
@Builder
@EqualsAndHashCode
@IdClass(StockId.class)
public class Stock{
    @Id
    @ManyToOne
    @JoinColumn(name="product")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;
    private int quantity;

}
