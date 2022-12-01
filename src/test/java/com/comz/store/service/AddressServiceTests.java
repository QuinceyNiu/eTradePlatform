package com.comz.store.service;

import com.comz.store.entity.Address;
import com.comz.store.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// @SpringBootTest：表示注解当前的类是一个测试类，不会随项目一起打包
@SpringBootTest
// @RunWith：表示启动这个单元测试类（不启动，则单元测试类是不能运行的），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class AddressServiceTests {

    @Autowired(required = false)
    private IAddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setPhone("7254783589");
        address.setName("Rusty");
        addressService.addNewAddress(2, "admin", address);
    }

    @Test
    public void delete() {
        addressService.delete(8, 7, "admin");
    }


}
