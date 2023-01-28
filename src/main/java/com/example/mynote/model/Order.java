package com.example.mynote.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @Column(name = "OrderID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employee;
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
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "order")
    private List<OrderDetail> orderDetail;
}
