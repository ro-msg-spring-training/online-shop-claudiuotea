package ro.msg.learning.shop.Models;

import lombok.Getter;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity <ID>{
    @Id
    @GeneratedValue
    private ID id;
}
