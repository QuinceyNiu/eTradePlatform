package com.comz.store.service.impl;

import com.comz.store.entity.Customer;
import com.comz.store.mapper.CustomerMapper;
import com.comz.store.service.ICustomerService;
import com.comz.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired(required = false)
    private CustomerMapper customerMapper;

    @Override
    public void reg(Customer customer) {
        String username = customer.getUsername();
        Customer result = customerMapper.findByUsername(username);
        if (result != null) {
            throw new UsernameDuplicateException("Username is occupied");
        }

        //md5的实现
        String oldPassword = customer.getPassword();
        //获得盐值
        String salt = UUID.randomUUID().toString().toUpperCase();

        //记录盐值
        customer.setSalt(salt);
        //使用盐值加密密码
        String md5Password = getMD5Password(oldPassword, salt);
        customer.setPassword(md5Password);

        customer.setIsDelete(0);

        //logo
        customer.setCreatedUser(customer.getUsername());
        customer.setModifiedUser(customer.getUsername());
        customer.setCreatedTime(new Date());
        customer.setModifiedTime(new Date());

        Integer rows = customerMapper.insert(customer);
        if (rows != 1) {
            throw new InsertException("Unknown exceptions were generated during user registration");
        } else {
            System.out.println("Insert data successfully");
        }

    }

    @Override
    public Customer login(String username, String password) {
        Customer result = customerMapper.findByUsername(username);
        if (result.getUsername() == null) {
            throw new UsernameNotFoundException("Username does not exist");
        }

        //用户名存在时，检查用户的密码是否匹配
        String salt = result.getSalt();
        String res_password = getMD5Password(password, salt);
        if (!res_password.equals(result.getPassword())) {
            throw new PasswordNotMatchException("Password error");
        }

        //判断是否被删除(is_delete字段是否为1)
        if (result.getIsDelete() == 1) {
            throw new UsernameNotFoundException("User data does not exist");
        }

        // 当所有的验证都通过时,返回当前用户的数据
        return result;

    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        Customer result = customerMapper.findByUid(uid);
        System.out.println(result);
        if (result == null || result.getIsDelete() == 1) {
            throw new UsernameNotFoundException("User data does not exist");
        }

        //输出的密码与数据库中的密码进行比较
        String oldMd5Password = getMD5Password(oldPassword, result.getSalt());

        //
        /*System.out.println("前端传进来的oldPassword = " + oldPassword);
        System.out.println("前端传进来的newPassword = " + newPassword);
        System.out.println("oldMd5Password = " + oldMd5Password);
        System.out.println("用户的原密码 = " + result.getPassword());*/
        //
        if (!oldMd5Password.equals(result.getPassword())) {
            throw new PasswordNotMatchException("Original password error");
        }

        String newMd5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = customerMapper.updatePasswordByUid(uid, newMd5Password, username, new Date());
        if (rows != 1) {
            throw new UpdateException("Unknown exception when updating data");
        }
    }

    @Override
    public Customer getByUid(Integer uid) {
        Customer result = customerMapper.findByUid(uid);
        if (result == null) {
            throw new UsernameNotFoundException("User data does not exist");
        }

        return result;
    }

    @Override
    public void changeInfo(Integer uid, String username, Customer customer) {
        Customer result = customerMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UsernameNotFoundException("User data does not exist");
        }

        //当用户数据存在时
        customer.setUid(uid);
        customer.setModifiedUser(username);
        customer.setModifiedTime(new Date());

        customerMapper.updateByUid(customer.getUid(), customer.getPhone(), customer.getEmail(),
                                    customer.getModifiedUser(), customer.getModifiedTime());
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        Customer result = customerMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UsernameNotFoundException("User data does not exist");
        }
        Integer rows = customerMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows != 1) {
            throw new UpdateException("Unknown exception when updating user avatar");
        }
    }

    /** 自定义一个md5加密算法 */
    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i ++) {
            // Invocation of md5 encryption algorithm (performed three times)
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
