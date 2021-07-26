package ro.msg.learning.shop.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.models.Address;
import ro.msg.learning.shop.models.OrderDetail;
import ro.msg.learning.shop.models.Orders;
import ro.msg.learning.shop.models.Stock;
import ro.msg.learning.shop.Repositories.interfaces.IAddressRepo;
import ro.msg.learning.shop.Repositories.interfaces.IOrderDetailRepo;
import ro.msg.learning.shop.Repositories.interfaces.IOrderRepo;
import ro.msg.learning.shop.Repositories.interfaces.IStockRepo;
import ro.msg.learning.shop.services.interfaces.IOrderService;
import ro.msg.learning.shop.strategies.interfaces.ILocationStrategy;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {
    @Qualifier("abundantStrategy")
    private final ILocationStrategy locationStrategy;
    private final IOrderRepo orderRepo;
    private final IAddressRepo addressRepo;
    private final IOrderDetailRepo orderDetailRepo;
    private final IStockRepo stockRepo;

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
