package ro.msg.learning.shop.Repositories.interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.models.Users;

public interface IUserRepo extends CrudRepository<Users,Integer> {
    Users findByUsername(String username);
}
