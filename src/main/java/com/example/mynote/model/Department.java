package com.example.mynote.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Departments")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "departmentId")
public class Department {
    @Id
    @Column(name = "DepartmentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    @Column(name = "DepartmentName", length = 50)
    private String departmentName;
    @Column(name = "DepartmentType", length = 50)
    private String departmentType;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    List<Employee> employees;
}
