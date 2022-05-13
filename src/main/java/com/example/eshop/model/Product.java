package com.example.eshop.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@ApiModel(description = "This model is for product table")
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @Min(value = 1)
    @ApiModelProperty(notes = "This field is for ProductId and ProductId Must be Greater then 0.")
    @Column(name = "productid", updatable = false, nullable = false, unique = true)
    private int productid;

    @NotEmpty
    @ApiModelProperty(notes = "This field is for Product Name and Product Name can not be empty.")
    private String productname;

    @ApiModelProperty(notes = "This field is for Product Description.")
    private String description;

    @Positive
    @ApiModelProperty(notes = "This field is for Product Price.")
    private int price;

    @ApiModelProperty(notes = "This field is for CategoryId, it relates to category table.")
    private int categoryid;

    @PositiveOrZero
    @ApiModelProperty(notes = "This field is for Product quantity.")
    private int quantity;

    @Override
    public String toString() {
        return "Product{" +
                "productid=" + productid +
                ", productname='" + productname + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", categoryid=" + categoryid +
                ", quantity=" + quantity +
                '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

}
