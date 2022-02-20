package com.example.demo.domain.repository;

import com.example.demo.domain.entitiy.Access;
import com.example.demo.domain.entitiy.Feature;
import com.example.demo.domain.entitiy.User;

public interface AccessRepository {
    Access findByUserIdAndFeatureId(long user_id, long feature_id);
    Access save(Access access);
}
