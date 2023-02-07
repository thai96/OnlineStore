package com.example.mynote.repositories;

import com.example.mynote.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Boolean existsByDepartmentName(@NotNull String departmentName);
    Optional<Department> findDepartmentByDepartmentName(@NotNull String departmentName);
}
