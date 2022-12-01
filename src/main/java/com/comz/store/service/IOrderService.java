package com.comz.store.service;

import com.comz.store.entity.Order;

public interface IOrderService {
    Order creat(Integer aid, Integer uid, String username, Integer[] cids);
}
