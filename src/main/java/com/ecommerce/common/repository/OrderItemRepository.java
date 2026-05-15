package com.ecommerce.common.repository;


import com.ecommerce.common.entity.OrderEntity;
import com.ecommerce.common.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    List<OrderItemEntity> findByOrder(OrderEntity order);

    List<OrderItemEntity> findByOrderIn(List<OrderEntity> orders);
}