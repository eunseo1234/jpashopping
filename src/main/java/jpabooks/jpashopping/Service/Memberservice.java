package jpabooks.jpashopping.Service;

import jakarta.persistence.EntityManager;
import jpabooks.jpashopping.Repository.MemberRepository;
import jpabooks.jpashopping.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor  //lombok final만 생성자 생성해줌
public class Memberservice {

//    @Autowired
//    MemberRepository memberRepository; //필드주입 (추천X)



//    private final MemberRepository memberRepository;
//    public Memberservice(MemberRepository memberRepository){
//        this.memberRepository=memberRepository;
//    } //생성자 주입

    private final MemberRepository memberRepository;  //lombok 어노테이션 사용


    //회원가입

    @Transactional
    public Long join(Member member){
        validateDateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }


//중복회원 검증
    private void validateDateDuplicateMember(Member member) {
        List<Member> findMembers =
                memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");


        }
    }

//회원조회
        public List<Member> findMembers() {
            return memberRepository.findAll();
        }
        public Member findOne(Long memberId) {
            return memberRepository.findOne(memberId);
        }
}
