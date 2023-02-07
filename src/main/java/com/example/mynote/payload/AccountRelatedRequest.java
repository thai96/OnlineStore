package com.example.mynote.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class AccountRelatedRequest <T>{
    @JsonIgnore
    private String accountEmail;
    @JsonIgnore
    private AccountInfo accountInfo;
    private T requestContent;

    public Boolean checkContentNotNull(){
        return !(requestContent == null);
    }

    public String getEmail(){
        if(checkStringNotEmptyOrBlank(accountEmail)){
            return accountEmail;
        }
        if(accountInfo != null && checkStringNotEmptyOrBlank(accountInfo.getEmail())){
            return accountInfo.getEmail();
        }
        return null;
    }
    private Boolean checkStringNotEmptyOrBlank( String string){
        return !(string == null || string.trim().isEmpty());
    }
}
