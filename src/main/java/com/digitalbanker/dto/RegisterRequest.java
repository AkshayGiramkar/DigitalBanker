package com.digitalbanker.dto;

import lombok.*;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
}
