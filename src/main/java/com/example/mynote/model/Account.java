package com.example.mynote.model;


import javax.persistence.*;

@Entity
public class Account {
    @Id
    @Column(name = "AccountID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    @Column(name = "CustomerID")
    private String customerId;
    @Column(name = "EmployeeID")
    private String employeeId;
    @Column(name = "Role")
    private String role;

    public Account() {
    }

    public Account(Long accountId, String email, String password, String customerId, String employeeId, String role) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.role = role;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getRole() {
        return role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
