package ro.msg.learning.shop.Repositories.interfaces;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.models.Supplier;

public interface ISupplierRepo extends CrudRepository<Supplier,Integer> {
}
