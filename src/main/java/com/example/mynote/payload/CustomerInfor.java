package com.example.mynote.payload;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class CustomerInfor {
    @Setter(AccessLevel.NONE)
    private Long customerId;
    private String email;
    private String contactName;
    private String contactTitle;
    private String companyName;
    private String address;
}
