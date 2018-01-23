package com.company.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleInputOutput implements InputOutput {
    Scanner scanner = new Scanner(System.in);
    //BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    @Override
    public String getString(String prompt) {
        //System.out.println(prompt);
        put(prompt);
        return scanner.nextLine();
        //return scanner.readLine();
    }

    @Override
    public void put(Object object) {
       // System.out.println(object);
        System.out.println(object);
    }

}

