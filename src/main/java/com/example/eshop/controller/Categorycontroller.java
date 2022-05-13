package com.example.eshop.controller;

import com.example.eshop.service.CategoryService;
import com.example.eshop.dao.Categoryrepository;
import com.example.eshop.model.Category;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("eshop/")
public class Categorycontroller {

    private CategoryService categoryService;

    @Autowired
    Categoryrepository crepo;

    @Autowired
    public Categorycontroller(CategoryService categoryService)    {
        this.categoryService = categoryService;
    }


    @ApiOperation(value = "Returns Category list",notes = "It will return list with all available category")
    @GetMapping(value = "categorylist", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getcategoryList(Model model)
    {
        List<Category> result = categoryService.getAllCategory();
        model.addAttribute("categorys",result);
        return "/category_list";
    }


    @ApiOperation(value = "Returns a category by CategoryId",notes = "Provide a CategoryId to look up specific category details")
    @GetMapping(value = "category/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getcategory(@Valid @PathVariable("id") int id,Model model)
    {
        Category category = categoryService.getonecategory(id);
        model.addAttribute("obj",category);
        return "category_detail";
    }


    @ApiOperation(value = "Add new Category form",notes = "This will call a page cointaining form to add new Category")
    @GetMapping(value = "category/add")
        public ModelAndView addcateg(Model model)
        {
            ModelAndView mv = new ModelAndView("category_add");
            mv.addObject("category",new Category());
            return mv;
        }


    @ApiOperation(value = "Add new category to database",notes = "This method will add new category to database")
    @PostMapping(value = "category")
    public String addnewcategory(@Valid Category category)
    {
        Optional<Category> cat = crepo.findById(category.getCategoryid());
        if (cat.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Category already Exist");
        else
        {
            Category newCategory = crepo.save(category);
//            URI location = ServletUriComponentsBuilder
//                    .fromCurrentRequest().path("/{id}")
//                    .buildAndExpand(newCategory.getCategoryid())
//                    .toUri();
////            return ResponseEntity.created(location).build();
            return "redirect:/eshop/category/"+newCategory.getCategoryid();
        }
    }

    @ApiOperation(value = "Update Category form",notes = "This will call a page cointaining form with existing data to update")
    @GetMapping(value = "category/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String editcategory(@Valid @PathVariable("id") int id,Model model)
    {
        Category category = categoryService.getonecategory(id);
        model.addAttribute("obj",category);
        return "category_update";
    }


    @ApiOperation(value = "Update Category",notes = "This will add updated Category details to database")
    @PostMapping(value = "category/update")
    public String updatecategory(@Valid Category updateddetails)
    {
        Category category = categoryService.getonecategory(updateddetails.getCategoryid());
        category.setCategoryname(updateddetails.getCategoryname());
        crepo.save(category);
        return "redirect:/eshop/category/"+updateddetails.getCategoryid();
    }


    @ApiOperation(value = "Delete Category",notes = "This will delete Category details from database")
    @GetMapping(value = "category/delete/{id}")
    public String deletecategory(@PathVariable("id") int id)
    {
        Category category = categoryService.getonecategory(id);
        crepo.deleteById(id);
        return "redirect:/eshop/categorylist";
    }
}
