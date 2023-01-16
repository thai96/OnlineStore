package com.example.mynote.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @Column(name = "CustomerID",length = 5)
    @Setter(AccessLevel.NONE)
    private String customerId;
    @Column(name = "CompanyName", length = 40)
    private String companyName;
    @Column(name = "ContactName", length = 30)
    private String contactName;
    @Column(name = "ContactTitle")
    private String contactTitle;
    @Column(name = "Address")
    public String address;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
    private Account account;
}
