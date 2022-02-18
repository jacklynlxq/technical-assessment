package com.example.demo.domain.service;

import com.example.demo.domain.dto.AccessRequest;
import com.example.demo.domain.entitiy.Access;

public interface AccessManagementService {
    Access checkUserFeatureAccess(AccessRequest accessRequest);
}
