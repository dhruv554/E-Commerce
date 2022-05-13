package com.example.eshop.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


@ApiModel(description = "This model is for category table.")
@Entity
@Table(name = "Category")
public class Category {

    @Id
    @Min(value = 1)
    @ApiModelProperty(notes = "This field is for CategoryId and CategoryId Must be Greater then 0.")
    @Column(name = "categoryid", updatable = false, nullable = false, unique = true)
    private int categoryid;

    @NotEmpty
    @ApiModelProperty(notes = "This field is for Category Name and Category Name can not be empty.")
    private String categoryname;

    @Override
    public String toString() {
        return "Category{" +
                "categoryid=" + categoryid +
                ", categoryname='" + categoryname + '\'' +
                '}';
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

}
