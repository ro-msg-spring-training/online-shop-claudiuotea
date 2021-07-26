package ro.msg.learning.shop.models;

import lombok.*;

import javax.persistence.*;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@IdClass(OrderDetailId.class)
@Builder
public class OrderDetail{
    @Id
    @ManyToOne
    @JoinColumn(name = "orders")
    private Orders orders;

    @Id
    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    int quantity;
}
