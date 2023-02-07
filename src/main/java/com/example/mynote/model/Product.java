package com.example.mynote.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Products")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productId")
public class Product {
    @Id
    @Column(name = "ProductID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    @ToString.Include
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
    @Column(name = "UnitsOnOrder")
    private short unitOnOrder;
    @Column(name = "ReorderLevel")
    private short reOrderLevel;
    @Column(name = "Discontinued")
    private Boolean discontinued;
    @Column(name = "Picture")
    private byte[] picture;
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    List<OrderDetail> orderDetails;
}
