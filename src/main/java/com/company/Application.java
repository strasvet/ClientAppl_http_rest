package com.company;

import com.company.dto.RegistrationResponse;
import com.company.entities.NewUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
public class Application
{
    public static void main( String[] args ) {

        regUs();




    }

    private static void regUs() {
        NewUser user = new NewUser("FirstName", "LastName", "emailmy@newmail.com", "passwords1New");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RegistrationResponse> response = restTemplate.postForEntity("http://192.168.1.21:8080/users/register", user, RegistrationResponse.class);
        RegistrationResponse registrationResponse = response.getBody();
        System.out.println("Body : \n"+registrationResponse);
    }
}
