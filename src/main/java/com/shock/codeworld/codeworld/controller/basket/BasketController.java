package com.shock.codeworld.codeworld.controller.basket;

import com.shock.codeworld.codeworld.controller.product.ResponseProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService service;


    @CrossOrigin
    @GetMapping("/basket-for-user")
    public List<ResponseBasket> getBasketForUser(@RequestParam Integer id) {
        return service.getAllBasketByIdUser(id);
    }


}
