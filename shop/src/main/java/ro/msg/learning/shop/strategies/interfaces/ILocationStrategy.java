package ro.msg.learning.shop.strategies.interfaces;

import ro.msg.learning.shop.models.Stock;
import java.util.List;
import java.util.Map;

public interface ILocationStrategy{
    List<Stock> getLocations(Map<Integer,Integer> products);
}
