package ro.msg.learning.shop.Models;

import lombok.*;

import javax.persistence.Id;

@javax.persistence.Entity
@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity<Integer> {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;

}
