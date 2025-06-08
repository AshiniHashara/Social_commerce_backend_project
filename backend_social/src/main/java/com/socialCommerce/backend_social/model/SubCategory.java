package com.socialCommerce.backend_social.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "SC_SUBCATEGORY")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subCat_id")
    private int subCatId;
    private String subCatName;

}
