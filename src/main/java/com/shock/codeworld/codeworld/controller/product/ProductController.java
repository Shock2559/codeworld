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
    @PostMapping("/update-product")
    public ResponseProduct updateProduct(@RequestBody ResponseProduct request) {
        return service.updateProduct(request);
    }
    @CrossOrigin
    @GetMapping("/get-products-by-user-id")
    public List<ResponseProduct> getAllProductsById(@RequestParam Integer id,
                                                    @RequestParam(required = false) Integer id_category) {
        return service.getAllProductsById(id, id_category);
    }

    @CrossOrigin
    @GetMapping("/all-products")
    public List<ResponseProduct> getALLProduct() {
        return service.getAllProducts();
    }

    @CrossOrigin
    @GetMapping("/delete-product")
    public Integer deleteProduct(@RequestParam Integer id) {
        service.deleteProduct(id);
        return 1;
    }

}
