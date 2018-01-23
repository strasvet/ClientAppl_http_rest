package com.company.model;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Settings {
    public static volatile Map<String,String> url = new HashMap<>();
    // properties to map
    //public void Settings(){ getProperties(); }
    public static void getProperties() {
        Properties properties = new Properties();
        InputStream instrem = Settings.class.getResourceAsStream("/application.properties");
        try {
            properties.load(instrem);
            //System.out.println(properties.getProperty("str"));
           // System.out.println(properties.entrySet());
           // System.out.println("Properties string names:\n"+properties.stringPropertyNames());
            for (String str:properties.stringPropertyNames()) {
                url.put(str, properties.getProperty(str));
                //System.out.println(str);
            }
        } catch (IOException e) {
           // e.printStackTrace();
            System.out.println("File \"application.properties\" is not found!");
        }

    }

}
