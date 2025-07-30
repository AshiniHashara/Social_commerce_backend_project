package com.socialCommerce.backend_social.model;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private int category_id;
    private int subcategory_id;
    private BigDecimal price;
    private Date release_date;
    private boolean available;
    private int quantity;
    private List<String> imageID;
    private List<String> imageUrls;


    // other fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(int subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getReleaseDate(Date releaseDate) {
        return release_date;
    }

    public void setReleaseDate(Date release_date) {
        this.release_date = release_date;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getQuantity(int quantity) {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
    public void setimageID(List<String> imageID) {
        this.imageID=imageID;
    }
    public List<String> getImageID() {
        return imageID;
    }

}



