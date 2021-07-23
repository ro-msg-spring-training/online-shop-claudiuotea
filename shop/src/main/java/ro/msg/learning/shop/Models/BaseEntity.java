package ro.msg.learning.shop.Models;

import lombok.Getter;

import javax.persistence.*;
import javax.persistence.Entity;

@MappedSuperclass
public abstract class BaseEntity <ID>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;
}
