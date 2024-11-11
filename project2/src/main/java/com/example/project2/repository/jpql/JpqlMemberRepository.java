package com.example.project2.repository.jpql;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.project2.entity.jpql.JpqlMember;
import com.example.project2.entity.jpql.Team;

public interface JpqlMemberRepository extends JpaRepository<JpqlMember, Long>, QuerydslPredicateExecutor<JpqlMember> {

    // findAll() => @Query 로 직접 구현
    @Query("SELECT m FROM JpqlMember m")
    List<JpqlMember> findMembers(Sort sort);

    // select 다음에 오는 구문이 2개 이상인 경우 Object[] 임
    @Query("SELECT m.name,m.age FROM JpqlMember m")
    List<Object[]> findByOrders();

    // 쿼리 메소드
    // 특정 이름을 가진 회원 조회
    @Query("SELECT m.jpqlMember where user_name from JpqlMember m")
    List<JpqlMember> findByName(String name);

    // 특정 나이보다 많은 회원 조회
    @Query("SELECT m.jpqlMember where age from JpqlMember m")
    List<JpqlMember> findByAgeGreateThan(int age);

    // 특정 멤버의팀 조회
    @Query("SELECT m.jpqlMember where team_id from JpqlMember m")
    List<JpqlMember> findByTeam(Team team);

    // 나이 합계, 나이 평균, 연장자, 최연소, 인원수
    @Query("SELECT count(m), sum(m.age),avg(m.age),max(m.age),min(m.age) from Jpqlember m")
    List<Object[]> aggregate();

    // 내부 조인인 경우 on 절 생략
    @Query("SELECT m from JpqlMember m join m.team t where m.team =?1")
    List<JpqlMember> findByTeam2(String teamName);

    @Query("SELECT m,t from jpqlMember m  join m.team t where m.team =?1")
    List<Object[]> findByTeam3(String teamName);

    @Query(value = "SELECT m,t from jpqlMember m  join m.team t where m.team =?1")
    List<Object[]> findByTeam4(String teamName);
}
