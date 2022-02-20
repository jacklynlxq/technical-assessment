package com.example.demo.domain.repository;

import com.example.demo.domain.entitiy.Feature;

public interface FeatureRepository {
    Feature findByFeatureName(String name);
}
