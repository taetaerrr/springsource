package com.example.mart.repository.sports;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entity.cascade.Child;

public interface ChildRepository extends JpaRepository<Child, Long> {

}
