package com.example.mynote.service.impl;

import com.example.mynote.exception.BadRequestException;
import com.example.mynote.exception.ResourceNotFoundException;
import com.example.mynote.model.Customer;
import com.example.mynote.model.Employee;
import com.example.mynote.payload.*;
import com.example.mynote.utils.AppUtils;
import com.example.mynote.model.Account;
import com.example.mynote.repositories.AccountRepository;
import com.example.mynote.service.AccountService;
import com.example.mynote.utils.RoleUtils;
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
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private ModelMapper mapper;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public AccountInfo getAccountInformation(String email) {
        Account account = accountRepository.findAccountByEmail(email).orElseThrow(()-> new ResourceNotFoundException("account not found!"));
        AccountInfo accountInfor = new AccountInfo();
        accountInfor.setAccountId(account.getAccountId());
        accountInfor.setRole(RoleUtils.getRoleByInt(account.getRole()));
        accountInfor.setEmail(account.getEmail());
        accountInfor.setEmployee(account.getEmployee()==null? null : mapper.map(account.getEmployee(), EmployeeInfor.class));
        accountInfor.setCustomer(account.getCustomer()==null? null : mapper.map(account.getCustomer(), CustomerInfor.class));
        return accountInfor;
    }

    @Override
    public Boolean checkAccountAvaibility(String email) {
        return accountRepository.existsAccountByEmail(email);
    }

    @Override
    public Account addAccount(Account newAccount) {
        if(accountRepository.existsAccountByEmail(newAccount.getEmail())){
            ApiResponse response = new ApiResponse(Boolean.FALSE,"Email is existed");
            throw new BadRequestException(response);
        }
        return accountRepository.save(newAccount);
    }

    @Override
    public Account updateAccountInformation(AccountInfo newInformation, String email) {
        Account account = accountRepository.findAccountByEmail(email)
                .orElseThrow(() -> new BadRequestException(new ApiResponse(Boolean.FALSE, "Account not found!")));
        account.setEmail(newInformation.getEmail());
        account.setCustomer(mapper.map(newInformation.getCustomer(), Customer.class));
        account.setEmployee(mapper.map(newInformation.getEmployee(), Employee.class));
        return accountRepository.save(account);
    }

    @Override
    public ApiResponse deleteAccount(String email) {
        Account account = accountRepository.findAccountByEmail(email).orElseThrow(()->new ResourceNotFoundException("account not found"));
        accountRepository.delete(account);
        return new ApiResponse(Boolean.TRUE, "Delete account successfully");
    }

    @Override
    public ApiResponse giveAdmin(String email) {
        Account account = accountRepository.findAccountByEmail(email).orElseThrow(()->new ResourceNotFoundException("account not found"));
        if(account.getEmployee()==null){
            throw new BadRequestException(new ApiResponse(Boolean.FALSE, "Account not valid"));
        }
        account.setRole(RoleUtils.getRoleIdByRole(Role.EMPLOYEE));
        accountRepository.save(account);
        return new ApiResponse(Boolean.TRUE, "Give admin successful");
    }

    @Override
    public ApiResponse removeAdmin(String email) {
        Account account = accountRepository.findAccountByEmail(email).orElseThrow(()->new ResourceNotFoundException("account not found"));
        account.setRole(RoleUtils.getRoleIdByRole(Role.CUSTOMER));
        accountRepository.save(account);
        return new ApiResponse(Boolean.TRUE, "Remove admin successful");
    }

    @Override
    public List<AccountInfo> getAllAccount(int page, int size) {
        AppUtils.validatePageNumberAndSize(page,size);
        Pageable pageable = PageRequest.of(page,size, Sort.Direction.DESC,"accountId");
        Page<Account> accounts = accountRepository.findAll(pageable);
        List<AccountInfo> accountInfors = accounts.stream().map(account -> mapper.map(account, AccountInfo.class)).collect(Collectors.toList());
        return accountInfors;
    }
}
