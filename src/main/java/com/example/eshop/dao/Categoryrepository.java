package com.example.eshop.dao;

import com.example.eshop.model.Category;
import io.swagger.annotations.ApiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@ApiModel(description = "This repository is to perform CRUD operation on Category table.")
@Repository
public interface Categoryrepository extends JpaRepository<Category, Integer> {

    // This will find all Categories which contains passed string in CategoryName
    List<Category> findByCategorynameContaining(String categoryname);

    // This will find Category By CategoryId
    List<Category> findByCategoryid(int categotyid);

    // This will find all categories SortedBy CategoryName Ascending
    List<Category> findAllByOrderByCategorynameAsc();

    // This will find all categories SortedBy CategoryName Descending
    List<Category> findAllByOrderByCategorynameDesc();

    // This will find all categories SortedBy CategoryId Ascending
    List<Category> findAllByOrderByCategoryidAsc();

    // This will find all categories SortedBy CategoryId Descending
    List<Category> findAllByOrderByCategoryidDesc();


}
