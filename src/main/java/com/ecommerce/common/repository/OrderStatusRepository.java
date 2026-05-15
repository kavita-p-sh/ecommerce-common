package com.ecommerce.common.repository;


import com.ecommerce.common.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Long> {
    Optional<OrderStatusEntity> findByStatusName(String statusName);
}
