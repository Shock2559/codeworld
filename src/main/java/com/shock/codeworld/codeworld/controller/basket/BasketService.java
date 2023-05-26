package com.shock.codeworld.codeworld.controller.basket;

import com.shock.codeworld.codeworld.entity.Basket;
import com.shock.codeworld.codeworld.entity.OrderBasket;
import com.shock.codeworld.codeworld.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    public final BasketRepository basketRepository;
    public final OrderBasketRepository orderBasketRepository;
    public final AllStatusBasketRepository allStatusBasketRepository;
    public final AllSubscriptionsBasketRepository allSubscriptionsBasketRepository;
    public final UserDataRepository userDataRepository;
    public final ProductsRepository productsRepository;

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

    public ResponseBasket createBasket(ResponseBasket request) {

        Basket basket = Basket.builder()
                .statusBasket(allStatusBasketRepository.getStatusBasketById(request.getId_statusBasket()))
                .subscriptionsBasket(allSubscriptionsBasketRepository.getSubscriptionsBasketById(request.getId_subscriptionsBasket()))
                .user(userDataRepository.my_getUserDataById(request.getId_user()))
                .build();

        basketRepository.save(basket);

        return ResponseBasket.builder()
                .id(basket.getId())
                .id_user(basket.getUser().getId())
                .name_user(basket.getUser().getName())
                .id_subscriptionsBasket(basket.getSubscriptionsBasket().getId())
                .name_subscriptionsBasket(basket.getSubscriptionsBasket().getName())
                .id_statusBasket(basket.getStatusBasket().getId())
                .name_statusBasket(basket.getStatusBasket().getName())
                .build();
    }

    public ResponseOrder createOrder(ResponseOrder request) {

        OrderBasket orderBasket = OrderBasket.builder()
                .basket(basketRepository.my_getBasketById(request.getId_basket()))
                .date_create(new Date())
                .delivery_date(new Date())
                .product(productsRepository.my_getProductById(request.getId_product()))
                .build();

        orderBasketRepository.save(orderBasket);

        return ResponseOrder.builder()
                .id(orderBasket.getId())
                .id_basket(orderBasket.getBasket().getId())
                .id_product(orderBasket.getProduct().getId())
                .name_product(orderBasket.getProduct().getName())
                .date_create(orderBasket.getDate_create())
                .delivery_date(orderBasket.getDelivery_date())
                .build();
    }

    public ResponseBasket updateBasket(ResponseBasket request) {

        Basket basket = basketRepository.my_getBasketById(request.getId());

        basket.setStatusBasket(allStatusBasketRepository.getStatusBasketById(request.getId_statusBasket()));

        if(request.getId_subscriptionsBasket() != null) {
            basket.setSubscriptionsBasket(allSubscriptionsBasketRepository.getSubscriptionsBasketById(request.getId_subscriptionsBasket()));
        }

        basketRepository.save(basket);


        return ResponseBasket.builder()
                .id(basket.getId())
                .id_user(basket.getUser().getId())
                .name_user(basket.getUser().getName())
                .id_subscriptionsBasket(basket.getSubscriptionsBasket().getId())
                .name_subscriptionsBasket(basket.getSubscriptionsBasket().getName())
                .id_statusBasket(basket.getStatusBasket().getId())
                .name_statusBasket(basket.getStatusBasket().getName())
                .build();
    }

    public ResponseOrder deleteOrder(Integer id) {

        OrderBasket orderBasket = orderBasketRepository.my_getOrderById(id);
        orderBasketRepository.delete(orderBasket);

        return ResponseOrder.builder().build();
    }

    public ResponseBasket deleteBaskit(Integer id) {

        List<OrderBasket> list = orderBasketRepository.my_getOrderByIdBasket(id);

        for(int i = list.size() - 1; i > -1; i--) {
            orderBasketRepository.delete(list.get(i));
        }

        Basket basket = basketRepository.my_getBasketById(id);
        basketRepository.delete(basket);

        return ResponseBasket.builder().build();
    }
}
