package com.example.demo.domain.service;

import com.example.demo.domain.dto.GetAccessRequest;
import com.example.demo.domain.dto.PostAccessRequest;
import com.example.demo.domain.entitiy.Access;

public interface AccessManagementService {
    Access checkUserFeatureAccess(GetAccessRequest getAccessRequest);

    Access addUserFeatureAccess(PostAccessRequest postAccessRequest);
}
