package com.example.mynote.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class SignUpRequest {
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 50)
    private String password;
    @NotBlank
    private String role;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CustomerInfor customerInfor;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EmployeeInfor employeeInfor;
}
