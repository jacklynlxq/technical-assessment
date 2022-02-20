package com.example.demo.domain.service;

import com.example.demo.domain.dto.GetAccessRequest;
import com.example.demo.domain.dto.UpdateAccessRequest;
import com.example.demo.domain.dto.UpdateAccessResponse;
import com.example.demo.domain.entitiy.Access;
import com.example.demo.infrastructure.entity.AccessJpaEntity;

public interface AccessManagementService {
    Access checkUserFeatureAccess(GetAccessRequest getAccessRequest);

    Access updateUserFeatureAccess(UpdateAccessRequest updateAccessRequest);
}
