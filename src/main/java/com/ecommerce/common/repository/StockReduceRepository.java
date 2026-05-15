package com.ecommerce.common.repository;

import com.ecommerce.common.entity.OrderEntity;
import com.ecommerce.common.entity.StockReduceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockReduceRepository extends JpaRepository<StockReduceEntity, Long> {

    List<StockReduceEntity> findByOrder(OrderEntity order);
}