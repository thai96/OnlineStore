package com.example.mynote.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Products")
public class Product {
    @Id
    @Column(name = "ProductID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long productId;
    @Column(name = "ProductName", length = 40, nullable = false)
    private String productName;
    @ManyToOne
    @JoinColumn(name = "CategoryID")
    private Category category;
    @Column(name = "QuantityPerUnit",length = 20)
    private String quantityPerUnit;
    @Column(name = "UnitPrice")
    private BigDecimal unitPrice;
    @Column(name = "UnitsInStock")
    private short unitInStock;
    @Column(name = "UnitsOnStock")
    private short unitOnStock;
    @Column(name = "ReorderLevel")
    private short reOrderLevel;
    @Column(name = "Discontinued")
    private Boolean discontinued;
    @Column(name = "Picture")
    private byte[] picture;
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    List<OrderDetail> orderDetails;
}
