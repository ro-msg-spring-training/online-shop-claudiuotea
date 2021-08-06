package ro.msg.learning.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.models.Orders;
import ro.msg.learning.shop.models.Users;
import ro.msg.learning.shop.services.impl.EmailServiceImpl;
import ro.msg.learning.shop.services.interfaces.IOrderService;

@RestController
@AllArgsConstructor
public class OrderController {
    private final IOrderService orderService;
    private final EmailServiceImpl emailService;

    @PostMapping(value = "/orders")
    public Orders addOrder(@RequestBody OrderDTO orderDTO, Authentication authentication) {
        Orders order = orderService.createOrder(orderDTO);
        //get the current user to find out the email
        Users currentUser = (Users) authentication.getPrincipal();
        emailService.sendPlainMessage(currentUser,orderDTO);
        emailService.sendHtmlMessage(currentUser,orderDTO);
        return order;
    }
}
