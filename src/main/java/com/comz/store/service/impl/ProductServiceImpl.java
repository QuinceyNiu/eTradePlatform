package com.comz.store.service.impl;

import com.comz.store.entity.Product;
import com.comz.store.mapper.ProductMapper;
import com.comz.store.service.IProductService;
import com.comz.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        return list;
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("尝试找到的商品不存在");
        }
        return product;
    }

}
