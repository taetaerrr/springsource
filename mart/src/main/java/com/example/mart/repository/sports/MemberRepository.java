package com.example.mart.repository.sports;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entity.item.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    void save(com.example.mart.entity.sports.Member member);

}
