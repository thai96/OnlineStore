package com.example.mynote.repositories;

import com.example.mynote.model.OrderDetail;
import com.example.mynote.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderProduct> {
}
