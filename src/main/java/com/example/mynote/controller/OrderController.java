package com.example.mynote.controller;

import com.example.mynote.model.Order;
import com.example.mynote.payload.ApiResponse;
import com.example.mynote.payload.OrderInfor;
import com.example.mynote.payload.Period;
import com.example.mynote.payload.UpdateRequest;
import com.example.mynote.service.OrderService;
import com.example.mynote.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderInfor>> getAll(
            @RequestParam(value = "page", required = false, defaultValue = AppConstant.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "begin",required = true) @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date begin,
            @RequestParam(value = "end", required = true) @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date end
    ){
        Period period = new Period(begin, end);
        List<OrderInfor> orders = orderService.getOrderByDate(period, page, size);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/customer/history")
    public ResponseEntity<List<OrderInfor>> getCustomerOrderHistory(
            @RequestParam String customerId
    ){
        if(customerId == null || customerId.trim().isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<OrderInfor> orders = orderService.getCustomerOrderHistory(customerId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/employee/history")
    public ResponseEntity<List<OrderInfor>> getEmployeeOrderHistory(
            @RequestParam Long id
    ){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<OrderInfor> orders = orderService.getEmployeeOrderHistory(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/information")
    public ResponseEntity<OrderInfor> getOrderInformation(
            @RequestParam(value = "id") Long id
    ){
        OrderInfor order = orderService.getOrderInfor(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<Order> insertOrder(
            @RequestBody OrderInfor order
    ){
        Order insertedOrder = orderService.addOrder(order);
        return new ResponseEntity<>(insertedOrder, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Order> updateOrder(
            @RequestBody UpdateRequest<OrderInfor, Long> updateRequest
    ){
        if(updateRequest.isNullInformation()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Order order = orderService.updateOrder(updateRequest.getNewInfor(), updateRequest.getItemId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOrder(
            @RequestParam(value = "id") Long id
    ){
        ApiResponse response = orderService.deleteOrder(id);
        return new ResponseEntity<>(response.getMessage(), response.getStatus());
    }
}
