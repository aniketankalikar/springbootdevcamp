package com.advaya.fakestoreapi.services;

import com.advaya.fakestoreapi.dtos.FakeStoreProductDTO;
import com.advaya.fakestoreapi.models.Category;
import com.advaya.fakestoreapi.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeProductServiceImpl implements ProductService{

    private RestTemplate restTemplate;

    public FakeProductServiceImpl(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDTO fakeStoreProductDTO = this.restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDTO.class);

        if(fakeStoreProductDTO == null)
            return null;

        Product product = convertDTOToEntity(fakeStoreProductDTO);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    private Product convertDTOToEntity(FakeStoreProductDTO fakeStoreProductDTO)
    {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setDesc(fakeStoreProductDTO.getDescription());
        product.setPrice(Double.valueOf(fakeStoreProductDTO.getPrice()) );
        product.setImage(fakeStoreProductDTO.getImage());

        Category category = new Category();
        category.setDesc(fakeStoreProductDTO.getCategory());

        product.setCategory(category);

        return product;
    }
}
