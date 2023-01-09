package com.example.mynote.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    @Column(name = "CustomerID",length = 5)
    private String customerId;
    @Column(name = "CompanyName", length = 40)
    private String companyName;
    @Column(name = "ContactName", length = 30)
    private String contactName;
    @Column(name = "ContactTitle")
    private String contactTitle;
    @Column(name = "Address")
    public String address;

    public Customer() {}

    public Customer(String customerId, String companyName, String contactName, String contactTitle, String address) {
        this.customerId = customerId;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
