package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateRequest {
    //@Length(min = 3, max = 50, message = "First name's length should be 3 to 50 characters")
    private String firstName;

    //@Length(min = 3, max = 50, message = "Last name's length should be 3 to 50 characters")
    private String lastName;

    //@Email(message = "Email format is incorrect")
    private String email;
}
