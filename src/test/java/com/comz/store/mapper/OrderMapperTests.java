package com.comz.store.mapper;

import com.comz.store.entity.Cart;
import com.comz.store.entity.Order;
import com.comz.store.entity.OrderItem;
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
public class OrderMapperTests {

    @Autowired(required = false)
    OrderMapper orderMapper;

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setUid(7);
        order.setRecvName("LeeSin");
        order.setRecvPhone("7021475489");
        orderMapper.insertOrder(order);
    }

    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000003);
        orderItem.setTitle("测试插入10000003好商品是否成功");
        orderMapper.insertOrderItem(orderItem);
    }





}
