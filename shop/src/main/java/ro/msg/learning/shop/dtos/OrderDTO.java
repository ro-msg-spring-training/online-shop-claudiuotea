package ro.msg.learning.shop.dtos;

import lombok.*;

import java.sql.Timestamp;
import java.util.HashMap;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {
    Timestamp time;
    String city;
    String country;
    String county;
    String streetAddress;
    HashMap<Integer,Integer> products;
}
