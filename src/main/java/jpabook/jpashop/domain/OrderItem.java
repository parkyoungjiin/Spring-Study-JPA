package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
// protected 생성자를 사용함으로써 new로 생성하지 않도록 함.
// 생성 메서드를 사용하도록 통일하는 것임.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 당시 가격
    private int count; // 주문 수량



    //==생성 메서드==//
    //static으로 메서드를 해야하는 이유
    // -> OrderItem을 생성하지 않더라도 해당 메서드를 실행 해야 하기 때문임.
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }


    //==비즈니스 로직==//
    //주문 취소 (주문 개수 만큼 재고를 다시 담아야 함)
    public void cancel() {
        // JPA -> 재고 변경 시 더티 체킹으로 update 쿼리가 자동으로 날라감.
        getItem().addStock(count);
    }

    //==조회 로직==/
    // 주문상품 전체 가격 조회
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
