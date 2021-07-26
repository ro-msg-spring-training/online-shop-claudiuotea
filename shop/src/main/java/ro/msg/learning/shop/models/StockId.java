package ro.msg.learning.shop.models;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class StockId implements Serializable {
    Integer product;
    Integer location;
}
