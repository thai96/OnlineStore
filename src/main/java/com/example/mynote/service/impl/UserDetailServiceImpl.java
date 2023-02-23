package com.example.mynote.service.impl;

import com.example.mynote.model.Account;
import com.example.mynote.repositories.AccountRepository;
import com.example.mynote.security.UserPrincipal;
import com.example.mynote.service.UserDetailService;
import com.example.mynote.utils.RoleUtils;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailService, UserDetailsService {

    private AccountRepository accountRepository;
    private ModelMapper mapper;

    @Autowired
    public UserDetailServiceImpl(AccountRepository accountRepository, ModelMapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails getUserDetail(String username) {
        Account account = accountRepository.findAccountByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return mapper.map(account, UserPrincipal.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        TypeMap<Account, UserPrincipal> typeMap = mapper.getTypeMap(Account.class, UserPrincipal.class);
        if (typeMap == null) {
            typeMap = mapper.createTypeMap(Account.class, UserPrincipal.class);
        }
        Converter<Integer, String> roleConverter = ctx -> RoleUtils.getRoleString(ctx.getSource());
        typeMap.addMappings(mapper -> mapper.using(roleConverter).map(Account::getRole, UserPrincipal::setRole));
        UserPrincipal user = mapper.map(account, UserPrincipal.class);
        return user;
    }
}
