package com.example.mynote.payload;

import lombok.Data;

@Data
public class UpdateRequest<T>{
    private T oldInfor;
    private T newInfor;

    public Boolean isNullInformation(){
        return oldInfor == null || newInfor == null;
    }
}
