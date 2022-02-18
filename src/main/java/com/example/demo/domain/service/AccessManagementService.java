package com.example.demo.domain.service;

import com.example.demo.presentation.dto.AccessRequest;
import com.example.demo.domain.entitiy.Access;

public interface AccessManagementService {
    Access checkUserFeatureAccess(AccessRequest accessRequest);
}
