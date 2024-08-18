package com.advaya.fakestoreapi.dtos;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class FakeStoreProductDTO {

    private Long id;
    private String title;

    private String price;

    private String category;

    private String description;

    private String image;

}
