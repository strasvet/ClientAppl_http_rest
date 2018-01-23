package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Data
public class ErrorResponse {
    int errorStatus;
    Map<String, List<String>> errorResponse;
    String message;
}
