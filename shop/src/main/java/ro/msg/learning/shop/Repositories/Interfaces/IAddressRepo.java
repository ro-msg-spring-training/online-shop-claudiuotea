package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.Address;

public interface IAddressRepo extends CrudRepository<Address,Integer> {
    Address findByCityAndCountryAndCountyAndStreetAddress(String city,String country,String county,String streetAddress);
}
