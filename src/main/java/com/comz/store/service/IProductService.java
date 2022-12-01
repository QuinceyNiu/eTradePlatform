package com.comz.store.service;

import com.comz.store.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findHotList();

    Product findById(Integer id);
}
