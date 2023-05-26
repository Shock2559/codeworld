package com.shock.codeworld.codeworld.controller.other;

import com.shock.codeworld.codeworld.entity.CategoryProduct;
import com.shock.codeworld.codeworld.repository.CategoryProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/other")
@RequiredArgsConstructor
public class OtherController {

    private final CategoryProductRepository categoryProductRepository;
    private final OtherService service;

    @CrossOrigin
    @GetMapping("/all-category")
    public List<CategoryProduct> getAllCategory() {
        return categoryProductRepository.getAllCategoryProduct();
    }

    @CrossOrigin
    @GetMapping("/category-for-parent")
    public List<CategoryProductResponse> getCategoryForParent(@RequestParam(required = false) Integer id) {

        if(id == null) {
            List<CategoryProduct> list = categoryProductRepository.getDefCategory();

            List<CategoryProductResponse> responses = new ArrayList<>();

            for(int i = 0; i < list.size(); i++) {
                responses.add(CategoryProductResponse.builder().id(list.get(i).getId()).name(list.get(i).getName()).build());
            }

            return responses;
        }

        return service.getCategoryProductForParent(id);
    }

    @CrossOrigin
    @GetMapping("/inventory-for-product")
    public List<InventoryProductResponse> getInventoryForProduct(@RequestParam(required = false) Integer id) {
        if(id == null) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "id is null");
        }

        return service.getInventoryByProduct(id);
    }

    @CrossOrigin
    @GetMapping("/get-all-status-basket")
    public List<AllStatusBasketResponse> getAllStatusBasket() {
        return service.getAllStatusBasket();
    }

    @CrossOrigin
    @GetMapping("/get-all-subscriptions-basket")
    public List<AllSubscriptionsBasketResponse> getAllSubscriptionsBasket() {
        return service.getAllSubscriptionsBasket();
    }



}
