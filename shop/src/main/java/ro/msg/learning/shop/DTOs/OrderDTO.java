package ro.msg.learning.shop.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.HashMap;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    Timestamp time;
    String city;
    String country;
    String county;
    String streetAddress;
    HashMap<Integer,Integer> products;
}
