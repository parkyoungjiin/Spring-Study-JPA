package jpabook.jpashop.api;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.SimpleOrderQueryDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderQueryDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderQueryDto> result = orders.stream()
                .map(o -> new SimpleOrderQueryDto(o)) // order -> SimpleOrderDto
                .collect(Collectors.toList());// list

        return result;

    }


    /**
     *  V3: n+1 문제 해결을 위해 페치 조인 적용
     *
     */
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderQueryDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderQueryDto> result = orders.stream()
                .map(order -> new SimpleOrderQueryDto(order))
                .collect(Collectors.toList());

        return result;
    }

    /**
     * V4: 엔티티 조회가 아닌 DTO로 바로 조회
     */
    @GetMapping("/api/v4/simple-orders")
    public List<SimpleOrderQueryDto> ordersV4() {
        return orderRepository.findOrderDtos();
    }



}
