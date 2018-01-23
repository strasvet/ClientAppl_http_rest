package com.company.view.item;

import com.company.core.PostMan2;
import com.company.entities.NewUser;
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

public class Registration extends Item {
    public Registration(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Registration as new user";
    }

    @Override
    public void perform() {
        //*** get New User + Headers + entity
        NewUser user = new NewUser();
        user.setFirstName(inputOutput.getString("Please, enter first name: "));
        user.setLastName(inputOutput.getString("Please, enter last name: "));
        user.setEmail(inputOutput.getString("Please, enter your e-mail: "));
        user.setPassword(inputOutput.getString("Please, enter your password: "));
        //Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("Authorization", null);
        //entity
        //HttpEntity<NewUser> entity = new HttpEntity<NewUser>(user,headers);
        HttpEntity<NewUser> entity = new HttpEntity<NewUser>(user,null);

        User userRes =(User) PostMan2.getPostman(Settings.url.get("register"),HttpMethod.POST, entity, Select.other);
        if(userRes!=null) {
            inputOutput.put("You registered in system as: " + "\n" + userRes + "\n" + "Your SESSION_ID: " + System.getProperty("SESSION_ID")+"\n");
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
