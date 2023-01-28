package com.example.mynote.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Categories")
public class Category {
    @Id
    @Column(name = "CategoryID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long categoryId;
    @Column(name = "CategoryName",unique = true ,nullable = false, length = 15)
    private String categoryName;
    @Column(name = "Description")
    private String description;
    @Column(name = "Picture")
    private byte[] picture;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    List<Product> products;
}
