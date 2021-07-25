package ro.msg.learning.shop.Strategies.Interfaces;

import ro.msg.learning.shop.Models.Stock;
import java.util.List;
import java.util.Map;

public interface ILocationStrategy{
    List<Stock> getLocations(Map<Integer,Integer> products);
}
