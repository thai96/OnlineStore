package com.example.mynote.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Employee {
    @Id
    @Column(name = "EmployeeID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    @Column(name = "LastName", length = 20, nullable = false)
    private String lastName;
    @Column(name = "FirstName", length = 10, nullable = false)
    private String firstName;
    @Column(name = "DepartmentID")
    private Long departmentId;
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

    public Employee() {}

    public Employee(String lastName, String firstName, Long departmentId, String title, String titleOfCourtesy, Date birthDate, Date hireDate, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.departmentId = departmentId;
        this.title = title;
        this.titleOfCourtesy = titleOfCourtesy;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.address = address;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleOfCourtesy(String titleOfCourtesy) {
        this.titleOfCourtesy = titleOfCourtesy;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleOfCourtesy() {
        return titleOfCourtesy;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public String getAddress() {
        return address;
    }
}
