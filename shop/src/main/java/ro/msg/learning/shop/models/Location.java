package ro.msg.learning.shop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@javax.persistence.Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Location extends BaseEntity<Integer>{
    private String name;

    @ManyToOne
    @JoinColumn(name = "address")
    private Address address;

    //infinite recursion
    @JsonIgnore
    @OneToMany(mappedBy = "location")
    List<Stock> stocks;
}
