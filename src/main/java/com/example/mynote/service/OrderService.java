package com.example.mynote.service;

import com.example.mynote.model.Order;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.OrderInfor;
import com.example.mynote.payload.Period;

import java.util.List;

public interface OrderService {
    Order addOrder(OrderInfor newOrder);
    Order updateOrder(OrderInfor modifiedRequest, Long id);
    ApiResponse deleteOrder(Long id);
    OrderInfor getOrderInfor(Long id);
    List<OrderInfor> getCustomerOrderHistory(String customerId);
    List<OrderInfor> getEmployeeOrderHistory(Long employeeId);
    List<OrderInfor> getOrderByDate(Period timePeriod,int page, int size);
}
