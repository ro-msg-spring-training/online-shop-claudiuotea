package ro.msg.learning.shop.DTOs;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProductDTO {
    //TODO: nu returneaza id-urile, doar NULL
    Integer id;
    Integer CategoryId;
    String Name;
    String Description;
    String CategoryName;
    String CategoryDescription;
    BigDecimal Price;
    Double Weight;
    String ImageUrl;
}
