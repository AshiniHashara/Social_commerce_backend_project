package com.socialCommerce.backend_social.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.OneToMany;


@Entity
@Table(name = "SC_PRODUCT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int category_id;
    private int subcategory_id;
    private BigDecimal price;
    private Date releaseDate;
    private boolean available;
    private int quantity;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ImageData> imageDatas;
    private long imageID;


}


