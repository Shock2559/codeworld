package com.shock.codeworld.codeworld.controller.basket;

import com.shock.codeworld.codeworld.entity.Basket;
import com.shock.codeworld.codeworld.entity.OrderBasket;
import com.shock.codeworld.codeworld.repository.BasketRepository;
import com.shock.codeworld.codeworld.repository.OrderBasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    public final BasketRepository basketRepository;
    public final OrderBasketRepository orderBasketRepository;

    public List<ResponseBasket> getAllBasketByIdUser(Integer id) {

        if(id == null) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found data");
        }

        List<Basket> list = basketRepository.my_getBasketByIdUser(id);
        List<ResponseBasket> response = new ArrayList<>();

        for(int i = 0; i < list.size(); i++) {
            response.add(ResponseBasket.builder()
                    .id(list.get(i).getId())
                    .id_user(list.get(i).getUser().getId())
                    .name_user(list.get(i).getUser().getName())
                    .id_subscriptionsBasket(list.get(i).getSubscriptionsBasket().getId())
                    .name_subscriptionsBasket(list.get(i).getSubscriptionsBasket().getName())
                    .id_statusBasket(list.get(i).getStatusBasket().getId())
                    .name_statusBasket(list.get(i).getStatusBasket().getName())
                    .build());
        }

        return response;
    }

    public List<ResponseOrder> getAllOrderByIdBasket(Integer id) {

        if(id == null) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not found data");
        }

        List<OrderBasket> list = orderBasketRepository.my_getOrderByIdBasket(id);
        List<ResponseOrder> response = new ArrayList<>();

        for(int i = 0; i < list.size(); i++) {
            response.add(ResponseOrder.builder()
                    .id(list.get(i).getId())
                    .id_basket(list.get(i).getBasket().getId())
                    .id_product(list.get(i).getProduct().getId())
                    .name_product(list.get(i).getProduct().getName())
                    .date_create(list.get(i).getDate_create())
                    .delivery_date(list.get(i).getDelivery_date())
                    .build());
        }

        return response;
    }

}
