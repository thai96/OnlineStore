package com.example.mynote.payload;

import com.example.mynote.model.Customer;
import com.example.mynote.model.Employee;
import lombok.Data;

@Data
public class AccountInfo {
    private Long accountId;
    private String email;
    private String role;
    private Customer customer;
    private Employee employee;

}
