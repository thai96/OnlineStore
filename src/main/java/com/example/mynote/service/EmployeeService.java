package com.example.mynote.service;

import com.example.mynote.model.Account;
import com.example.mynote.model.Department;
import com.example.mynote.model.Employee;
import com.example.mynote.payload.AccountInfo;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.EmployeeInfor;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(EmployeeInfor employee, AccountInfo account);
    Employee updateEmployee(EmployeeInfor oldInfor,EmployeeInfor newInfor);
    ApiResponse deleteEmployee(EmployeeInfor employeeInfor);
    EmployeeInfor getEmployeeInformation(String email);
    ApiResponse moveEmployeeDepartment(EmployeeInfor employee, Department newDepartment);
    List<EmployeeInfor> getAllEmployee(int page, int size);
}
