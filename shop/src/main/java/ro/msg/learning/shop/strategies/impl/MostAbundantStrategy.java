package ro.msg.learning.shop.strategies.impl;

import lombok.AllArgsConstructor;
import ro.msg.learning.shop.models.Stock;
import ro.msg.learning.shop.Repositories.interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.interfaces.IStockRepo;
import ro.msg.learning.shop.strategies.interfaces.ILocationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class MostAbundantStrategy implements ILocationStrategy {
    private final IStockRepo stockRepo;
    private final IProductRepo productRepo;

    @Override
    public List<Stock> getLocations(Map<Integer,Integer> products) {
        ArrayList<Stock> locationProducts = new ArrayList<>();
       products.forEach((prodId,quantity)->{
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
