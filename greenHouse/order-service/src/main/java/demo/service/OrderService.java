package demo.service;


import java.util.List;

import demo.model.Order;

public interface OrderService {
    Order createOrder(Order order);
    List<Order> findAll();
}
