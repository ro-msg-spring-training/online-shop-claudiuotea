package ro.msg.learning.shop.Models;

import lombok.*;

import javax.persistence.Id;

@javax.persistence.Entity
@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity<Integer> {
    private String FirstName;
    private String LastName;
    private String Username;
    private String Password;
    private String EmailAddress;

}
