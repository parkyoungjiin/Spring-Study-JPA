package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    @PersistenceContext // EntityManager를 빈으로 등록할 때 사용하는 어노테이션.
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        // 저장 후 member를 반환하지 않고 memberId를 반환한다.
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
