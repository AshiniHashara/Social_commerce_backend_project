package com.socialCommerce.backend_social.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "SC_CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cat_id")
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_cat_id",referencedColumnName = "cat_id" )
    private List<SubCategory> subCategory;
}
