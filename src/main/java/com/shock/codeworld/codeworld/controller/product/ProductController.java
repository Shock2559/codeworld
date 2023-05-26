package com.shock.codeworld.codeworld.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService service;

    @CrossOrigin
    @PostMapping("/add-product")
    public ResponseProduct addProduct(@RequestBody ResponseProduct request) {
        return service.addProduct(request);
    }

}
