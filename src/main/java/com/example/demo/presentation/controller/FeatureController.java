package com.example.demo.presentation.controller;

import com.example.demo.domain.dto.GetAccessRequest;
import com.example.demo.domain.dto.GetAccessResponse;
import com.example.demo.domain.dto.PostAccessRequest;
import com.example.demo.domain.entitiy.Access;
import com.example.demo.service.AccessManagementServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Api("Feature Controller")
@Controller
@RequestMapping("/api/v1/feature")
public class FeatureController {
    private static final Logger logger = Logger.getLogger(FeatureController.class);

    @Autowired
    AccessManagementServiceImpl accessManagementService;

    /**
     * @param featureName feature name
     * @param email user email address
     * @return Response (if found) along with HTTP Status Code
     */
    @ApiOperation(
            value = "Get User Feature Access",
            notes="Receives email (user’s email) and featureName as request parameters and returns the example response in JSON format. "
    )
    @GetMapping
    @ResponseBody
    public ResponseEntity<GetAccessResponse> getUserFeatureAccess(@RequestParam String featureName, @RequestParam String email){
        logger.info("User email: " + email);
        logger.info("Feature name: " + featureName);

        GetAccessRequest getAccessRequest = new GetAccessRequest(email, featureName);
        GetAccessResponse response;

        try{
            // check access of user for feature
            Boolean access = accessManagementService.checkUserFeatureAccess(getAccessRequest).getUserFeatureAccess();
            logger.info("Access: " + access);

            response = new GetAccessResponse(access);
        }
        catch (EntityNotFoundException ex){
            logger.info("Feature Name or Email not found");
            logger.info(ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (NullPointerException ex){
            logger.info("Can't find record of feature access: " + featureName + " for this user email: " + email + ", please add access for the user.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * @param postAccessRequest Consists of email (user email), featureName (feature name) and enable (boolean of access)
     * @return Empty Response along with HTTP Status Code
     */
    @ApiOperation(
            value = "Post User Feature Access",
            notes = "Receives the example request body in JSON format and returns an empty response with HTTP Status OK (200) when the database is updated successfully, otherwise returns Http Status Not Modified (304)."
    )
    @PostMapping
    @ResponseBody
    public ResponseEntity<Void> postUserFeatureAccess(@RequestBody PostAccessRequest postAccessRequest){

        try{
            // add access of user
            Access access = accessManagementService.addUserFeatureAccess(postAccessRequest);
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
