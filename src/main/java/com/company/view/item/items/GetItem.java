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
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class GetItem extends Item {
    public GetItem(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Get Item By Id";
    }

    @Override
    public void perform() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<ItemResponse>entity = new HttpEntity<ItemResponse>(null, headers);

        String id = inputOutput.getInteger("Please,enter id").toString();
        Settings.url.put("itemId",id);

        RestTemplate restTemplate = new RestTemplate();
        //ResponseEntity<ItemResponse> responseEntity = restTemplate.exchange("http://localhost:8080/items/{itemId}",HttpMethod.GET, entity,ItemResponse.class, Settings.url);
        ItemResponse itemResponse =(ItemResponse) PostMan2.getPostman(Settings.url.get("item"),HttpMethod.GET, entity,Select.item);
        //System.out.println(itemResponse);
        if(itemResponse!=null) {
            inputOutput.put(itemResponse);
        }else{
            inputOutput.put("Is item id: \""+id+"\" not found..");
        }
    }
}
