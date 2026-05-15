package com.ecommerce.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_orders")
@Data
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "order_id", length = 36)
    private UUID orderId;

    @Column(name = "user_id", nullable = false, length = 36)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID userId;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    @ToString.Exclude
    private OrderStatusEntity status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<OrderItemEntity> orderItems;
}