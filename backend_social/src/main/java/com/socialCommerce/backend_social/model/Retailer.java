package com.socialCommerce.backend_social.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Transactional
@Data
@NoArgsConstructor
@Table(name = "SC_RETAILER")
public class Retailer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "retailer_id")
    private int retailerId;
    private BigDecimal margin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product_id", referencedColumnName = "id")
    private Product product;

//    public int getRetailer_id() {
//        return retailerId;
//    }
}
