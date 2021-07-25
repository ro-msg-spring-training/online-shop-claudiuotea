package ro.msg.learning.shop.Models;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class StockId implements Serializable {
    Integer product;
    Integer location;
}
