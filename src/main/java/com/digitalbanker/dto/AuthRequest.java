package com.digitalbanker.dto;

import lombok.*;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
