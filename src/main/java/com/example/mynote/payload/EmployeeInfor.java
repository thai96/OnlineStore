package com.example.mynote.payload;

import com.example.mynote.model.Department;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;
@Data
public class EmployeeInfor {
    private Long employeeId;
    private String lastName;
    private String firstName;
    private String departmentName;
    private String title;
    private String titleOfCourtesy;
    private Date birthDate;
    private Date hireDate;
    private String address;

}
