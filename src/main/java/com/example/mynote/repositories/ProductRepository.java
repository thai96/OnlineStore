package com.example.mynote.repositories;

import com.example.mynote.exception.ResourceNotFoundException;
import com.example.mynote.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
     default int getProductAmount(Long productId){
        Optional<Product> productOptional = findById(productId);
        if(productOptional.isPresent()){
            return productOptional.get().getUnitInStock();
        }
        else{
            return -1;
        }
    }

    Optional<Product>findByProductName(@NotNull String productName);
}
