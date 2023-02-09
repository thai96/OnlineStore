package com.example.mynote.payload;

import lombok.Data;

@Data
public class UpdateRequest<T,I>{
    private I itemId;
    private T newInfor;

    public Boolean isNullInformation(){
        return itemId == null || newInfor == null;
    }
}
