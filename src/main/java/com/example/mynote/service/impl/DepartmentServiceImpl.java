package com.example.mynote.service.impl;

import com.example.mynote.exception.BadRequestException;
import com.example.mynote.exception.ResourceNotFoundException;
import com.example.mynote.model.Department;
import com.example.mynote.model.Employee;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.EmployeeInfor;
import com.example.mynote.repositories.DepartmentRepository;
import com.example.mynote.service.DepartmentService;
import com.example.mynote.utils.AppUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private ModelMapper mapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    @Override
    public Department addDepartment(Department department) {
        if(departmentRepository.existsByDepartmentName(department.getDepartmentName())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Department is existed");
            throw new BadRequestException(apiResponse);
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department oldInfor, Department newInfor) {
        checkDepartmentExistById(oldInfor);
        Department department = departmentRepository.findById(oldInfor.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department not found!"));
        department.setDepartmentType(newInfor.getDepartmentType());
        department.setDepartmentName(newInfor.getDepartmentName());
        return departmentRepository.save(department);
    }

    @Override
    public List<EmployeeInfor> getDepartmentEmployees(Department department) {
        checkDepartmentExistById(department);
        Department departmentEmployee = departmentRepository.findById(department.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("Department not found!"));
        List<Employee> employees = departmentEmployee.getEmployees();
        if(employees.isEmpty()){
            return Collections.emptyList();
        }
        return employees.stream().map(employee -> mapper.map(employee, EmployeeInfor.class)).collect(Collectors.toList());
    }

    @Override
    public ApiResponse deleteDepartment(Department department) {
        checkDepartmentExistById(department);
        departmentRepository.delete(department);
        return new ApiResponse(Boolean.TRUE,"Delete successful");
    }

    @Override
    public List<Department> getAllDepartment(int page, int size) {
        AppUtils.validatePageNumberAndSize(page,size);
        Pageable departmentPageable = PageRequest.of(page,size, Sort.Direction.DESC,"departmentName");
        return departmentRepository.findAll(departmentPageable).getContent();
    }

    private Boolean checkDepartmentExistById(Department department){
        if(departmentRepository.existsById(department.getDepartmentId())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Department is existed");
            throw new BadRequestException(apiResponse);
        }
        return Boolean.TRUE;
    }
}
