package com.example.eshop.service;

import com.example.eshop.dao.Productrepository;
import com.example.eshop.model.Product;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

@ApiModel(description = "This is Product Service Class")
@Service
public class ProductService {

    private Productrepository productrepository;

    @Autowired
    public ProductService(Productrepository productrepository)
    {
        this.productrepository = productrepository;
    }

        public Product getOneProduct(int id){

            Optional<Product> product = productrepository.findById(id);
            if (product.isPresent()){
                Product obj = new Product();
                obj.setProductid(product.get().getProductid());
                obj.setProductname(product.get().getProductname());
                obj.setPrice(product.get().getPrice());
                obj.setDescription(product.get().getDescription());
                obj.setQuantity(product.get().getQuantity());
                obj.setCategoryid(product.get().getCategoryid());
                return obj;
            }
            else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product does not exist");
        }
}
