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

public class ChangeToDone extends Item {
    public ChangeToDone(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Change Item to Done";
    }

    @Override
    public void perform() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<ItemResponse>entity = new HttpEntity<ItemResponse>(null, headers);

        String id = inputOutput.getInteger("Please, enter id for change").toString();
        Settings.url.put("itemId",id);

        RestTemplate restTemplate = new RestTemplate();
        ItemResponse itemResponse =(ItemResponse) PostMan2.getPostman(Settings.url.get("done"),HttpMethod.PUT, entity, Select.item);

        if(itemResponse!=null) {
            String str ="Item status of item id: "+itemResponse.getId() +"\n changed to: "+itemResponse.getItemStatus();
            inputOutput.put(str);
        }else{
            inputOutput.put("Is item id: \""+id+"\" not found..");
        }
    }
}
