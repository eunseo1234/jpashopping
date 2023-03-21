package jpabooks.jpashopping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabooks.jpashopping.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    //save
    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }
    public Member find(Long id){
        return em.find(Member.class,id);
    }
}
