package com.company.view.item.user;

import com.company.core.PostMan2;
import com.company.entities.NewUser;
import com.company.entities.User;
import com.company.model.Select;
import com.company.model.Settings;
import com.company.view.InputOutput;
import com.company.view.Item;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class GetAllUsers extends Item {
    public GetAllUsers(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Get all users";
    }

    @Override
    public void perform() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<User> entity = new HttpEntity<>(null,headers);
        List<User> users = (List<User>) PostMan2.getPostman(Settings.url.get("users"), HttpMethod.GET,entity,Select.users);
        users.forEach(x->inputOutput.put(String.format("[ userId:%s, %s, %s %s ]",x.getId(),x.getEmail(),x.getFirstName(),x.getLastName())));

    }
}
