package ro.msg.learning.shop.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AddProductDTO {
    Integer CategoryId;
    String Name;
    String Description;
    BigDecimal Price;
    Integer SupplierId;
    Double Weight;
    String ImageUrl;
}
