package com.company.view.item.items;

import com.company.view.*;
import com.company.view.item.user.GetAllUsers;
import com.company.view.item.user.GetUser;
import com.company.view.item.user.UpdateUser;

import java.util.ArrayList;

public class MenuItemAll extends Item {
    public MenuItemAll(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Item management";
    }

    @Override
    public void perform() {

        if(System.getProperty("SESSION_ID")!=null) {
            //*** add items
            ArrayList<Item> res = new ArrayList<>();
            res.add(new GetAllItems(inputOutput));
            res.add(new GetItem(inputOutput));
            res.add(new ChangeToDone(inputOutput));
            res.add(new CreateItem(inputOutput));
            res.add(new DeleteItem(inputOutput));
            //res.add(new GetAllUsers(inputOutput));
            //res.add(new GetUser(inputOutput));
            //res.add(new UpdateUser(inputOutput));
            //res.add(new LogOut(inputOutput));
            res.add(new ReturnItem(inputOutput));

            //*** run items
            Menu menu = new Menu(res, inputOutput);
            menu.runMenu();
        }

    }
}
