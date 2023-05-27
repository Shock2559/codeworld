package com.shock.codeworld.codeworld.controller.product;

import com.shock.codeworld.codeworld.entity.CategoryProduct;
import com.shock.codeworld.codeworld.entity.Products;
import com.shock.codeworld.codeworld.repository.CategoryProductRepository;
import com.shock.codeworld.codeworld.repository.ProductsRepository;
import com.shock.codeworld.codeworld.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
            System.out.println("test");
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

    public List<ResponseProduct> getAllProducts() {
        List<Products> products = new ArrayList<>();
        products = productsRepository.my_getAllProduct();

        List<ResponseProduct> response = new ArrayList<>();


        for (int i = 0; i < products.size(); i++) {
            response.add(ResponseProduct.builder()
                    .id(products.get(i).getId())
                    .id_user(products.get(i).getUserData().getId())
                    .id_category(products.get(i).getCategoryProduct().getId())
                    .categoryName(products.get(i).getCategoryProduct().getName())
                    .name(products.get(i).getName())
                    .description(products.get(i).getDescription())
                    .cost(products.get(i).getCost())
                    .photo(products.get(i).getPhoto())
                    .build());
        }

        return response;
    }

    public List<ResponseProduct> getAllProductsById(Integer id, Integer id_category) {

        if(id == null) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found data");
        }

        List<Products> products = new ArrayList<>();
        List<ResponseProduct> response;

        if(id_category == null) {
            products = productsRepository.getAllProducts(id);

            response = new ArrayList<>();
        } else {
            CategoryProduct category = categoryProductRepository.my_getCategoryProductById(id_category);
            System.out.println(category);
            products = productsRepository.my_getAllProductByCategory(id, category);

            response = new ArrayList<>();
        }



        for (int i = 0; i < products.size(); i++) {
            response.add(ResponseProduct.builder()
                    .id(products.get(i).getId())
                    .id_user(products.get(i).getUserData().getId())
                    .id_category((products.get(i).getCategoryProduct() == null) ? null : products.get(i).getCategoryProduct().getId())
                    .categoryName(products.get(i).getCategoryProduct().getName())
                    .name(products.get(i).getName())
                    .description(products.get(i).getDescription())
                    .cost(products.get(i).getCost())
                    .photo(products.get(i).getPhoto())
                    .build());
        }

        return response;

    }
    public ResponseProduct updateProduct(ResponseProduct request) {

        if(request.getId() == null || request.getId_category() == 0 || request.getId_user() == 0
                || request.getName() == null || request.getCost() == null
                || request.getDescription() == null) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found data");
        }

        Products products = productsRepository.my_getProductById(request.getId());

        products.setCategoryProduct(categoryProductRepository.my_getCategoryProductById(request.getId_category()));
        products.setUserData(userDataRepository.my_getUserDataById(request.getId_user()));
        products.setName(request.getName());
        products.setDescription(request.getDescription());
        products.setPhoto(request.getPhoto());
        products.setCost(request.getCost());

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

    public void deleteProduct(Integer id) {

        Products products = productsRepository.my_getProductById(id);
        productsRepository.delete(products);

    }
}
