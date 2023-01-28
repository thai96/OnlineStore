package com.example.mynote.controller;

import com.example.mynote.payload.AccountInfo;
import com.example.mynote.service.AccountService;
import com.example.mynote.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
