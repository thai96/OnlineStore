package com.example.mynote.service;

import com.example.mynote.model.Account;
import com.example.mynote.model.Customer;
import com.example.mynote.payload.AccountInfo;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.CustomerInfor;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer newCustomer, Account account);
    Customer updateCustomer(CustomerInfor oldInfor, CustomerInfor newInfor);
    ApiResponse deleteCustomer(CustomerInfor customerInfor);
    CustomerInfor getCurrentCustomerInformation(String email);
    List<CustomerInfor> getAllCustomerInfor(int page, int size);
}
