package com.example.eshop.service;

import com.example.eshop.dao.Categoryrepository;
import com.example.eshop.model.Category;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApiModel(description = "This is Category Service Class")
@Service
public class CategoryService {

    private Categoryrepository categoryrepository;

    @Autowired
    public CategoryService(Categoryrepository categoryrepository)
    {
        this.categoryrepository = categoryrepository;
    }

    public List<Category> getAllCategory()
    {
        List<Category> obj = new ArrayList<Category>();
        List<Category> category = categoryrepository.findAll();

        category.forEach( category1 -> {
            Category category2 = new Category();

            category2.setCategoryid(category1.getCategoryid());
            category2.setCategoryname(category1.getCategoryname());
            obj.add(category2);
        });
        return obj;
    }

    public Category getonecategory(int id){

        Optional<Category> category = categoryrepository.findById(id);
        if (category.isPresent()) {

            Category obj = new Category();

            obj.setCategoryid(category.get().getCategoryid());
            obj.setCategoryname(category.get().getCategoryname());
            return obj;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category does not exist");
    }
}
