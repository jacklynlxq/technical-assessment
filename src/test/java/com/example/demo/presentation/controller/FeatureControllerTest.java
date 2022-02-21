package com.example.demo.presentation.controller;

import com.example.demo.domain.dto.GetAccessRequest;
import com.example.demo.domain.dto.PostAccessRequest;
import com.example.demo.domain.entitiy.Access;
import com.example.demo.domain.service.AccessManagementService;
import com.example.demo.infrastructure.entity.AccessJpaEntity;
import com.example.demo.infrastructure.entity.FeatureJpaEntity;
import com.example.demo.infrastructure.entity.UserJpaEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FeatureControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AccessManagementService accessManagementService;


    @Test
    void getUserFeatureAccessReturnHttpStatusOk() throws Exception {

        OngoingStubbing<Access> accessOngoingStubbing = when(accessManagementService.checkUserFeatureAccess(getAccessRequest())).thenReturn(expectedGetAccess());
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/feature")
                                                .param("featureName", "Feature A")
                                                .param("email", "user1@mail.com"))
                                                .andExpect(status().isOk())
                                                .andReturn();
        String stringResponse = mvcResult.getResponse().getContentAsString();
        Assert.isTrue(stringResponse.equals("{\"canAccess\":true}"), "Expected Response: {\"canAccess\":true}; Got Response: " + stringResponse);
    }

    private GetAccessRequest getAccessRequest(){
        return new GetAccessRequest("user1@mail.com", "Feature A");
    }

    private Access expectedGetAccess(){
        UserJpaEntity user = new UserJpaEntity("user1@mail.com");
        FeatureJpaEntity feature = new FeatureJpaEntity("Feature A");
        return new AccessJpaEntity(user, feature, true);
    }

    @Test
    void postUserFeatureAccessReturnHttpStatusOk() throws Exception {
        OngoingStubbing<Access> accessOngoingStubbing = when(accessManagementService.addUserFeatureAccess(postAccessRequest())).thenReturn(expectedPostAccess());

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestBodyJson = ow.writeValueAsString(postAccessRequest());

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/feature")
                        .contentType(APPLICATION_JSON)
                        .content(requestBodyJson))
                        .andExpect(status().isOk())
                        .andReturn();
    }

    private PostAccessRequest postAccessRequest(){
        return new PostAccessRequest("user1@mail.com", "feature A", true);
    }

    private Access expectedPostAccess(){
        UserJpaEntity user = new UserJpaEntity("user1@mail.com");
        FeatureJpaEntity feature = new FeatureJpaEntity("Feature A");
        return new AccessJpaEntity(user, feature, true);
    }
}
