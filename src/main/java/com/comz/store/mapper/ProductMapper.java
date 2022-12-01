package com.comz.store.mapper;

import com.comz.store.entity.Cart;
import com.comz.store.entity.Product;

import java.util.Date;
import java.util.List;

public interface ProductMapper {
    List<Product> findHotList();

    Product findById(Integer id);
}
