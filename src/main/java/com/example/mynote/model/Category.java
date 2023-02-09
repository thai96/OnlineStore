package com.example.mynote.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Categories")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "categoryId", scope = Category.class)
public class Category {
    @Id
    @Column(name = "CategoryID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    @ToString.Include
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
