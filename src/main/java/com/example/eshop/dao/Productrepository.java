package com.example.eshop.dao;

import com.example.eshop.model.Product;
import io.swagger.annotations.ApiModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@ApiModel(description = "This repository is to perform CRUD operation on Product table.")
@Repository
public interface Productrepository extends JpaRepository<Product, Integer>

        {

        // This query will find all products
           List<Product> findAllBy(Pageable pageable);

        // This query will find Product By ProductNameorId
            @Query("from Product where productname=?1 or productid=?1")
            Product searchProduct(String nid);

}
