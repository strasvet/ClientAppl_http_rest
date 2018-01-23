package com.company.view;

public class ReturnItem extends Item {
    public ReturnItem(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Return to main menu";
    }

    @Override
    public void perform() {
        inputOutput.put("***********************");

    }
    @Override
    public boolean isExit(){
        return true;
    }
}
