package com.comz.store.service.impl;

import com.comz.store.entity.Cart;
import com.comz.store.entity.Product;
import com.comz.store.mapper.CartMapper;
import com.comz.store.mapper.ProductMapper;
import com.comz.store.service.ICartService;
import com.comz.store.service.ex.*;
import com.comz.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        if (result == null) { //购物车中没有信息，进行新增
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            //price通过Product中的pid进行获取
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            //补全日志
            cart.setCreatedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedUser(username);
            cart.setModifiedTime(new Date());
            Integer rows = cartMapper.insert(cart);

            if (rows != 1) {
                throw new InsertException("Unknown error when inserting data");
            }
        } else {
            //当购物车中该产品已经存在时，则进行更新
            Integer num = result.getNum() + amount;
            Integer rows = cartMapper.updateNumByCid(result.getCid(), num, username, new Date());
            if (rows != 1) {
                throw new UpdateException("Unknown error when updating data");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null) {
            throw new CartNotFoundException("数据不存在");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据失败");
        }

        //返回新的购物车数据的总量
        return num;
    }

    @Override
    public Integer subNum(Integer cid, Integer uid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result == null) {
            throw new CartNotFoundException("the data is not exist");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("Illegal data access");
        }
        Integer num = result.getNum() - 1;
        Integer rows;
        if (num < 0) {
            rows = cartMapper.updateNumByCid(cid, 0, username, new Date());
        } else {
            rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        }

        if (rows != 1) {
            throw new UpdateException("Failed to update data");
        }

        //返回新的购物车数据的总量
        return num;
    }

    @Override
    public List<CartVO> getVOByCid(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCid(cids);
        for (CartVO cartVO : list) {
            if (!(cartVO.getUid().equals(uid))) {
                list.remove(cartVO);
            }
        }
        return list;
    }

    @Override
    public void delete(Integer[] cids, Integer uid) {
        List<CartVO> result = cartMapper.findVOByCid(cids);
        if (result == null) {
            throw new CartNotFoundException("Exception for shopping cart data not existing");
        }

        for (CartVO cart : result) {
            if (!cart.getUid().equals(uid)) {
                throw new AccessDeniedException("Illegal data access");
            }
        }

        Integer rows = cartMapper.deleteByCids(cids);
        if (rows < 1) {
            throw new DeleteException("Generate exception when deleting data");
        }
    }
}
