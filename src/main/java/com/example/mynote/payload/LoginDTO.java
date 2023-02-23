package com.example.mynote.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
