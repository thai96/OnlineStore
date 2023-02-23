package com.example.mynote.service;

import com.example.mynote.model.Account;
import com.example.mynote.payload.AccountInfo;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.SignUpRequest;

import java.util.List;

public interface AccountService {
    AccountInfo getAccountInformation(String email);
    Boolean checkAccountAvaibility(String email);
    ApiResponse registerUser(SignUpRequest signUpRequest);
    Account addAccount(Account newAccount);
    Account updateAccountInformation(AccountInfo newInformation, String email);
    ApiResponse deleteAccount(String email);
    ApiResponse giveAdmin(String email);
    ApiResponse removeAdmin(String email);
    List<AccountInfo> getAllAccount(int page, int size);

}
