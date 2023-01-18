package com.example.mynote.service;

import com.example.mynote.model.Department;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.EmployeeInfor;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department department);
    Department updateDepartment(Department oldInfor, Department newInfor);
    List<EmployeeInfor> getDepartmentEmployees(Department department);
    ApiResponse deleteDepartment(Department department);
}
