package com.comz.store.mapper;

import com.comz.store.entity.Address;
import com.comz.store.entity.Customer;
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
public class AddressMapperTests {

    @Autowired(required = false)
    AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(1);
        address.setPhone("123456789");
        address.setName("LJR");
        addressMapper.insert(address);
    }

    @Test
    public void countByUid() {
        Integer cnt = addressMapper.countByUid(1);
        System.out.println(cnt);
    }

    @Test
    public void findByUid() {
        List<Address> list = addressMapper.findByUid(7);
        for (Address a : list) {
            System.out.println(a);
        }
    }

    @Test
    public void findByAid() {
        Address list = addressMapper.findByAid(7);
        System.out.println(list);
    }

    @Test
    public void updateNonDefault() {
        addressMapper.updateNonDefault(7);
    }

    @Test
    public void updateDefaultByAid() {
        addressMapper.updateDefaultByAid(8, "rxy", new Date());
    }

    @Test
    public void deleteByAid() {
        addressMapper.deleteByAid(7);
    }

    @Test
    public void findLastModified() {
        Address address = addressMapper.findLastModified(7);
        System.out.println(address);
    }



}
