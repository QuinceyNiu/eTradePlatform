package com.comz.store.mapper;

import com.comz.store.entity.Cart;
import com.comz.store.entity.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

// @SpringBootTest：表示注解当前的类是一个测试类，不会随项目一起打包
@SpringBootTest
// @RunWith：表示启动这个单元测试类（不启动，则单元测试类是不能运行的），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class StateMapperTests {

    @Autowired(required = false)
    StateMapper stateMapper;

    @Test
    public void findAll() {
        List<State> list = stateMapper.findAll();
        for (State state : list) {
            System.out.println(state);
        }

    }




}
