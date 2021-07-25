package ro.msg.learning.shop.Models;
import lombok.*;
import javax.persistence.Id;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Address extends BaseEntity<Integer>{
    private String country;
    private String city;
    private String county;
    private String streetAddress;

}
