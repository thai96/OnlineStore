package com.example.mynote.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Order {
    @Id
    @Column(name = "OrderID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column(name = "CustomerID", length = 5)
    private String customerId;
    @Column(name = "EmployeeID")
    private Long employeeId;
    @Column(name = "OrderDate")
    private Date orderDate;
    @Column(name = "RequiredDate")
    private Date requiredDate;
    @Column(name = "ShippedDate")
    private Date shippedDate;
    @Column(name = "Freight")
    private BigDecimal freight;
    @Column(name = "ShipName", length = 40)
    private String shipName;
    @Column(name = "ShipAddress", length = 60)
    private String shipAddress;
    @Column(name = "ShipCity", length = 15)
    private String shipCity;
    @Column(name = "ShipRegion", length = 15)
    private String shipRegion;
    @Column(name = "ShipPostalCode", length = 10)
    private String shipPostalCode;
    @Column(name = "ShipCountry", length = 15)
    private String shipCountry;

    public Order() {}

    public Order(String customerId, Long employeeId, Date orderDate, Date requiredDate, Date shippedDate, BigDecimal freight, String shipName, String shipAddress, String shipCity, String shipRegion, String shipPostalCode, String shipCountry) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.freight = freight;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipRegion = shipRegion;
        this.shipPostalCode = shipPostalCode;
        this.shipCountry = shipCountry;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getRequiredDate() {
        return requiredDate;
    }

    public Date getShippedDate() {
        return shippedDate;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public String getShipName() {
        return shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setRequiredDate(Date requiredDate) {
        this.requiredDate = requiredDate;
    }

    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }
}
