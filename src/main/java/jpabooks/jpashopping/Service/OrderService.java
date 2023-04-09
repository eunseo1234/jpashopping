package jpabooks.jpashopping.Service;

import jpabooks.jpashopping.Repository.ItemRepository;
import jpabooks.jpashopping.Repository.MemberRepository;
import jpabooks.jpashopping.Repository.OrderRepository;
import jpabooks.jpashopping.domain.*;
import jpabooks.jpashopping.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    //주문
    @Transactional
    public Long order(Long memberId,Long itemId,int count){
        //엔티티 조회
        Member member=memberRepository.findOne(memberId);
        Item item=itemRepository.findOne(itemId);

        //배송 정보( 주소만)
        Delivery delivery=new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        //주문상품(orderitem) 생성
        OrderItem orderItem=OrderItem.createOrderItem(item,item.getPrice(),count);
        //주문 생성
        Order order=Order.createOrder(member,delivery,orderItem);

        //주문 저장
        orderRepository.save(order);
        return order.getId();

    }
    //취소
    @Transactional
    public void cancelItem(Long orderId){
        Order order=orderRepository.findOne(orderId);
        order.cancle();
    }


}
