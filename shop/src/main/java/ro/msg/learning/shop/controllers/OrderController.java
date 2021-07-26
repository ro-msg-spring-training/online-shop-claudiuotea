package ro.msg.learning.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.models.Orders;
import ro.msg.learning.shop.services.interfaces.IOrderService;

@Controller
@AllArgsConstructor
public class OrderController {
    @Autowired
    private final IOrderService orderService;

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
