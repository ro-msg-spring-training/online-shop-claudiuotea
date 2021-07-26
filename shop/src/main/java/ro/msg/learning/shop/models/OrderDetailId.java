package ro.msg.learning.shop.models;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class OrderDetailId implements Serializable {
    int orders;
    int product;
}
