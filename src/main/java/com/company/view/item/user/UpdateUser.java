package com.company.view.item.user;

import com.company.core.PostMan2;
import com.company.dto.UpdateRequest;
import com.company.entities.User;
import com.company.model.Select;
import com.company.model.Settings;
import com.company.view.InputOutput;
import com.company.view.Item;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class UpdateUser extends Item {
    public UpdateUser(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Update user";
    }

    @Override
    public void perform() {
        UpdateRequest userR = new UpdateRequest();

        String id =inputOutput.getInteger("Please, enter Id of user for update: ").toString();
        Settings.url.put("userId", id);
        //if(id.equalsIgnoreCase("0"))  new GetAllUsers(inputOutput);
        userR.setFirstName(inputOutput.getString("Enter new first name"));
        userR.setLastName(inputOutput.getString("Enter new last name for update"));
        userR.setEmail(inputOutput.getString("Enter e-mail for update"));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<UpdateRequest> entity = new HttpEntity<UpdateRequest>(userR, headers);

        User user = (User) PostMan2.getPostman(Settings.url.get("user"), HttpMethod.PUT,entity,Select.user);
        if(user!=null) {
           inputOutput.put((String.format("| User was changed to: %s, %s %s |",user.getEmail(), user.getFirstName(), user.getLastName())));
           // inputOutput.put(user);
        }else{
            inputOutput.put("This id: \""+id+ "\" - is not found on server");
        }

        //List<User> users = (List<User>) PostMan2.getPostman(Settings.url.get("user"), HttpMethod.GET,entity,Select.user);
        //users.forEach(x->inputOutput.put(String.format("[ %s, %s %s ]",x.getEmail(),x.getFirstName(),x.getLastName())));

    }
}
