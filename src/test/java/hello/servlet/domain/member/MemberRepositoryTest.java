package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member29 = new Member("승기",20);
        //when
        Member savedMember29 = memberRepository.save(member29);

        //then
        Member findMember = memberRepository.findById(savedMember29.getId());
        assertThat(findMember).isEqualTo(savedMember29);

    }

    @Test
    void 전체검사(){
        //given
        Member member30 = new Member("선기",30);
        Member member31 = new Member("뱅룩",31);

        memberRepository.save(member30);
        memberRepository.save(member31);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member30,member31);
        //이건 내가 만든거
        assertThat(result.get(1).getUsername()).isEqualTo("뱅룩");

    }
}