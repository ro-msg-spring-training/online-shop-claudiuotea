package ro.msg.learning.shop.Services.Impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.DTOs.OrderDTO;
import ro.msg.learning.shop.Models.Address;
import ro.msg.learning.shop.Models.OrderDetail;
import ro.msg.learning.shop.Models.Orders;
import ro.msg.learning.shop.Models.Stock;
import ro.msg.learning.shop.Repositories.Interfaces.IAddressRepo;
import ro.msg.learning.shop.Repositories.Interfaces.IOrderDetailRepo;
import ro.msg.learning.shop.Repositories.Interfaces.IOrderRepo;
import ro.msg.learning.shop.Repositories.Interfaces.IStockRepo;
import ro.msg.learning.shop.Services.Interfaces.IOrderService;
import ro.msg.learning.shop.Strategies.Interfaces.ILocationStrategy;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    @Qualifier("abundantStrategy")
    ILocationStrategy locationStrategy;
    IOrderRepo orderRepo;
    IAddressRepo addressRepo;
    IOrderDetailRepo orderDetailRepo;
    IStockRepo stockRepo;

    @Override
    public Orders createOrder(OrderDTO order) {
        //use the strategy to get Stock objects (containing product,quantity,location)
        List<Stock> locations = locationStrategy.getLocations(order.getProducts());

        //check if the address exists, otherwise create it and save it
        Address address = addressRepo.findByCityAndCountryAndCountyAndStreetAddress(order.getCity(),order.getCountry(),order.getCounty(),order.getStreetAddress());
        if(address == null)
            address = addressRepo.save(Address.builder().streetAddress(order.getStreetAddress())
            .city(order.getCity())
            .country(order.getCountry())
            .county(order.getCounty())
            .build());

        //create the order
        Orders newOrder = orderRepo.save(Orders.builder()
                .shippedFrom(locations.get(0).getLocation())
                .createdAt(order.getTime().toLocalDateTime())
                .address(address)
                .customer(null)
                .build());

        //create order details
        locations.forEach(stock->{
            OrderDetail newOrderDetail = OrderDetail.builder()
                    .orders(newOrder)
                    .product(stock.getProduct())
                    .quantity(stock.getQuantity())
                    .build();
            orderDetailRepo.save(newOrderDetail);

            //now update the stocks quantity
            Stock toUpdate = stockRepo.findByProductAndLocation(stock.getProduct(),stock.getLocation());
            toUpdate.setQuantity(toUpdate.getQuantity()-stock.getQuantity());
            stockRepo.save(toUpdate);
        });
        return newOrder;
    }
}
