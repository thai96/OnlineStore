package com.example.mynote.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Accounts")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Account {
    @Id
    @Column(name = "AccountID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    @ToString.Include
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
    private int role;
}
