package com.example.mynote.service;

import com.example.mynote.model.Account;
import com.example.mynote.model.Department;
import com.example.mynote.model.Employee;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.EmployeeInfor;

public interface EmployeeService {
    Employee addEmployee(Employee employee, Account account);
    Employee updateEmployee(EmployeeInfor oldInfor,EmployeeInfor newInfor);
    ApiResponse deleteEmployee(EmployeeInfor employeeInfor);
    EmployeeInfor getEmployeeInformation(String email);
    ApiResponse moveEmployeeDepartment(EmployeeInfor employee, Department newDepartment);

}
