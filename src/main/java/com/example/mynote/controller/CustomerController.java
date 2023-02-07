package com.example.mynote.controller;

import com.example.mynote.model.Customer;
import com.example.mynote.payload.*;
import com.example.mynote.service.AccountService;
import com.example.mynote.service.CustomerService;
import com.example.mynote.utils.AppConstant;
import com.example.mynote.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;
    private AccountService accountService;

    @Autowired
    public CustomerController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerInfor>> getAll(
            @RequestParam(value = "page", required = false, defaultValue = AppConstant.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size
    ){
        List<CustomerInfor> customerInfors = customerService.getAllCustomerInfor(page, size);
        return new ResponseEntity<>(customerInfors, HttpStatus.OK);
    }

    @GetMapping("/information")
    public ResponseEntity<CustomerInfor> getCustomer(
            @RequestParam(value = "email") String email
    ){
        CustomerInfor customer = customerService.getCurrentCustomerInformation(email);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Customer> updateCustomer(
            @RequestBody UpdateRequest<CustomerInfor> customers
    ){
        if(customers.isNullInformation()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer = customerService.updateCustomer(customers.getOldInfor(), customers.getNewInfor());
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PutMapping("add")
    public ResponseEntity<Customer> insertCustomer(
            @RequestBody AccountRelatedRequest<Customer> insertRequest
    ){
        String accountEmail = insertRequest.getEmail();
        if(accountEmail == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(insertRequest.getRequestContent() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer customer = customerService.addCustomer(insertRequest.getRequestContent(), accountEmail);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(
            @RequestParam(value = "id") String customerId
    ){
        ApiResponse response = customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(response.getMessage(),response.getStatus());
    }

}
