package com.company.view.item.items;

import com.company.core.PostMan2;
import com.company.dto.ItemResponse;
import com.company.entities.User;
import com.company.model.Select;
import com.company.model.Settings;
import com.company.view.InputOutput;
import com.company.view.Item;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.List;

public class GetAllItems extends Item {
    public GetAllItems(InputOutput inputOutput) {
        super(inputOutput);
    }

    @Override
    public String displayedName() {
        return "Get all items";
    }

    @Override
    public void perform() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<ItemResponse>entity = new HttpEntity<ItemResponse>(null, headers);
        List<ItemResponse> itemResponse =(List<ItemResponse>) PostMan2.getPostman(Settings.url.get("items"),HttpMethod.GET, entity,Select.items);
        itemResponse.forEach(x-> inputOutput.put(String.format("[ %s ]",x)));

    }
}
