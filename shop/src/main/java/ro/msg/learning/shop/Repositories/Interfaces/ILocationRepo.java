package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.Location;

public interface ILocationRepo extends CrudRepository<Location,Integer> {
}
