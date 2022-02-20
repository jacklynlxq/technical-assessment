package com.example.demo.domain.repository;

import com.example.demo.domain.entitiy.Access;

public interface AccessRepository {
    Access findByUserIdAndFeatureId(long user_id, long feature_id);
    Access save(Access access);
}
