package com.example.demo.presentation.controller;

import com.example.demo.presentation.dto.AccessRequest;
import com.example.demo.presentation.dto.AccessResponse;
import com.example.demo.service.AccessManagementServiceImpl;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api/v1/feature")
public class FeatureController {
    private static final Logger logger = Logger.getLogger(FeatureController.class);

    @GetMapping
    @ResponseBody
    public AccessResponse getUserFeatureAccess(@RequestParam String featureName, @RequestParam String email){
        logger.info("User email: " + email);
        logger.info("Feature name: " + featureName);
        // TODO: add exception for only 1 request
        // TODO: add error message if null
        // TODO: add validation for email

        AccessRequest accessRequest = new AccessRequest(email, featureName);

        Boolean access = false;
        if(accessManagementService.checkUserFeatureAccess(accessRequest) == null){ access = false; }
        else{ access = true;  }

        logger.info("Access: " + access);

        return new AccessResponse(access);
    }

    @Autowired
    AccessManagementServiceImpl accessManagementService;

//    @Autowired
//    FeatureServiceImpl featureService;


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
