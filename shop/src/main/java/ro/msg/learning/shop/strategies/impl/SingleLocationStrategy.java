package ro.msg.learning.shop.strategies.impl;

import lombok.AllArgsConstructor;
import ro.msg.learning.shop.models.Location;
import ro.msg.learning.shop.models.Product;
import ro.msg.learning.shop.models.Stock;
import ro.msg.learning.shop.Repositories.interfaces.ILocationRepo;
import ro.msg.learning.shop.Repositories.interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.interfaces.IStockRepo;
import ro.msg.learning.shop.strategies.interfaces.ILocationStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
