package com.example.mynote.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Column(name = "AccountID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long accountId;
    @Column(name = "Email", unique = true)
    private String email;
    @Column(name = "Password")
    private String password;
    @OneToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;
    @Column(name = "Role")
    private String role;
}
