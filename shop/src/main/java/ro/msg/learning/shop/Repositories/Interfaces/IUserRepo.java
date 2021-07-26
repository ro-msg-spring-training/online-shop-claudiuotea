package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.Users;

public interface IUserRepo extends CrudRepository<Users,Integer> {
    Users findByUsername(String username);
}
