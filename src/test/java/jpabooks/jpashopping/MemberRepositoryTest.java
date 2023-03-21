package jpabooks.jpashopping;


import jpabooks.jpashopping.domain.Member;
import jpabooks.jpashopping.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() {
        Member member = new Member();
        Member member1=new Member();
        member.setUsername("memberA");
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);

        assertThat(findMember.getId()).isEqualTo(member.getId());
    }
}