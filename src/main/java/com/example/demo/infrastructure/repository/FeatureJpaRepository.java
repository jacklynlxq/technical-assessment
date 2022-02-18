package com.example.demo.infrastructure.repository;

import com.example.demo.domain.repository.FeatureRepository;
import com.example.demo.infrastructure.entity.FeatureJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatureJpaRepository extends FeatureRepository, CrudRepository<FeatureJpaEntity, Long> {
    FeatureJpaEntity findByFeatureName(String name);
}
