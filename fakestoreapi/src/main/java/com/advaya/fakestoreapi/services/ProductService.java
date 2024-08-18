package com.advaya.fakestoreapi.services;

import com.advaya.fakestoreapi.models.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(Long id);
    public List<Product> getAllProducts();

}
