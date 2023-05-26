package com.shock.codeworld.codeworld.controller.product;

import com.shock.codeworld.codeworld.entity.Products;
import com.shock.codeworld.codeworld.repository.CategoryProductRepository;
import com.shock.codeworld.codeworld.repository.ProductsRepository;
import com.shock.codeworld.codeworld.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;
    private final UserDataRepository userDataRepository;
    private final CategoryProductRepository categoryProductRepository;

    public ResponseProduct addProduct(ResponseProduct request) {

        if(request.getId_category() == 0 || request.getId_user() == 0
        || request.getName() == null || request.getCost() == null
        || request.getDescription() == null) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found data");
        }

        Products products = Products.builder()
                .categoryProduct(categoryProductRepository.my_getCategoryProductById(request.getId_category()))
                .userData(userDataRepository.my_getUserDataById(request.getId_user()))
                .name(request.getName())
                .description(request.getDescription())
                .photo(request.getPhoto())
                .cost(request.getCost())
                .build();

        productsRepository.save(products);


        return ResponseProduct.builder()
                .id(products.getId())
                .categoryName(products.getCategoryProduct().getName())
                .name(products.getName())
                .cost(products.getCost())
                .description(products.getDescription())
                .photo(products.getPhoto())
                .cost(products.getCost())
                .build();
    }

}
