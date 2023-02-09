package com.example.mynote.repositories;

import com.example.mynote.model.OrderDetail;
import com.example.mynote.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderProduct> {
    List<OrderDetail> findOrderDetailsByOrder_OrderId(Long orderId);
}
