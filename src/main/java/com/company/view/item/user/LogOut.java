package com.company.view.item.user;

import com.company.core.PostMan2;
import com.company.dto.LoginRequest;
import com.company.dto.RegistrationResponse;
import com.company.entities.User;
import com.company.model.Select;
import com.company.model.Settings;
import com.company.view.InputOutput;
import com.company.view.Item;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LogOut extends Item {
    public LogOut(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "LogOut";
    }

    @Override
    public void perform() {
        //LoginRequest loginRequest = new LoginRequest();
        //tel-tan@site.com
        //loginRequest.setEmail(inputOutput.getString("Please,enter e-mail:"));
        //password1
        //loginRequest.setPassword(inputOutput.getString("Please, enter password:"));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity entity = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();

        //ResponseEntity<String> res = restTemplate.exchange(Settings.url.get("logout"),HttpMethod.POST,entity,String.class);
        String res = (String) PostMan2.getPostman(Settings.url.get("logout"),HttpMethod.POST,entity,Select.string).toString();
        if(System.getProperty("SESSION_ID")!=null) System.clearProperty("SESSION_ID");
        inputOutput.put("Your logout status: "+res+"\n");

    }
    @Override
    public boolean isExit(){
        return true;
    }
}
