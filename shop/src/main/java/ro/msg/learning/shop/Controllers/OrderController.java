package ro.msg.learning.shop.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.msg.learning.shop.DTOs.OrderDTO;
import ro.msg.learning.shop.Models.Orders;
import ro.msg.learning.shop.Services.Interfaces.IOrderService;

@Controller
public class OrderController {
    @Autowired
    IOrderService orderService;

    @PostMapping(value = "/orders")
    public ResponseEntity<Orders> addProduct(@RequestBody OrderDTO orderDTO){
        try{
            Orders order = orderService.createOrder(orderDTO);
            return new ResponseEntity<>(order,HttpStatus.OK);
        }
        catch(RuntimeException ex){
            return new ResponseEntity<>(null,HttpStatus.CONFLICT);
        }
    }
}
