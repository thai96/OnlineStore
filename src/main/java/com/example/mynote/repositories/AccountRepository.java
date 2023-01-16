package com.example.mynote.repositories;

import com.example.mynote.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findAccountByEmail(@NotBlank String email);
    Boolean existsAccountByEmail(@NotBlank String email);
}
