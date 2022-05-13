package com.example.eshop.controller;

import com.example.eshop.dao.Productrepository;
import com.example.eshop.model.Product;
import com.example.eshop.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
public class Productcontroller {

    private ProductService productService;

    @Autowired
    Productrepository prepo;

    @Autowired
    public Productcontroller(ProductService productService)
    {
        this.productService = productService;
    }

    @ApiOperation(value = "Returns Products list",notes = "It will return list with all available Products")
    @GetMapping(value = "products", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getproducts(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int pagesize,
                              @RequestParam(defaultValue = "productid") String sortproperty,
                              @RequestParam(value = "name_id",defaultValue = "") String nid)
    {
        if (nid.isEmpty()) {
            List<Product> result = prepo.findAllBy(PageRequest.of(page, pagesize, Sort.Direction.ASC, sortproperty));
            model.addAttribute("products", result);
            model.addAttribute("pageSize", pagesize);
            model.addAttribute("currentPage", page);
            return "product_list";
        }
        else {
            System.out.println("first line: "+nid);
            Product result = prepo.searchProduct(nid);
            if(result == null)
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product you are searching for does Not Exist");
            else{
                model.addAttribute("obj",result);
                System.out.println(result);
                return "product_detail";
            }
        }
    }

    @ApiOperation(value = "Returns a product by ProductId",notes = "Provide a ProductId to look up specific product details")
    @GetMapping(value = "product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getproduct(@PathVariable("id") int id,Model model)
    {
        Product product = productService.getOneProduct(id);
        model.addAttribute("obj",product);
        return "product_detail";
    }


    @ApiOperation(value = "Add new Product form",notes = "This will call a page cointaining form to add new Product")
    @GetMapping(value = "product/add")
    public ModelAndView addnewproduct(Model model)
    {
        ModelAndView mv = new ModelAndView("product_add");
        mv.addObject("product",new Product());
        return mv;
    }


    @ApiOperation(value = "Add a new product to database",notes = "This method will add new product to database")
    @PostMapping(value = "product")
    public String addproduct(@Valid Product product)
    {
        Optional<Product> pr = prepo.findById(product.getProductid());
        if (pr.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Product already Exist");
        else {
                Product newProduct = prepo.save(product);
//                URI location = ServletUriComponentsBuilder
//                                .fromCurrentRequest().path("/{id}")
//                                .buildAndExpand(newProduct.getProductid())
//                                .toUri();
//                return ResponseEntity.created(location).build();
            return "redirect:/eshop/product/"+newProduct.getProductid();
        }
    }


    @ApiOperation(value = "Update Product form",notes = "This will call a page cointaining form with existing data to update")
    @GetMapping(value = "product/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String editproduct(@Valid @PathVariable("id") int id,Model model)
    {
        Product product = productService.getOneProduct(id);
        model.addAttribute("obj",product);
        return "product_update";
    }


    @ApiOperation(value = "Update product",notes = "This will add updated Product details to database")
    @PostMapping(value = "product/update")
    public String updateproduct(@Valid Product updateddetails)
    {
        Product product = productService.getOneProduct(updateddetails.getProductid());
        product.setProductname(updateddetails.getProductname());
        product.setPrice(updateddetails.getPrice());
        product.setDescription(updateddetails.getDescription());
        product.setQuantity(updateddetails.getQuantity());
        product.setCategoryid(updateddetails.getCategoryid());
        prepo.save(product);

        return "redirect:/eshop/product/"+updateddetails.getProductid();
    }


    @ApiOperation(value = "Delete a product",notes = "This will delete Product details from database")
    @GetMapping("product/delete/{id}")
    public String deleteproduct(@PathVariable("id") int id)
    {
        Product product = productService.getOneProduct(id);
        prepo.deleteById(id);
        return "redirect:/eshop/products";
    }

}
