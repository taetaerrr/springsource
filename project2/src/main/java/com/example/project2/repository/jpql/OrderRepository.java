package com.example.project2.repository.jpql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project2.entity.jpql.Address;
import com.example.project2.entity.jpql.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o.address from order o")
    List<Address> findByAddress();

    @Query("select o.member, o.product,o.orderAmount from order o")
    List<Address> findByOrders();
}
