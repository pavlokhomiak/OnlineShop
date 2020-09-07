package com.internet.shop.dao;

import com.internet.shop.models.Order;
import com.internet.shop.models.ShoppingCart;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Order create(Order order);

    Optional<Order> getById(Long id);

    List<Order> getUsersOrders(Long userId);

    List<Order> getAll();

    boolean deleteById(Long id);

    boolean delete(Order order);
}
