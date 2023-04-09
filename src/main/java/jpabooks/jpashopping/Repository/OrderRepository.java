package jpabooks.jpashopping.Repository;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jpabooks.jpashopping.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }
    public Order findOne(Long id){
        return em.find(Order.class,id);
    }
}
