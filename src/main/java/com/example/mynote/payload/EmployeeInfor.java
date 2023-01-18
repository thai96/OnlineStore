package com.example.mynote.payload;

import com.example.mynote.model.Department;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;
@Data
public class EmployeeInfor {
    @Setter(AccessLevel.NONE)
    private Long employeeId;
    private String lastName;
    private String firstName;
    private Department department;
    private String title;
    private String titleOfCourtesy;
    private Date birthDate;
    private Date hireDate;
    private String address;

}
