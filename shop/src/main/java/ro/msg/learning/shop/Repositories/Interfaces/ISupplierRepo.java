package ro.msg.learning.shop.Repositories.Interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.Models.Supplier;

public interface ISupplierRepo extends CrudRepository<Supplier,Integer> {
}
