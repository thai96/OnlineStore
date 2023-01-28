package com.example.mynote.repositories;

import com.example.mynote.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    Optional<Customer> findCustomerByContactName(@NotBlank String contactName);
    Optional<List<Customer>> findCustomersByCompanyName(@NotBlank String companyName);
    Optional<Customer> findCustomerByCustomerId(@NotBlank String customerId);

}
