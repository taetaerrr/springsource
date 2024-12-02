package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // email은 unique 라서 optional 사용 List(x)
    Optional<Member> findByEmail(String email);

    // 닉네임 수정
    @Modifying // update, delete 시 필수
    @Query("UPDATE Member m set m.nickname=:nickname where m.email=:email")
    void updateNickName(String nickname, String email);

}
