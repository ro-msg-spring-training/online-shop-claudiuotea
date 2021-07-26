package ro.msg.learning.shop.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity <ID>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;
}
