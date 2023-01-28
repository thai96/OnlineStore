package com.example.mynote.service;

import com.example.mynote.model.Account;
import com.example.mynote.payload.AccountInfo;
import com.example.mynote.payload.ApiResponse;

import java.util.List;

public interface AccountService {
    AccountInfo getAccountInformation(String email);
    Boolean checkAccountAvaibility(String email);
    Account addAccount(Account newAccount);
    Account updateAccountInformation(AccountInfo newInformation, AccountInfo oldInformation);
    ApiResponse deleteAccount(String email);
    ApiResponse giveAdmin(String email);
    ApiResponse removeAdmin(String email);
    List<AccountInfo> getAllAccount(int page, int size);
}
