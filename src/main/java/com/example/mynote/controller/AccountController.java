package com.example.mynote.controller;

import com.example.mynote.model.Account;
import com.example.mynote.payload.AccountInfo;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.UpdateRequest;
import com.example.mynote.service.AccountService;
import com.example.mynote.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountInfo>> getAll(
        @RequestParam(value = "page",required = false, defaultValue = AppConstant.DEFAULT_PAGE_NUMBER)Integer page,
        @RequestParam(value = "size",required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE)Integer size
    ){
        List<AccountInfo> accounts = accountService.getAllAccount(page,size);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/AccountInformation")
    public ResponseEntity<AccountInfo> getAccount(
            @RequestParam(value = "email")String email
    ){
        if(!accountService.checkAccountAvaibility(email)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        AccountInfo accountInfo = accountService.getAccountInformation(email);
        return new ResponseEntity<AccountInfo>(accountInfo, HttpStatus.OK);
    }

    @GetMapping("/GiveAdmin")
    public ResponseEntity giveAdmin(
            @RequestParam(value = "email")String email
    ){
        if(accountService.checkAccountAvaibility(email)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ApiResponse response = accountService.giveAdmin(email);
        return new ResponseEntity(response.getStatus());
    }

    @GetMapping("/RemoveAdmin")
    public ResponseEntity removeAdmin(
            @RequestParam(value = "email")String email
    ){
        if(accountService.checkAccountAvaibility(email)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ApiResponse response = accountService.removeAdmin(email);
        return new ResponseEntity(response.getStatus());
    }

    @PostMapping("/UpdateAccount")
    public ResponseEntity<Account> updateAccount(
            @RequestBody UpdateRequest<AccountInfo, String> request
            ){
        Account account = accountService.updateAccountInformation(request.getNewInfor(), request.getItemId());
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @PutMapping("/AddNewAccount")
    public ResponseEntity<Account> addAccount(
            @RequestBody Account account
    ){
        if(accountService.checkAccountAvaibility(account.getEmail())){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Account addedAccount = accountService.addAccount(account);
        return new ResponseEntity<>(addedAccount, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @DeleteMapping("/DeleteAccount")
    public ResponseEntity deleteAccount(
            @RequestParam(value = "email") String email
    ){
        ApiResponse response = accountService.deleteAccount(email);
        return new ResponseEntity(response.getStatus());
    }


}
