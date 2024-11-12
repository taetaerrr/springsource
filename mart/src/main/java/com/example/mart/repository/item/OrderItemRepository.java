package com.example.mart.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mart.entity.item.OrderItem;
import com.example.mart.repository.QueryDslOrderRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
