package com.comz.store.mapper;

import com.comz.store.entity.Address;
import com.comz.store.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

// @SpringBootTest：表示注解当前的类是一个测试类，不会随项目一起打包
@SpringBootTest
// @RunWith：表示启动这个单元测试类（不启动，则单元测试类是不能运行的），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class CartMapperTests {

    @Autowired(required = false)
    CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(7);
        cart.setPid(10000011);
        cart.setNum(2);
        cart.setPrice(5000L);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1, 5, "admin", new Date());
    }

    @Test
    public void findByUidAndPid() {
        Cart cart = cartMapper.findByUidAndPid(7, 10000011);
        System.out.println(cart);
    }

    @Test
    public void findVOByUid() {
        System.out.println(cartMapper.findVOByUid(8));
    }

    @Test
    public void findByCid() {
        Cart cart = cartMapper.findByCid(4);
        System.out.println(cart);
    }

    @Test
    public void findVOByCid() {
        Integer[] cids = {2, 8, 9, 10};
        System.out.println(cartMapper.findVOByCid(cids));
    }





}
