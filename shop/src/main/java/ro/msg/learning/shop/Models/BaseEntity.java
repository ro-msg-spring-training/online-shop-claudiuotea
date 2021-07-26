package ro.msg.learning.shop.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity <ID>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;
}
