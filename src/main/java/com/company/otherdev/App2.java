package com.company.otherdev;

import com.company.core.PostMan2;
import com.company.dto.ItemResponse;
import com.company.dto.LoginRequest;
import com.company.entities.NewUser;
import com.company.entities.User;
import com.company.model.Select;
import com.company.model.Settings;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class App2 {

    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
@SneakyThrows
    public static void main(String[] args) {

        //System.setProperty("SESSION_ID", "4450bec9-424e-4bda-ab2d-4645ab08460e");
 //       System.setProperty("SESSION_ID", "da1e263e-7e4f-4526-ae92-308f59bbba7e");
       Settings.getProperties();
        //System.out.println(Settings.url.get("register"));

    //registerUser();

        loginUser();

        //getUser();
        //getUsers();


       //createItem();
       // changeToDone();
    //deleteItem();
    //getItem();
    getItems();

    }

    private static void getItems() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<ItemResponse>entity = new HttpEntity<ItemResponse>(null, headers);
        Settings.url.put("itemId","12");
        List<ItemResponse> itemResponse =(List<ItemResponse>) PostMan2.getPostman("http://localhost:8080/items/",HttpMethod.GET, entity,Select.items);
        itemResponse.forEach(x-> System.out.println(x));



        // RestTemplate restTemplate = new RestTemplate();
      //  ResponseEntity<ItemResponse[]> responseEntity = restTemplate.exchange("http://localhost:8080/items/",HttpMethod.GET, entity,ItemResponse[].class, Settings.url);
     //   Arrays.asList(responseEntity.getBody()).forEach(x->System.out.println(x));



        //String str ="Item status of item id: "+responseEntity.getBody().getId() +"\n changed to: "+responseEntity.getBody().getItemStatus();

       // System.out.println(responseEntity.getBody());
    }

    private static void getItem() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<ItemResponse>entity = new HttpEntity<ItemResponse>(null, headers);

        Settings.url.put("itemId","1");

        RestTemplate restTemplate = new RestTemplate();
        //ResponseEntity<ItemResponse> responseEntity = restTemplate.exchange("http://localhost:8080/items/{itemId}",HttpMethod.GET, entity,ItemResponse.class, Settings.url);
        ItemResponse itemResponse =(ItemResponse) PostMan2.getPostman("http://localhost:8080/items/{itemId}",HttpMethod.GET, entity,Select.item);
        System.out.println(itemResponse);
        //String str ="Item status of item id: "+responseEntity.getBody().getId() +"\n changed to: "+responseEntity.getBody().getItemStatus();

        //System.out.println(responseEntity.getBody());
    }

    private static void deleteItem() {
    //type string
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<ItemResponse>entity = new HttpEntity<ItemResponse>(null, headers);

        Settings.url.put("itemId","11");

        RestTemplate restTemplate = new RestTemplate();
        String responseEntity = restTemplate.exchange("http://localhost:8080/items/{itemId}",HttpMethod.DELETE, entity,String.class, Settings.url).toString();
        System.out.println(responseEntity);
 //       ResponseEntity<ItemResponse> responseEntity = restTemplate.exchange("http://localhost:8080/items/{itemId}",HttpMethod.DELETE, entity,ItemResponse.class, Settings.url);
 //       String str = responseEntity.getBody().toString();
 //       System.out.println(str);

        //String str ="Item status of item id: "+responseEntity.getBody().getId() +"\n changed to: "+responseEntity.getBody().getItemStatus();
        //System.out.println(str);
     //   ResponseEntity<ItemResponse> responseEntity = restTemplate.exchange("http://localhost:8080/items/{itemId}",HttpMethod.DELETE, entity, ItemResponse.class, Settings.url);
     //   System.out.println(responseEntity.getBody().toString());

    }

    private static void changeToDone() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<ItemResponse>entity = new HttpEntity<ItemResponse>(null, headers);

        Settings.url.put("itemId","11");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ItemResponse> responseEntity = restTemplate.exchange("http://localhost:8080/items/done/{itemId}",HttpMethod.PUT, entity,ItemResponse.class, Settings.url);
        String str ="Item status of item id: "+responseEntity.getBody().getId() +"\n changed to: "+responseEntity.getBody().getItemStatus();
        System.out.println(str);
    }

    private static void createItem() {
        //String url= Settings.url.get("login");
        ItemResponse item = new ItemResponse();
        item.setTitle("stringTitles");
        item.setDescription("stringDescription");
        item.setItemType("1");

        HttpMethod type = HttpMethod.POST;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<ItemResponse>entity = new HttpEntity<ItemResponse>(item, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ItemResponse> responseEntity = restTemplate.exchange("http://localhost:8080/items/",HttpMethod.POST, entity, ItemResponse.class);
        ItemResponse registrationResponse =responseEntity.getBody();
        System.out.println(registrationResponse);
        //User user =(User) PostMan2.getPostman("http://localhost:8080/items/",HttpMethod.POST, entity, Select.other);
        //System.out.println("email from return: "+user.getEmail());
    }


    //    private static void getUser() {
//        System.out.println("SESSION_ID IS:\n"+System.getProperty("SESSION_ID"));
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", "*/*");
//        headers.add("Autorization", System.getProperty("SESSION_ID"));
//        HttpEntity<User> entity = new HttpEntity<>(null,headers);
//        //Settings.url.put("userID","2");
//
//        String str =restTemplate.exchange("http://localhost:8080/users/2", HttpMethod.GET,entity,String.class).getBody();
//        System.out.println(str);
//        //PostMan.getPostman("http://192.168.1.21:8080/users/1",HttpMethod.GET,entity,Settings.url);
//    }
    private static void getUser(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<User> entity = new HttpEntity<User>(null, headers);
        Settings.url.put("userId", "1");
        //PostMan.getPostman("http://localhost:8080/users/1", HttpMethod.GET,entity,null);

        //String bo = restTemplate.exchange("http://localhost:8080/users/1",HttpMethod.GET,entity,String.class).getBody();
        //System.out.println(bo);


        //*** End Vers
        //ResponseEntity<User> response = restTemplate.exchange(Settings.url.get("user"),HttpMethod.GET,entity, User.class, Settings.url);
        //User user = response.getBody();
        //System.out.println(user);
        User user = (User) PostMan2.getPostman(Settings.url.get("user"), HttpMethod.GET,entity,Select.user);
        System.out.println((String.format("%s %s",user.getFirstName(),user.getLastName())));

//        String boo = restTemplate.exchange("http://localhost:8080/users/1",HttpMethod.GET, entity, String.class).getBody();
//        try {
//            User user = new ObjectMapper().readValue(boo, new TypeReference<User>() {});
//            //List<User> users = new ObjectMapper().readValue(body, new TypeReference<List<User>>() {});
//            System.out.println(user);
//        } catch (IOException e) { /* not a..e.printStackTrace();*/ }
        //restTemplate.exchange("http://localhost:8080/users/1",HttpMethod.GET, entity, String.class);
    }
    private static void getUsers() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Accept", "*/*");
        headers.add("Authorization", System.getProperty("SESSION_ID"));
        HttpEntity<User> entity = new HttpEntity<>(null,headers);
        String url = Settings.url.get("users");
        //PostMan.getPostman("http://localhost:8080/users/", HttpMethod.GET,entity,Settings.url);

        //String bo = restTemplate.exchange("http://localhost:8080/users/",HttpMethod.GET,entity,String.class).getBody();
        //List<User> users = new ObjectMapper().readValue(bo, new TypeReference<List<User>>() {});
        //users.forEach(x->System.out.println(String.format("%s %s",x.getFirstName(),x.getLastName())));
        //*** endsVers
        //ResponseEntity<User[]> users = restTemplate.exchange("http://localhost:8080/users/", HttpMethod.GET, entity, User[].class);
        //Arrays.asList(users.getBody()).forEach(x->System.out.println(String.format("%s %s",x.getFirstName(),x.getLastName())));
        List<User> users = (List<User>) PostMan2.getPostman("http://localhost:8080/users/", HttpMethod.GET,entity,Select.users);
        users.forEach(x->System.out.println(String.format("%s %s",x.getFirstName(),x.getLastName())));

    }

    private static void loginUser() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("dmitry@mail.ru");
        loginRequest.setPassword("password1");

       String url= Settings.url.get("login");
       HttpMethod type = HttpMethod.POST;
       HttpHeaders headers = new HttpHeaders();
       //headers.set("Autorization", System.getProperty("SESSION_ID"));
        headers.set("Authorization", null);
       HttpEntity<LoginRequest>entity = new HttpEntity<LoginRequest>(loginRequest, headers);
       //PostMan.getPostman("http://localhost:8080/users/login",HttpMethod.POST, entity,Settings.url);
       User user =(User) PostMan2.getPostman("http://localhost:8080/users/login",HttpMethod.POST, entity, Select.other);
        System.out.println("email from return: "+user.getEmail()+"\n Session id: \n"+System.getProperty("SESSION_ID"));
    }

    private static void registerUser() throws IOException {
        //*** get Url
        String url = "http://localhost:8080/users/register";
        //*** get type HTTPMethod
        HttpMethod type = HttpMethod.POST;
        //*** get New User + Headers + entity
        NewUser user = new NewUser();
        System.out.println("Enter First Name:");
        user.setFirstName(input.readLine());
        System.out.println("Enter last name:");
        user.setLastName(input.readLine());
        System.out.println("Enter e-mail:");
        user.setEmail(input.readLine());
        System.out.println("Enter password:");
        user.setPassword(input.readLine());
        //Headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("Authorization", System.getProperty("SESSION_ID"));
        //entity
        //HttpEntity<NewUser> entity = new HttpEntity<NewUser>(user,headers);
        HttpEntity<NewUser> entity = new HttpEntity<NewUser>(user,null);


        //PostMan post = new PostMan();
        PostMan.getPostman(url,type,entity, null);
    }
}
