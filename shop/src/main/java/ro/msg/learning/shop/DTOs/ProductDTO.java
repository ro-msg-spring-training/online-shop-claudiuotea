package ro.msg.learning.shop.DTOs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class ProductDTO {
    Integer Id;
    Integer CategoryId;
    String Name;
    String Description;
    String CategoryName;
    String CategoryDescription;
    BigDecimal Price;
    Double Weight;
    String ImageUrl;
}
