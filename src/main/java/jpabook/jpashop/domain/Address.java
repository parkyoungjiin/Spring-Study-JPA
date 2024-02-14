package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable //어딘가 내장될 수 있음을 선언.
@Getter
//setter를 넣지 않은 이유 : 주소의 경우는 값 타입으로 변경 불가능하도록 설계 해야 하기 때문.
public class Address {
    private String city;
    private String street;
    private String zipcode;

    //임베디드 타입은 기본 생성자가 필수.
    protected Address() {

    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
