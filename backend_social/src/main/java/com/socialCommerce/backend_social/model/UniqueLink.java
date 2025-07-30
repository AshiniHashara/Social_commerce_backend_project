package com.socialCommerce.backend_social.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "SC_UNIQUE_LINK")
public class UniqueLink {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uniqueLink_id")
    private int id;
    private String unique_link;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_retailer_id", referencedColumnName = "retailer_id")
    private Retailer retailer;
}
