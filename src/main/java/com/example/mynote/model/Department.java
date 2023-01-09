package com.example.mynote.model;

import jakarta.persistence.*;

@Entity
public class Department {
    @Id
    @Column(name = "DepartmentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    @Column(name = "DepartmentName", length = 50)
    private String departmentName;
    @Column(name = "DepartmentType", length = 50)
    private String departmentType;

    public Department() {}

    public Department(Long departmentId, String departmentName, String departmentType) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentType = departmentType;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }
}
