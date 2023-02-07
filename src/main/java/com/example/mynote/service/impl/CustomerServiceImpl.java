package com.example.mynote.service.impl;

import com.example.mynote.exception.BadRequestException;
import com.example.mynote.exception.ResourceNotFoundException;
import com.example.mynote.model.Account;
import com.example.mynote.model.Customer;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.CustomerInfor;
import com.example.mynote.repositories.AccountRepository;
import com.example.mynote.repositories.CustomerRepository;
import com.example.mynote.service.CustomerService;
import com.example.mynote.utils.AppUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomerServiceImpl implements CustomerService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private ModelMapper mapper;

    @Autowired
    public CustomerServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository, ModelMapper mapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public Customer addCustomer(Customer newCustomer, String email) {
        if(!accountRepository.existsAccountByEmail(email)){
            ApiResponse response = new ApiResponse(Boolean.FALSE,"Account not existed!");
            throw new BadRequestException(response);
        }
        Customer savedCustomer = customerRepository.save(newCustomer);
        Account acc = accountRepository.findAccountByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Account not found!"));
        acc.setCustomer(savedCustomer);
        accountRepository.save(acc);
        return savedCustomer;
    }

    @Override
    public Customer updateCustomer(CustomerInfor oldInfor, CustomerInfor newInfor) {
        if(customerRepository.existsById(oldInfor.getCustomerId())){
            ApiResponse response = new ApiResponse(Boolean.FALSE, "Customer not existed!");
            throw new BadRequestException(response);
        }
        Customer customer = customerRepository.findCustomerByCustomerId(oldInfor.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer not existed"));
        customer.setAddress(newInfor.getAddress());
        customer.setCompanyName(newInfor.getCompanyName());
        customer.setContactName(newInfor.getContactName());
        customer.setContactTitle(newInfor.getContactTitle());
        return customerRepository.save(customer);
    }

    @Override
    public ApiResponse deleteCustomer(String customerId) {
        if(customerRepository.existsById(customerId)){
            ApiResponse response = new ApiResponse(Boolean.FALSE, "Customer not existed!");
            throw new BadRequestException(response);
        }
        customerRepository.deleteById(customerId);
        return new ApiResponse(Boolean.TRUE,"Delete customer successful");
    }

    @Override
    public CustomerInfor getCurrentCustomerInformation(String email) {
        if(!accountRepository.existsAccountByEmail(email)){
            ApiResponse response = new ApiResponse(Boolean.FALSE, "Account contains email not existed!");
            throw new BadRequestException(response);
        }
        Account account = accountRepository.findAccountByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Account contain email not existed!"));
        if(account.getCustomer() == null){
            ApiResponse response = new ApiResponse(Boolean.FALSE, "Account is not a customer account!");
            throw new BadRequestException(response);
        }
        Customer customer = account.getCustomer();
        return mapper.map(customer, CustomerInfor.class);
    }

    @Override
    public List<CustomerInfor> getAllCustomerInfor(int page, int size) {
        AppUtils.validatePageNumberAndSize(page,size);
        Pageable customerPageable = PageRequest.of(page,size, Sort.Direction.DESC,"customerId","contactName");
        Page<Customer> customerPages = customerRepository.findAll(customerPageable);
        List<CustomerInfor> customerInfors = customerPages.stream().map(customer -> mapper.map(customer, CustomerInfor.class)).collect(Collectors.toList());
        return customerInfors;
    }
}
