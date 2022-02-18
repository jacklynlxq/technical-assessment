package com.example.demo.infrastructure.repository;

import com.example.demo.domain.repository.AccessRepository;
import com.example.demo.infrastructure.entity.AccessJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessJpaRepository extends AccessRepository, CrudRepository<AccessJpaEntity, Long> {
    AccessJpaEntity findByUserIdAndFeatureId(long user_id, long feature_id);
}
