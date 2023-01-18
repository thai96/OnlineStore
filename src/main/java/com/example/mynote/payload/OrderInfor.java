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
    Customer customer;
    Employee employee;
    @JsonIgnore
    private Date orderDate;
    @JsonIgnore
    private Date requiredDate;
    @JsonIgnore
    private Date shippedDate;
    private BigDecimal freight;
    private String shipName;
    private String shipAddress;
    @JsonIgnore
    private String shipCity;
    @JsonIgnore
    private String shipRegion;
    List<OrderDetail> orderDetails;
}
