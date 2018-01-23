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

import java.util.function.Predicate;

public class CreateItem extends Item {
    public CreateItem(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Create new Item";
    }

    @Override
    public void perform() {
        ItemResponse item = new ItemResponse();
        item.setTitle(inputOutput.getString("Please, input Title"));
        item.setDescription(inputOutput.getString("Please, input description"));

        Predicate<Integer> fromTo = x->x==3 || x==2 || x==1;
        item.setItemType(inputOutput.getInteger("Please, input itemType (1:TASK,2:BUG,3:TEST)", fromTo ).toString());

        HttpMethod type = HttpMethod.POST;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<ItemResponse>entity = new HttpEntity<ItemResponse>(item, headers);

        RestTemplate restTemplate = new RestTemplate();
        ItemResponse itemResponse =(ItemResponse) PostMan2.getPostman(Settings.url.get("create"),HttpMethod.POST, entity, Select.item);

        if(itemResponse!=null) {
            //inputOutput.put(itemResponse);
            inputOutput.put(String.format(" Id:%s\n Status:%s\n CreateDate:%s\n Description:%s\n Type:%s\n Title:%s\n Author%s\n e-mail:%s\n ",itemResponse.getId(),itemResponse.getItemStatus(),itemResponse.getCreatedDate(),itemResponse.getDescription(),itemResponse.getItemType(),itemResponse.getTitle(),itemResponse.getUser().getLastName(),itemResponse.getUser().getEmail()));
        }else{
            //inputOutput.put("Is item id: \""+id+"\" not found..");
        }
    }
}
