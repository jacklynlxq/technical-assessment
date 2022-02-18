package com.example.demo.service;

import com.example.demo.domain.dto.AccessRequest;
import com.example.demo.domain.entitiy.Access;
import com.example.demo.domain.entitiy.Feature;
import com.example.demo.domain.entitiy.User;
import com.example.demo.domain.service.AccessManagementService;
import com.example.demo.infrastructure.repository.AccessJpaRepository;
import com.example.demo.infrastructure.repository.FeatureJpaRepository;
import com.example.demo.infrastructure.repository.UserJpaRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AccessManagementServiceImpl implements AccessManagementService {
    private static final Logger logger = Logger.getLogger(AccessManagementServiceImpl.class);

    @Autowired
    UserJpaRepository userJpaRepository; // inject UserRepository implementation

    @Autowired
    FeatureJpaRepository featureJpaRepository; // inject FeatureRepository implementation

    @Autowired
    AccessJpaRepository accessJpaRepository; // inject AccessRepository implementation


    @Override
    public Access checkUserFeatureAccess(AccessRequest accessRequest) {
        // get featureID from featureName
        Feature feature = featureJpaRepository.findByFeatureName(accessRequest.getFeatureName());
        if(feature == null){
            throw new EntityNotFoundException("can't find feature with name: " + accessRequest.getFeatureName());
        }
        logger.info("Feature id: " + feature.getId());

        // get userId from userEmail
        User user = userJpaRepository.findByUserEmail(accessRequest.getUserEmail());
        if(user == null){
            throw new EntityNotFoundException("can't find user with email: " + accessRequest.getUserEmail());
        }
        logger.info("User id: " + user.getId());

        return accessJpaRepository.findByUserIdAndFeatureId(user.getId(), feature.getId());
    }

}
