package ro.msg.learning.shop.Repositories.interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.models.Location;

public interface ILocationRepo extends CrudRepository<Location,Integer> {
}
