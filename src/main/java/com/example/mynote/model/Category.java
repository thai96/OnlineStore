package com.example.mynote.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Column(name = "CategoryID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    public Long categoryId;
    @Column(name = "CategoryName", nullable = false, length = 15)
    public String categoryName;
    @Column(name = "Description")
    public String description;
    @Column(name = "Picture")
    public byte[] picture;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    List<Product> products;
}
