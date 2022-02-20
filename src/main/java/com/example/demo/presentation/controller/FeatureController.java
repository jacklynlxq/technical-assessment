package com.example.demo.presentation.controller;

import com.example.demo.domain.dto.GetAccessRequest;
import com.example.demo.domain.dto.GetAccessResponse;
import com.example.demo.domain.dto.UpdateAccessRequest;
import com.example.demo.service.AccessManagementServiceImpl;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;


@Controller
@RequestMapping("/api/v1/feature")
public class FeatureController {
    private static final Logger logger = Logger.getLogger(FeatureController.class);

    @GetMapping
    @ResponseBody
    public GetAccessResponse getUserFeatureAccess(@RequestParam String featureName, @RequestParam String email){
        logger.info("User email: " + email);
        logger.info("Feature name: " + featureName);
        // TODO: add exception for only 1 request
        // TODO: add error message if null
        // TODO: add validation for email

        GetAccessRequest getAccessRequest = new GetAccessRequest(email, featureName);

        Boolean access = false;
        if(accessManagementService.checkUserFeatureAccess(getAccessRequest) == null){
            throw new EntityNotFoundException(
                    "Can't find record of feature access: " + featureName + " for this user email: " + email + ", please add access for the user."
            );
        }
        else{
            access = accessManagementService.checkUserFeatureAccess(getAccessRequest).getUserFeatureAccess();
        }

        logger.info("Access: " + access);

        return new GetAccessResponse(access);
    }

    @Autowired
    AccessManagementServiceImpl accessManagementService;

    @PostMapping
    @ResponseBody
    public ResponseEntity postUserFeatureAccess(@RequestBody UpdateAccessRequest updateAccessRequest){
        // TODO: pass UpdateAccessRequest to service to repository to add into database

        accessManagementService.updateUserFeatureAccess(updateAccessRequest);
        //TODO: if not updated, return 304 not modified

        return ResponseEntity.ok(HttpStatus.OK);
    }


//    @GetMapping("/")
//    @ResponseBody
//    public AccessResponse getUserFeatureAccess(AccessRequest accessRequest){
//        accessRequest.setUserEmail("afs"); // TODO: delete
//        boolean canAccess = this.accessService.checkUserFeatureAccess(accessRequest);
//
//        AccessResponse accessResponse = new AccessResponse();
//        // TODO: service: checkUserFeatureAccess
//        accessResponse.setCanAccess(canAccess);
//
//        return accessResponse;
//    }


}
