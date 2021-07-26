package ro.msg.learning.shop.models;


import lombok.*;

import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Revenue extends BaseEntity<Integer>{
    @ManyToOne
    private Location location;
    LocalDate date;
    BigDecimal sum;
}
