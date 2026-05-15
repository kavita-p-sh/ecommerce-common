package com.ecommerce.common.repository;



import com.ecommerce.common.entity.RoleEntity;
import com.ecommerce.common.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(RoleName roleName);
}