package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member userA = new Member("userA", 10);
        //when
        Member savedMember = memberRepository.save(userA);
        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember.getId()).isEqualTo(savedMember.getId());
    }

    @Test
    void findAll() {
        //given
        Member userA = new Member("userA", 10);
        Member userB = new Member("userB", 20);

        Member savedMember1 = memberRepository.save(userA);
        Member savedMember2 = memberRepository.save(userB);

        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(savedMember1, savedMember2);
    }
}
