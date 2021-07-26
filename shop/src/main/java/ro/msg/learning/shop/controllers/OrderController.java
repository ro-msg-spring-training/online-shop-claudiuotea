package ro.msg.learning.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.models.Orders;
import ro.msg.learning.shop.models.Users;
import ro.msg.learning.shop.services.impl.EmailServiceImpl;
import ro.msg.learning.shop.services.interfaces.IOrderService;

import javax.mail.MessagingException;

@Controller
@AllArgsConstructor
public class OrderController {
    private final IOrderService orderService;
    private final EmailServiceImpl emailService;

    @PostMapping(value = "/orders")
    public ResponseEntity<Orders> addProduct(@RequestBody OrderDTO orderDTO, Authentication authentication){
        try{
            Orders order = orderService.createOrder(orderDTO);

            //get the current user to find out the email
            Users currentUser = (Users) authentication.getPrincipal();
            emailService.sendPlainMessage(currentUser.getEmail(), "Thanks for the order", "Thank you!");
            emailService.sendHtmlMessage(currentUser.getEmail(), "Thanks for the order", "Thank you!");
            return new ResponseEntity<>(order,HttpStatus.OK);
        }
        catch(RuntimeException | MessagingException ex){
            return new ResponseEntity<>(null,HttpStatus.CONFLICT);
        }
    }
}
