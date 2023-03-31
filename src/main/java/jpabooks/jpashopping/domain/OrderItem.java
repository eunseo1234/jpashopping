package jpabooks.jpashopping.domain;

import jakarta.persistence.*;
import jpabooks.jpashopping.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Table(name="order_item")
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;      //주문상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order; //주문

    private int orderprice;
    private int count;
}
