package com.comz.store.service;

import com.comz.store.entity.Customer;
import com.comz.store.mapper.CustomerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @SpringBootTest：表示注解当前的类是一个测试类，不会随项目一起打包
@SpringBootTest
// @RunWith：表示启动这个单元测试类（不启动，则单元测试类是不能运行的），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class CustomerServiceTests {

    @Autowired(required = false)
    private ICustomerService customerService;

    @Test
    public void reg() {
        Customer customer = new Customer();
        customer.setUsername("Eason");
        customer.setPassword("1234");
        customerService.reg(customer);
        System.out.println("OK");
    }

    @Test
    public void login() {
        Customer user = customerService.login("Eason", "1234");
        System.out.println(user);
    }

    @Test
    public void changePassword() {
        customerService.changePassword(3, "Eason", "1234", "123");
    }

    @Test
    public void findByUid() {
        System.out.println(customerService.getByUid(6));
    }

    @Test
    public void changeInfo() {
        Customer user = new Customer();
        user.setPhone("158999999");
        user.setEmail("45645@gmail.com");
        customerService.changeInfo(6, "ChangedName", user);
    }

    @Test
    public void changeAvatar() {
        customerService.changeAvatar(7, "/upload/test.png", "MM");
    }

}
