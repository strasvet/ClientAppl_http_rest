package com.company.dto;

import com.company.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemResponse {
        private String id;
        private String itemStatus;
        private String itemType;
        private String createdDate;
        private User user;
        private String description;
        private String title;


}
