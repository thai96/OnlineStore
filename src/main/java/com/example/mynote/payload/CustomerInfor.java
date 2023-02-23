package com.example.mynote.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class CustomerInfor {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String customerId;
    private String contactName;
    private String contactTitle;
    private String companyName;
    private String address;
}
