package ro.msg.learning.shop.Models;


import lombok.*;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Revenue extends BaseEntity<Integer>{
    @ManyToOne
    private Location Location;
    LocalDate Date;
    BigDecimal Sum;
}
