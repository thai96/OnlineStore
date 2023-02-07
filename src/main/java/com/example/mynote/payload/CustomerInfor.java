package com.example.mynote.payload;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class CustomerInfor {
    @Setter(AccessLevel.NONE)
    private String customerId;
    private String contactName;
    private String contactTitle;
    private String companyName;
    private String address;
}
