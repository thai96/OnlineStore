package com.example.mynote.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailService {
    UserDetails getUserDetail (String userId);
}
