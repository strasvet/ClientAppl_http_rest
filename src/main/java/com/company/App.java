package com.company;

import com.company.model.Settings;
import com.company.view.*;
import com.company.view.item.Login;
import com.company.view.item.Registration;

import java.util.ArrayList;

public class App {
    static InputOutput inputOutput= new ConsoleInputOutput();

    public static void main(String[] args) {
        Settings.getProperties();
        getMenu();
    }

        private static void getMenu(){
        //*** add items
        ArrayList<Item> res = new ArrayList<>();
        res.add(new Login(inputOutput));
        res.add(new Registration(inputOutput));
        //res.add(new LogOut(inputOutput));
        //res.add(new GetAllUsers(inputOutput));
        //res.add(new GetUser(inputOutput));
        //res.add(new UpdateUser(inputOutput));
        res.add(new ExitItem(inputOutput));

        //*** run items
        Menu menu = new Menu(res, inputOutput);
        menu.runMenu();

    }
}
