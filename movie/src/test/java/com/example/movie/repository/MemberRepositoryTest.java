package com.example.movie.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.example.movie.entity.Member;
import com.example.movie.entity.constant.MemberRole;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 50).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@naver.com")
                    .password(passwordEncoder.encode("1111"))
                    .nickname("nickname" + i)
                    .role(MemberRole.MEMBER)
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    public void testUpdate() {
        Member member = memberRepository.findById(2L).get();
        member.setNickname("수정이");
        memberRepository.save(member);

    }

    @Transactional
    @Test
    public void testUpdateQuery() {
        memberRepository.updateNickName("수정잉잉", "user3@naver.com");
    }

}
