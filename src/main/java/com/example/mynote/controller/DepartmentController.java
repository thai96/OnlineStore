package com.example.mynote.controller;

import com.example.mynote.model.Department;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.EmployeeInfor;
import com.example.mynote.payload.UpdateRequest;
import com.example.mynote.service.DepartmentService;
import com.example.mynote.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAll(
            @RequestParam(value = "page", required = false, defaultValue = AppConstant.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size
    ){
        List<Department> departments = departmentService.getAllDepartment(page, size);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeInfor>> getDepartmentEmployee(
            @RequestBody Department department
    ){
        List<EmployeeInfor> employeeInfors = departmentService.getDepartmentEmployees(department);
        return new ResponseEntity<>(employeeInfors, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Department> updateDepartment(
            @RequestBody UpdateRequest<Department> requestContent
    ){
        if(requestContent.isNullInformation()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Department updatedDepartment = departmentService.updateDepartment(requestContent.getOldInfor(), requestContent.getNewInfor());
        return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<Department> insertDepartment(
            @RequestBody Department department
    ){
        Department insertedDepartment = departmentService.addDepartment(department);
        return new ResponseEntity<>(insertedDepartment, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDepartment(
            @RequestBody Department department
    ){
        ApiResponse response = departmentService.deleteDepartment(department);
        return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }
}
