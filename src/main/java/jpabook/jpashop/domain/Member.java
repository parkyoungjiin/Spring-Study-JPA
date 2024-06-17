package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;
    @JsonIgnore
    //"order 테이블에 있는 member 필드에 의해 매핑이 된 것이다" 를 명시한 것.
    @OneToMany(mappedBy = "member") // 연관관계 주인이 아닐 때 mappedBy를 설정.
    private List<Order> orders = new ArrayList<>();



}
