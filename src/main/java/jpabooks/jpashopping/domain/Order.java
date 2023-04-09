package jpabooks.jpashopping.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter@Setter
//회원,배송 정보,주문상태
public class Order {
    @Id
    @GeneratedValue
    @Column(name="order_Id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;    //주문회원@Id

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<>(); //one에 many list 생성

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //배송정보
    private LocalDateTime orderDate; //주문시간
    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]
    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }


    //method 생성//
    public static Order createOrder(Member member,Delivery delivery,OrderItem ...orderItems){
        Order order=new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem:orderItems){
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.Order);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }
    //비즈니스 로직
    public void cancle(){
        if(delivery.getStatus() ==DeliveryStatus.COMP){
            throw new IllegalStateException("배송중 물품 주문취소 불가");
        }
        this.setStatus(OrderStatus.Cancle);
        for(OrderItem orderItem: orderItems){
            orderItem.cancel();
        }
    }
    //조회
    //전체 가격
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
        totalPrice+=orderItem.getTotalPrice();
        }
        return totalPrice;
    }
}

