package ro.msg.learning.shop.strategies.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.models.Location;
import ro.msg.learning.shop.models.Product;
import ro.msg.learning.shop.models.Stock;
import ro.msg.learning.shop.Repositories.interfaces.ILocationRepo;
import ro.msg.learning.shop.Repositories.interfaces.IProductRepo;
import ro.msg.learning.shop.Repositories.interfaces.IStockRepo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SingleLocationStrategyTest {
    @InjectMocks
    private SingleLocationStrategy strategy;
    @Mock
    private IStockRepo stockRepo;
    @Mock
    private IProductRepo productRepo;
    @Mock
    ILocationRepo locationRepo;

    private Product prod1;
    private Product prod2;
    private Location loc1;
    private Location loc2;
    private Stock stock1;
    private Stock stock2;
    private Stock stock3;

    @BeforeEach
    public void init(){
        prod1 = Product.builder()
                .name("first prod")
                .weight(2.0)
                .supplier(null)
                .price(BigDecimal.valueOf(1.1))
                .imageUrl("first prod")
                .description("first prod")
                .productCategory(null)
                .build();
        prod2 = Product.builder()
                .name("second prod")
                .weight(2.0)
                .supplier(null)
                .price(BigDecimal.valueOf(1.1))
                .imageUrl("second prod")
                .description("second prod")
                .productCategory(null)
                .build();
        loc1 = Location.builder()
                .stocks(null)
                .address(null)
                .name("First loc")
                .build();
        loc2 = Location.builder()
                .stocks(null)
                .address(null)
                .name("Second loc")
                .build();
        loc1.setId(1);
        loc2.setId(2);
        stock1 = Stock.builder()
                .quantity(10)
                .location(loc1)
                .product(prod1)
                .build();
        stock2 = Stock.builder()
                .product(prod2)
                .location(loc2)
                .quantity(15)
                .build();
        stock3 = Stock.builder()
                .product(prod1)
                .location(loc2)
                .quantity(15)
                .build();

    }

    @Test
    void getLocationsWorking() {
        Mockito.when(productRepo.findById(1)).thenReturn(java.util.Optional.ofNullable(prod1));
        Mockito.when(productRepo.findById(2)).thenReturn(java.util.Optional.ofNullable(prod2));
        Mockito.when(stockRepo.findStocksByProductAndQuantityGreaterThanEqual(prod1,5))
                .thenReturn(List.of(stock1,stock3));
        Mockito.when(stockRepo.findStocksByProductAndQuantityGreaterThanEqual(prod2,3))
                .thenReturn(List.of(stock2));
        Mockito.when(locationRepo.findById(2)).thenReturn(java.util.Optional.ofNullable(loc2));

        HashMap<Integer,Integer> prods = new HashMap<>();
        prods.put(1,5);
        prods.put(2,3);

        List<Stock> locations = strategy.getLocations(prods);
        Assertions.assertEquals(locations.get(0).getLocation(),loc2);
        Assertions.assertEquals(locations.get(0).getLocation(),locations.get(1).getLocation());
        Assertions.assertEquals(locations.get(0).getProduct(),prod1);
        Assertions.assertEquals(locations.get(1).getProduct(),prod2);

    }

    @Test
    void getLocationsFailDueToMissingStock() {
        Mockito.when(productRepo.findById(1)).thenReturn(java.util.Optional.ofNullable(prod1));
        Mockito.when(productRepo.findById(2)).thenReturn(java.util.Optional.ofNullable(prod2));
        Mockito.when(stockRepo.findStocksByProductAndQuantityGreaterThanEqual(prod1,5))
                .thenReturn(List.of(stock1,stock3));
        Mockito.when(stockRepo.findStocksByProductAndQuantityGreaterThanEqual(prod2,3))
                .thenReturn(List.of(stock2));
        Mockito.when(locationRepo.findById(2)).thenReturn(java.util.Optional.ofNullable(loc2));

        HashMap<Integer,Integer> prods = new HashMap<>();
        prods.put(1,55);
        prods.put(2,3);


        try {
            List<Stock> locations = strategy.getLocations(prods);
            assert false;
        }
        catch(RuntimeException ex){
            assert true;
        }
    }
}