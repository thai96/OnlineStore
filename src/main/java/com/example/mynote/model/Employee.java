package com.example.mynote.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employees")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeId")
public class Employee {
    @Id
    @Column(name = "EmployeeID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long employeeId;
    @Column(name = "LastName", length = 20, nullable = false)
    private String lastName;
    @Column(name = "FirstName", length = 10, nullable = false)
    private String firstName;
    @ManyToOne
    @JoinColumn(name = "DepartmentID")
    private Department department;
    @Column(name = "Title", length = 30)
    private String title;
    @Column(name = "TitleOfCourtesy", length = 25)
    private String titleOfCourtesy;
    @Column(name = "BirthDate")
    private Date birthDate;
    @Column(name = "HireDate")
    private Date hireDate;
    @Column(name = "Address", length = 60)
    private String address;
    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
    private Account account;
}
