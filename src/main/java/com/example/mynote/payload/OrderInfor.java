package com.example.mynote.payload;

import com.example.mynote.model.Customer;
import com.example.mynote.model.Employee;
import com.example.mynote.model.OrderDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
@Data
public class OrderInfor {
    private Long orderId;
    private CustomerInfor customer;
    private EmployeeInfor employee;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private BigDecimal freight;
    private String shipName;
    private String shipAddress;
    private String shipCity;
    private String shipRegion;
    List<OrderDetailInfo> orderDetails;
}
