package ro.msg.learning.shop.DTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class UpdateProductDTO {
    String Description;
    BigDecimal Price;
    Integer Supplier;
    Double Weight;
    String Imageurl;
}
