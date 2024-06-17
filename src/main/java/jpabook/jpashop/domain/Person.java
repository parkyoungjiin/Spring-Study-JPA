package jpabook.jpashop.domain;

import lombok.Builder;
import lombok.ToString;

@Builder(toBuilder = true)
@ToString
public class Person {
    private String name;
    private int age;

    public static void main(String[] args) {
        // 원래 객체 생성
        Person person = Person.builder()
                .name("John")
                .age(30)
                .build();

        System.out.println("Original: " + person);

        // toBuilder를 이용한 객체 복사 및 일부 필드 변경
        Person newPerson = person.toBuilder()
                .age(35)
                .build();

        System.out.println("Modified: " + newPerson);
    }
}
