package com.company.core;

import com.company.dto.ErrorResponse;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostMan {
    public static void getPostman(String url, Enum type, HttpEntity<?> entity, Map<String,String> map){
        /* Obernut v tray catch
        if(url = user){
            ResponseEntity<User> response = restTemplate.exchange(url,HttpMethod.GET,entity, User.class, Settings.url);
            User user = response.getBody();
            System.out.println(user); }
            */
        if(map==null) map = new HashMap<String,String>();
        RestTemplate restTemplate = new RestTemplate();
        try {
            //switch/case/ map.get("settings")
            ResponseEntity<RegistrationResponse> response = restTemplate.exchange(url, (HttpMethod) type, entity, RegistrationResponse.class, map);
            RegistrationResponse registrationResponse = response.getBody();
            if(registrationResponse.getSessionId()!=null)System.setProperty("SESSION_ID", registrationResponse.getSessionId());
            //System.setProperty(response.getBody().getUser().getId(),response.getBody().getSessionId());

            System.out.println("Body of Response :\n" + response.getBody());
        }catch(HttpClientErrorException e){
            ObjectMapper mapper = new ObjectMapper();
            Map<String,List<String>> errors = null;
            try {
                //System.out.println( e.getResponseBodyAsString());
               //errors = mapper.readValue(e.getResponseBodyAsString(),ErrorResponse.class).getErrorResponse();
                if((errors=mapper.readValue(e.getResponseBodyAsString(),ErrorResponse.class).getErrorResponse())!=null)
                    for (List<String> list : errors.values()) {
                        System.err.println(list.toString().replace("[", "").replace("]", ""));
                        System.err.println("Filed!!! Alert!!!");
                    }else{
                    //System.out.println(e.getResponseBodyAsString());
                    System.out.println("Get message: \n"
                            +mapper.readValue(e.getResponseBodyAsString(),ErrorResponse.class).getMessage());
                }
            }

            catch (IOException e1) {/*not a.. e1.printStackTrace();*/}

            }
        }

    }

