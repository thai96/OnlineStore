package com.example.mynote.payload;

import com.example.mynote.model.Department;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.Date;
@Data
public class EmployeeInfor {
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
