package com.shock.codeworld.codeworld.controller.product;

import com.shock.codeworld.codeworld.entity.Products;
import com.shock.codeworld.codeworld.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @CrossOrigin
    @GetMapping("/get-all-products")
    public List<ResponseProduct> getAllProducts(@RequestParam Integer id) {
        return service.getAllProducts(id);
    }

}
