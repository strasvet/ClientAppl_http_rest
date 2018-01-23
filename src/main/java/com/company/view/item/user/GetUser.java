package com.company.view.item.user;

import com.company.core.PostMan2;
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

public class GetUser extends Item {
    public GetUser(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Get user";
    }

    @Override
    public void perform() {


        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<User> entity = new HttpEntity<User>(null, headers);
        String id =inputOutput.getInteger("Please, enter Id of user: ").toString();
        Settings.url.put("userId", id);
        User user = (User) PostMan2.getPostman(Settings.url.get("user"), HttpMethod.GET,entity,Select.user);
        if(user!=null) {
            inputOutput.put((String.format("[ %s, %s %s ]",user.getEmail(), user.getFirstName(), user.getLastName())));
        }else{
            inputOutput.put("This id: \""+id+ "\" - is not found on server");
        }

        //List<User> users = (List<User>) PostMan2.getPostman(Settings.url.get("user"), HttpMethod.GET,entity,Select.user);
        //users.forEach(x->inputOutput.put(String.format("[ %s, %s %s ]",x.getEmail(),x.getFirstName(),x.getLastName())));

    }
}
