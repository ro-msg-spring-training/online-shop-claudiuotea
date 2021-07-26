package ro.msg.learning.shop.Strategies.Impl;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.DTOs.OrderDTO;
import ro.msg.learning.shop.Models.Location;
import ro.msg.learning.shop.Models.Stock;
import ro.msg.learning.shop.Repositories.Interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.Interfaces.IStockRepo;
import ro.msg.learning.shop.Strategies.Interfaces.ILocationStrategy;

import java.util.*;

@AllArgsConstructor
public class MostAbundantStrategy implements ILocationStrategy {
    IStockRepo stockRepo;
    IProductRepo productRepo;

    @Override
    public List<Stock> getLocations(Map<Integer,Integer> products) {
        ArrayList<Stock> locationProducts = new ArrayList<>();
       products.forEach((prodId,quantity)->{
            //TODO:add throws here if doesn't exist
            Stock biggestStock = stockRepo.findFirstByProductOrderByQuantityDesc(productRepo.findById(prodId).get());
            if(biggestStock == null || biggestStock.getQuantity() < quantity)
                throw new RuntimeException("Cannot find a location for product with id " + prodId);
            locationProducts.add(Stock.builder()
                    .quantity(quantity)
                    .product(biggestStock.getProduct())
                    .location(biggestStock.getLocation())
                    .build());

        });
       return locationProducts;
    }
}
