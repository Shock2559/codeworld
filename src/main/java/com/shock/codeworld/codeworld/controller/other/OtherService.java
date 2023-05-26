package com.shock.codeworld.codeworld.controller.other;

import com.shock.codeworld.codeworld.entity.CategoryProduct;
import com.shock.codeworld.codeworld.entity.InventoryProduct;
import com.shock.codeworld.codeworld.repository.CategoryProductRepository;
import com.shock.codeworld.codeworld.repository.InventoryProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OtherService {

    private final CategoryProductRepository categoryProductRepository;
    private final InventoryProductRepository inventoryProductRepository;

    public List<CategoryProductResponse> getCategoryProductForParent(int id) {
        List<CategoryProduct> list = categoryProductRepository.getCategoryProductForParent(id);

        List<CategoryProductResponse> responses = new ArrayList<>();

        for(int i = 0; i < list.size(); i++) {
            responses.add(CategoryProductResponse.builder().id(list.get(i).getId()).name(list.get(i).getName()).build());
        }

        return responses;
    }

    public List<InventoryProductResponse> getInventoryByProduct(Integer id) {

        List<InventoryProduct> list = inventoryProductRepository.getInventoryProductForIdProduct(id);
        List<InventoryProductResponse> response = new ArrayList<>();

        for(int i = 0; i < list.size(); i++) {
            response.add(InventoryProductResponse.builder()
                    .dateCreate(list.get(i).getDateCreate())
                    .products(list.get(i).getProducts().getCategoryProduct().getName())
                    .stateProduct(list.get(i).getStateProduct().getName())
                    .structuralDivision(list.get(i).getStructuralDivision().getName())
                    .build());
        }

        return response;

    }

}
