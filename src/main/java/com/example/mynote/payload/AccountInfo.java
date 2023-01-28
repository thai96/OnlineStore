package com.example.mynote.payload;

import com.example.mynote.model.Customer;
import com.example.mynote.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

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
