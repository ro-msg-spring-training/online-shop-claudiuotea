package ro.msg.learning.shop.Strategies.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.Models.Location;
import ro.msg.learning.shop.Models.Product;
import ro.msg.learning.shop.Models.Stock;
import ro.msg.learning.shop.Repositories.Interfaces.ILocationRepo;
import ro.msg.learning.shop.Repositories.Interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.Interfaces.IStockRepo;
import ro.msg.learning.shop.Strategies.Interfaces.ILocationStrategy;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SingleLocationStrategy implements ILocationStrategy {
    private final ILocationRepo locationRepo;
    private final IStockRepo stockRepo;
    private final IProductRepo productRepo;

    @Override
    public List<Stock> getLocations(Map<Integer,Integer> products) {
        //a frequency vector, mapping each location how many products has
        HashMap<Integer,Integer> locationProducts = new HashMap<>();
        //iterate through products and find all the stocks with the needed quantity
        products.forEach((prodId,quantity)->{
            //TODO: add throw here and refactor
            Product searchProduct = productRepo.findById(prodId).get();
            //find all the stocks for this product
            stockRepo.findStocksByProductAndQuantityGreaterThanEqual(searchProduct,quantity).forEach(stock->{
                //for each stock increment the frequency for that location
                int key = stock.getLocation().getId(), value = 1;
                if(locationProducts.containsKey(key)){
                    //contains the key so just increment the value
                    value = locationProducts.get(key);
                    locationProducts.put(key,value + 1);
                }
                else
                    locationProducts.put(key,value);
            });
        });
        //now check if any location has all the products
        int needed = products.size();
        Optional<Map.Entry<Integer,Integer>> location = locationProducts.entrySet()
                .stream()
                .filter(map->map.getValue()==needed)
                .findFirst();

        if(location.isEmpty())
            throw new RuntimeException("There is no location which contains all the products!");

        //get the location
        Location foundProducts = locationRepo.findById(location.get().getKey()).get();
        //now get a list of stocks and return it
        return products.entrySet()
                .stream()
                .map(map-> Stock.builder()
                        .location(foundProducts)
                        .product(productRepo.findById(map.getKey()).get())
                        .quantity(map.getValue())
                        .build())
                .collect(Collectors.toList());

    }
}
