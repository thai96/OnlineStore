package com.example.mynote.controller;

import com.example.mynote.model.Account;
import com.example.mynote.model.Employee;
import com.example.mynote.payload.*;
import com.example.mynote.service.AccountService;
import com.example.mynote.service.EmployeeService;
import com.example.mynote.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    private AccountService accountService;


    @Autowired
    public EmployeeController(EmployeeService employeeService, AccountService accountService) {
        this.employeeService = employeeService;
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeInfor>> getAll(
            @RequestParam(value = "page", required = false, defaultValue = AppConstant.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size
    ){
        List<EmployeeInfor> employeeInfors = employeeService.getAllEmployee(page, size);
        return new ResponseEntity<>(employeeInfors, HttpStatus.OK);
    }

    @GetMapping("/EmployeeInformation")
    public ResponseEntity<EmployeeInfor> getEmployee(
            @RequestParam(value = "email") String email
    ){
        EmployeeInfor employee = employeeService.getEmployeeInformation(email);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/UpdateEmployee")
    public ResponseEntity<Employee> updateEmployee(
            @RequestBody UpdateRequest<EmployeeInfor> employeeInfors
    ){
        if(employeeInfors.isNullInformation()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Employee employee =employeeService.updateEmployee(employeeInfors.getOldInfor(), employeeInfors.getNewInfor());
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/Delete")
    public ResponseEntity deleteEmployee(
            @RequestBody EmployeeInfor infor
    ){
        ApiResponse response = employeeService.deleteEmployee(infor);
        return new ResponseEntity(response.getStatus());
    }

    @PutMapping("/AddNew")
    public ResponseEntity<Employee> insertEmployee(
            @RequestBody AccountRelatedRequest<EmployeeInfor> infor
            ){
        AccountInfo account = null;
        if(infor.getAccountEmail() != null){
            account = accountService.getAccountInformation(infor.getAccountEmail());
        } else if (infor.getAccountInfo() != null) {
            account = accountService.getAccountInformation(infor.getAccountInfo().getEmail());
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Employee employee = employeeService.addEmployee(infor.getRequestContent(), account);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}
