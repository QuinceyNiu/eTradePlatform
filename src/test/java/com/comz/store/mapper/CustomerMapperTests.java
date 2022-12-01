package com.comz.store.mapper;

import com.comz.store.entity.Customer;
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
public class CustomerMapperTests {

    @Autowired(required = false)
    CustomerMapper customerMapper;

    @Test
    public void insert() {
        Customer customer = new Customer();
        customer.setUsername("IU");
        customer.setPassword("123456");
        Integer row = customerMapper.insert(customer);
        System.out.println(row);
    }

    @Test
    public void findByUsername() {
        String s = new String("IU");
        Customer customer = customerMapper.findByUsername(s);
        System.out.println(customer);
    }

    @Test
    public void updatePasswordByUid() {
        customerMapper.updatePasswordByUid(4, "321", "admin", new Date());
    }

    @Test
    public void findByUid() {
        System.out.println(customerMapper.findByUid(4));
    }

    @Test
    public void updateByUid() {
        customerMapper.updateByUid(7, "7029723461", "xir16@pitt.edu", "管理员", new Date());
    }

    @Test
    public void updateAvatarByUid() {
        customerMapper.updateAvatarByUid(7, "文件路径", "管理员", new Date());
    }
}
