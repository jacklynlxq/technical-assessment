package com.example.demo.service;

import com.example.demo.domain.dto.GetAccessRequest;
import com.example.demo.domain.dto.PostAccessRequest;
import com.example.demo.domain.entitiy.Access;
import com.example.demo.domain.service.AccessManagementService;
import com.example.demo.infrastructure.entity.AccessJpaEntity;
import com.example.demo.infrastructure.entity.FeatureJpaEntity;
import com.example.demo.infrastructure.entity.UserJpaEntity;
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
    UserJpaRepository userJpaRepository;

    @Autowired
    FeatureJpaRepository featureJpaRepository;

    @Autowired
    AccessJpaRepository accessJpaRepository;


    @Override
    public Access checkUserFeatureAccess(GetAccessRequest getAccessRequest) {

        // get userId from userEmail
        UserJpaEntity user = getUserByEmail(getAccessRequest.getUserEmail());

        // get featureID from featureName
        FeatureJpaEntity feature = getFeatureByFeatureName(getAccessRequest.getFeatureName());

        return accessJpaRepository.findByUserIdAndFeatureId(user.getId(), feature.getId());
    }

    @Override
    public Access addUserFeatureAccess(PostAccessRequest postAccessRequest){
        // create User object
        UserJpaEntity user = getUserByEmail(postAccessRequest.getEmail());

        // create Feature object
        FeatureJpaEntity feature = getFeatureByFeatureName(postAccessRequest.getFeatureName());

        // create new AccessJPAEntity
        AccessJpaEntity access = new AccessJpaEntity(user, feature, postAccessRequest.getEnable());

        return accessJpaRepository.save(access);
    }

    private UserJpaEntity getUserByEmail(String userEmail){

        // get user from userEmail
        UserJpaEntity user = userJpaRepository.findByUserEmail(userEmail);

        if(user == null){
            throw new EntityNotFoundException("Can't find user with email: " + userEmail);
        }

        logger.info("User id: " + user.getId());

        return user;
    }

    private FeatureJpaEntity getFeatureByFeatureName(String featureName){

        // get feature from featureName
        FeatureJpaEntity feature = featureJpaRepository.findByFeatureName(featureName);

        if(feature == null){
            throw new EntityNotFoundException("Can't find feature with name: " + featureName);
        }

        logger.info("Feature id: " + feature.getId());

        return feature;
    }
}
