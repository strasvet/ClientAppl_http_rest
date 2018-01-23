package com.company.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    //@NotBlank(message = "Email cannot be blank")
    //@Email(message = "Email format is incorrect")
    private String email;

    //@NotBlank(message = "Password cannot be blank")
    private String password;
}
