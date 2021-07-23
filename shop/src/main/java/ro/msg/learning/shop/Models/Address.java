package ro.msg.learning.shop.Models;
import lombok.*;
import javax.persistence.Id;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Address extends BaseEntity<Integer>{
    private String Country;
    private String City;
    private String County;
    private String StreetAddress;

}
