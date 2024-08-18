package com.advaya.fakestoreapi.controllers;

import com.advaya.fakestoreapi.models.Product;
import com.advaya.fakestoreapi.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id)
    {

        return productService.getProductById(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
       return  productService.getAllProducts();
    }
}
