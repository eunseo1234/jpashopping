package jpabooks.jpashopping.Service;

import jpabooks.jpashopping.Repository.MemberRepository;
import jpabooks.jpashopping.domain.Member;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;




//1.회원가입 성공해야함
//2.회원가입시 중복(이름) 예외 발생해야함
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class MemberserviceTest {
    @Autowired
    Memberservice memberservice;
    @Autowired
    MemberRepository memberRepository;  //생성자 필드 주입
    @Test       //회원가입
    public void join()throws Exception{
        //given
        Member member=new Member();
        member.setName("lee");
        //when
        Long savedId=memberservice.join(member);
        //then
        assertEquals(member,memberRepository.findOne(savedId));

    }
//validateTest
//    @Test(expected=IllegalStateException.class)
//    public void validateMember()throws Exception{
//        //given
//        Member member1=new Member();
//        member1.setName("eun");
//        Member member2=new Member();
//        member2.setName("eun");
//
//        //when
//        memberservice.join(member1);
//        memberservice.join(member2);
//
//        //then
//        fail("예외발생");
//
//    }
//

}