package com.company.dto;

import com.company.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationResponse {
    private String sessionId;
    private User user;
}
