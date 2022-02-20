package com.example.demo.service;

import com.example.demo.domain.dto.GetAccessRequest;
import com.example.demo.domain.dto.UpdateAccessRequest;
import com.example.demo.domain.entitiy.Access;
import com.example.demo.domain.entitiy.Feature;
import com.example.demo.domain.entitiy.User;
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
import java.util.Optional;

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
    public Access checkUserFeatureAccess(GetAccessRequest getAccessRequest) {
        // get featureID from featureName
//        Feature feature = featureJpaRepository.findByFeatureName(getAccessRequest.getFeatureName());
//        if(feature == null){
//            throw new EntityNotFoundException("Can't find feature with name: " + getAccessRequest.getFeatureName());
//        }
//        logger.info("Feature id: " + feature.getId());
        Feature feature = getFeatureByFeatureName(getAccessRequest.getFeatureName());


        // get userId from userEmail
//        User user = userJpaRepository.findByUserEmail(getAccessRequest.getUserEmail());
//        if(user == null){
//            throw new EntityNotFoundException("Can't find user with email: " + getAccessRequest.getUserEmail());
//        }
//        logger.info("User id: " + user.getId());

//        long userId = getUserIdByEmail(getAccessRequest.getUserEmail());
        User user = getUserByEmail(getAccessRequest.getFeatureName());

        return accessJpaRepository.findByUserIdAndFeatureId(user.getId(), feature.getId());
//        return accessJpaRepository.findByUserIdAndFeatureId(userId, featureId);
    }

    @Override
    public Access updateUserFeatureAccess(UpdateAccessRequest updateAccessRequest){
        // create User object
        UserJpaEntity user = getUserByEmail(updateAccessRequest.getEmail());

        // create Feature object
        FeatureJpaEntity feature = getFeatureByFeatureName(updateAccessRequest.getFeatureName());

        // create new AccessJPAEntity
        AccessJpaEntity access = new AccessJpaEntity(user, feature, updateAccessRequest.getEnable());

        // call save() from AccessJpaRepository
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
