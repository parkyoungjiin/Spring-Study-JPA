package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // 검색 기능은 추후에 추가할 예정.
    public List<Order> findAll(OrderSearch orderSearch) {
        String jpql = "select o from Order o Join o.member m" +
                " where o.status = :status" +
                " and m.name like :name";
        em.createQuery(jpql, Order.class)
                .setParameter("status",orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList();

    }
}
