package ro.msg.learning.shop.services.interfaces;


import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.models.Orders;

public interface IOrderService {
    Orders createOrder(OrderDTO order);
}
