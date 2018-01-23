package com.company.view.item.items;

import com.company.core.PostMan2;
import com.company.dto.ItemResponse;
import com.company.model.Select;
import com.company.model.Settings;
import com.company.view.InputOutput;
import com.company.view.Item;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Predicate;

public class DeleteItem extends Item {
    public DeleteItem(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Delete item";
    }

    @Override
    public void perform() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<ItemResponse>entity = new HttpEntity<ItemResponse>(null, headers);


        String id = inputOutput.getInteger("Please,enter id").toString();
        Settings.url.put("itemId",id);


        ResponseEntity<String> respo = (ResponseEntity<String>) PostMan2.getPostman(Settings.url.get("delete"),HttpMethod.DELETE, entity, Select.string);
        if(respo!=null){
            inputOutput.put("Status code: "+respo.getStatusCode());
        }else{
            //inputOutput.put("ItemId "+id +" is not found/or status..");
        }

    }
}
