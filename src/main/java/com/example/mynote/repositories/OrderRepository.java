package com.example.mynote.repositories;

import com.example.mynote.model.Customer;
import com.example.mynote.model.Employee;
import com.example.mynote.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    public List<Order> findAllByCustomer(Customer customer);
    public List<Order> findAllByEmployee(Employee employee);
}
