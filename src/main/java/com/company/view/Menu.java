package com.company.view;

import java.util.ArrayList;

public class Menu {
    ArrayList<Item> items;
    InputOutput inputOutput;
    public Menu(ArrayList<Item> items, InputOutput inputOutput) {
        this.items = items;
        this.inputOutput = inputOutput;
    }
    public void runMenu(){
        int size = items.size();
        while(true){
            for(int i = 1; i<=size; i++){
                inputOutput.put(i+". "+items.get(i-1).displayedName());
            }
            int itemNumber;
            String errMsg = "";
            do{
                itemNumber = inputOutput.getInteger(errMsg+"Enter item/menu number ");
                errMsg = "Wrong input. ";
            } while (itemNumber<=0 || itemNumber>size);
            items.get(itemNumber-1).perform();
            if(items.get(itemNumber-1).isExit()){
                break;
            }
        }
    }
}
