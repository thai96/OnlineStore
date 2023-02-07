package com.example.mynote.payload;

import com.example.mynote.model.Category;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Data
public class ProductInfor {
    private Long productId;
    private String productName;
    private CategoryInfor category;
    private String quantityPerUnit;
    private BigDecimal unitPrice;
    private short unitInStock;
    private short unitOnStock;
    private short reOrderLevel;
    private Boolean discontinued;
    @Column(name = "Picture")
    private byte[] picture;
}
