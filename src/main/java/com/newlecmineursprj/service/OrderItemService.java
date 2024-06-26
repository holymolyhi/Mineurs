package com.newlecmineursprj.service;

import java.util.List;

import com.newlecmineursprj.entity.OrderItem;

public interface OrderItemService {

    List<OrderItem> getList();

    OrderItem getById(Long id);

    void add(OrderItem orderItem);
}
