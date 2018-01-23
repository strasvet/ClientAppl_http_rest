package com.company.core;

import com.company.dto.ErrorResponse;
import com.company.dto.ItemResponse;
import com.company.dto.RegistrationResponse;
import com.company.entities.User;
import com.company.model.Settings;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PostMan2 {
    public static Object getPostman(String url, Enum type, HttpEntity<?> entity, Enum select) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            switch (select.toString()) {
                case "user":
                    ResponseEntity<User> res = restTemplate.exchange(url, (HttpMethod) type, entity, User.class, Settings.url);
                    return res.getBody();
                case "users":
                    ResponseEntity<User[]> resp = restTemplate.exchange(url, (HttpMethod) type, entity, User[].class, Settings.url);
                    return Arrays.asList(resp.getBody());
                case "other":
                    ResponseEntity<RegistrationResponse> response = restTemplate.exchange(url, (HttpMethod) type, entity, RegistrationResponse.class, Settings.url);
                    RegistrationResponse registrationResponse = response.getBody();
                    if (registrationResponse.getSessionId() != null)
                        System.setProperty("SESSION_ID", registrationResponse.getSessionId());
                    //System.out.println("Your first name: "+registrationResponse.getUser().getFirstName());
                    return registrationResponse.getUser();
                case "string":
                    ResponseEntity<String> stringRes = restTemplate.exchange(url, (HttpMethod) type, entity, String.class,Settings.url);
                    return stringRes;
                case "item":
                    ResponseEntity<ItemResponse> responseEntity = restTemplate.exchange(url, (HttpMethod) type, entity,ItemResponse.class,Settings.url);
                    return responseEntity.getBody();
                case "items":
                    ResponseEntity<ItemResponse[]> responseEntitys = restTemplate.exchange(url, (HttpMethod) type, entity,ItemResponse[].class,Settings.url);
                    return Arrays.asList(responseEntitys.getBody());
            }
            //ResponseEntity<User> response = restTemplate.exchange(url, (HttpMethod) type, entity, User.class, map);
            //        ResponseEntity<RegistrationResponse> response = restTemplate.exchange(url, (HttpMethod) type, entity, RegistrationResponse.class, Settings.url);
            //        RegistrationResponse registrationResponse = response.getBody();
            //        if(registrationResponse.getSessionId()!=null)System.setProperty("SESSION_ID", registrationResponse.getSessionId());
            //System.setProperty(response.getBody().getUser().getId(),response.getBody().getSessionId());

            //        System.out.println("Body of Response :\n" + response.getBody());
        } catch (HttpClientErrorException e) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, List<String>> errors = null;
            try {
                if ((errors = mapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class).getErrorResponse()) != null)
                    for (List<String> list : errors.values()) {
                        System.err.println(list.toString().replace("[", "").replace("]", ""));
                        //    //    //System.err.println("Filed!!! Alert!!!");
                    }
                else {
                    //System.out.println(e.getResponseBodyAsString());
                    System.err.println(/*"Get message: \n"+*/
                            mapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class).getMessage());
                }
            } catch (IOException e1) {/*not a.. e1.printStackTrace();*/}

        }
        return null;
    }


}

