package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    //스프링이 엔티티 매니저를 만들어서 주입 해주는 코드
//    @PersistenceContext // 부트를 사용하지 않으면 @PersistenceContext를 사용해야 하지만, 부트는 Autowired만 사용해도 인젝션 가능하게 함.
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).
                getResultList();
    }


    public List<Member> findByName(String name) {
        //JPQL : 엔티티를 조회하는 문법 m은 Alias
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }


}
