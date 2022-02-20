package com.example.demo.presentation.controller;

import com.example.demo.domain.dto.GetAccessRequest;
import com.example.demo.domain.dto.GetAccessResponse;
import com.example.demo.domain.dto.UpdateAccessRequest;
import com.example.demo.domain.entitiy.Access;
import com.example.demo.service.AccessManagementServiceImpl;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public ResponseEntity<Void> postUserFeatureAccess(@RequestBody UpdateAccessRequest updateAccessRequest){

        try{
            Access access = accessManagementService.updateUserFeatureAccess(updateAccessRequest);
            logger.info("Access added:" + access);
        }
        catch (DataIntegrityViolationException ex){
            logger.info("Violation of Unique Keys: unit_id & feature_id in Access table (Duplicated record)");
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (EntityNotFoundException ex){
            logger.info("Feature Name or Email not found");
            logger.info(ex);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        catch (Exception ex){
            logger.info(ex);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
