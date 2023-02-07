package com.example.mynote.service.impl;

import com.example.mynote.exception.BadRequestException;
import com.example.mynote.exception.ResourceNotFoundException;
import com.example.mynote.model.Account;
import com.example.mynote.model.Department;
import com.example.mynote.model.Employee;
import com.example.mynote.payload.AccountInfo;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.EmployeeInfor;
import com.example.mynote.repositories.AccountRepository;
import com.example.mynote.repositories.DepartmentRepository;
import com.example.mynote.repositories.EmployeeRepository;
import com.example.mynote.service.EmployeeService;
import com.example.mynote.utils.AppUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private AccountRepository accountRepository;
    private DepartmentRepository departmentRepository;
    private ModelMapper mapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, AccountRepository accountRepository, DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.accountRepository = accountRepository;
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    @Override
    public Employee addEmployee(EmployeeInfor employee, AccountInfo account) {
        if(!accountRepository.existsAccountByEmail(account.getEmail())){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Department is existed");
            throw new BadRequestException(apiResponse);
        }
        Account acc = accountRepository.findAccountByEmail(account.getEmail()).orElseThrow(()->new ResourceNotFoundException("Account not existed!"));
        Employee savedEmployee = employeeRepository.save(mapper.map(employee, Employee.class));
        acc.setEmployee(savedEmployee);
        accountRepository.save(acc);
        return savedEmployee;
    }

    @Override
    public Employee updateEmployee(EmployeeInfor oldInfor, EmployeeInfor newInfor) {
        checkEmployeeExistById(oldInfor);
        Employee employee = employeeRepository.findById(oldInfor.getEmployeeId()).get();
        employee.setAddress(newInfor.getAddress());
        employee.setTitle(newInfor.getTitle());
        Department department = departmentRepository.findDepartmentByDepartmentName(newInfor.getDepartmentName()).orElseThrow(()->new ResourceNotFoundException("Department not found!"));
        employee.setDepartment(department);
        employee.setFirstName(newInfor.getFirstName());
        employee.setLastName(newInfor.getLastName());
        employee.setBirthDate(new java.sql.Date(newInfor.getBirthDate().getTime()));
        employee.setHireDate(new java.sql.Date(newInfor.getHireDate().getTime()));
        employee.setTitleOfCourtesy(newInfor.getTitleOfCourtesy());
        return employeeRepository.save(employee);
    }

    @Override
    public ApiResponse deleteEmployee(EmployeeInfor employeeInfor) {
        checkEmployeeExistById(employeeInfor);
        Employee employee = employeeRepository.findById(employeeInfor.getEmployeeId()).get();
        employeeRepository.delete(employee);
        return new ApiResponse(Boolean.TRUE,"Delete successful!");
    }

    @Override
    public EmployeeInfor getEmployeeInformation(String email) {
        if(!accountRepository.existsAccountByEmail(email)){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Department is existed");
            throw new BadRequestException(apiResponse);
        }
        Account account = accountRepository.findAccountByEmail(email).get();
        if(account.getEmployee() == null){
            throw new BadRequestException(new ApiResponse(Boolean.FALSE,"Employee not found"));
        }
        return mapper.map(
                account.getEmployee(),
                EmployeeInfor.class
        );
    }

    @Override
    public ApiResponse moveEmployeeDepartment(EmployeeInfor employee, Department newDepartment) {
        checkEmployeeExistById(employee);
        if(departmentRepository.existsById(newDepartment.getDepartmentId())){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Department not found");
            throw new BadRequestException(apiResponse);
        }
        Employee employeeInDepartment = employeeRepository.findById(employee.getEmployeeId()).get();
        employeeInDepartment.setDepartment(newDepartment);
        return new ApiResponse(Boolean.TRUE,"Employee move to new department successful!");
    }

    @Override
    public List<EmployeeInfor> getAllEmployee(int page, int size) {
        AppUtils.validatePageNumberAndSize(page,size);
        Pageable employeePageable = PageRequest.of(page, size, Sort.Direction.DESC, "employeeId");
        Page<Employee> employees = employeeRepository.findAll(employeePageable);
        return employees.stream().map(employee -> mapper.map(employee, EmployeeInfor.class)).collect(Collectors.toList());
    }

    private Boolean checkEmployeeExistById(EmployeeInfor infor){
        if(!employeeRepository.existsById(infor.getEmployeeId())){
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Employee not found");
            throw new BadRequestException(apiResponse);
        }
        return Boolean.TRUE;
    }
}
