package com.example.mynote.service;

import com.example.mynote.model.Order;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.OrderInfor;
import com.example.mynote.payload.Period;

import java.util.List;

public interface OrderService {
    Order addOrder(OrderInfor newOrder);
    Order updateOrder(OrderInfor modifiedRequest, OrderInfor oldRequest);
    ApiResponse deleteOrder(Long id);
    OrderInfor getOrderInfor(Long id);
    List<OrderInfor> getCustomerOrderHistory(int page, int size);
    List<OrderInfor> getEmployeeOrderHistory(int page, int size);
    List<OrderInfor> getOrderByDate(Period timePeriod,int page, int size);
    Order reOrder(Long id);
}
