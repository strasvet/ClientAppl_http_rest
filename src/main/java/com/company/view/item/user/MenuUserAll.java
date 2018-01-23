package com.company.view.item.user;

import com.company.core.PostMan2;
import com.company.dto.LoginRequest;
import com.company.entities.User;
import com.company.model.Select;
import com.company.model.Settings;
import com.company.view.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;

public class MenuUserAll extends Item {
    public MenuUserAll(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "User INFO";
    }

    @Override
    public void perform() {

        if(System.getProperty("SESSION_ID")!=null) {
            //*** add items
            ArrayList<Item> res = new ArrayList<>();
            res.add(new GetAllUsers(inputOutput));
            res.add(new GetUser(inputOutput));
            res.add(new UpdateUser(inputOutput));
            //res.add(new LogOut(inputOutput));
            res.add(new ReturnItem(inputOutput));

            //*** run items
            Menu menu = new Menu(res, inputOutput);
            menu.runMenu();
        }

    }
}
