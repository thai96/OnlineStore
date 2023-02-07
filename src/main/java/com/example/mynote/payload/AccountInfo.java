package com.example.mynote.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfo {
    private Long accountId;
    private String email;
    private Role role;
    private CustomerInfor customer;
    private EmployeeInfor employee;

}
