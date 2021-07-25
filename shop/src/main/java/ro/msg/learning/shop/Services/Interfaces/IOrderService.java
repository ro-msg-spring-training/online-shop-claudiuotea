package ro.msg.learning.shop.Services.Interfaces;


import ro.msg.learning.shop.DTOs.OrderDTO;
import ro.msg.learning.shop.Models.Orders;

public interface IOrderService {
    Orders createOrder(OrderDTO order);
}
