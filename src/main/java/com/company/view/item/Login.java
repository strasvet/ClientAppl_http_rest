package com.company.view.item;

import com.company.core.PostMan2;
import com.company.dto.LoginRequest;
import com.company.entities.User;
import com.company.model.Select;
import com.company.model.Settings;
import com.company.view.InputOutput;
import com.company.view.Item;
import com.company.view.Menu;
import com.company.view.item.items.MenuItemAll;
import com.company.view.item.user.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;

public class Login extends Item {
    public Login(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Login";
    }

    @Override
    public void perform() {
        LoginRequest loginRequest = new LoginRequest();
        //tel-tan@site.com
        loginRequest.setEmail(inputOutput.getString("Please,enter e-mail:"));
        //password1
        loginRequest.setPassword(inputOutput.getString("Please, enter password:"));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.set("Authorization", null);
        HttpEntity<LoginRequest> entity = new HttpEntity<LoginRequest>(loginRequest, headers);
        //ResponseEntity<RegistrationResponse> response =(ResponseEntity<RegistrationResponse>) PostMan2.getPostman(Settings.url.get("login"),HttpMethod.POST, entity, Select.other);
        //User user = response.getBody().getUser();
        User user =(User) PostMan2.getPostman(Settings.url.get("login"),HttpMethod.POST, entity, Select.other);
        if(user!=null) {
            inputOutput.put("You login as: " + user.getEmail() + "\n" + "Your SESSION_ID: " + System.getProperty("SESSION_ID")+"\n");
        }
        if(System.getProperty("SESSION_ID")!=null) {
            //*** add items
            ArrayList<Item> res = new ArrayList<>();
            res.add(new MenuUserAll(inputOutput));
            res.add(new MenuItemAll(inputOutput));
            res.add(new LogOut(inputOutput));
            //res.add(new GetAllUsers(inputOutput));
            //res.add(new GetUser(inputOutput));
            //res.add(new UpdateUser(inputOutput));
            //res.add(new LogOut(inputOutput));
            //res.add(new ExitItem(inputOutput));

            //*** run items
            Menu menu = new Menu(res, inputOutput);
            menu.runMenu();
        }

    }
}
