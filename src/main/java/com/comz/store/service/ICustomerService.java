package com.comz.store.service;

import com.comz.store.entity.Customer;

public interface ICustomerService {

    /**
     * 用户注册方法
     * @param customer 用户数据对象
     */
    void reg(Customer customer);

    /**
     * 用户登录功能
     * @param username 用户登录名
     * @param password 用户登录密码
     * @return 返回当前匹配的用户数据，如果没有则返回null
     */
    Customer login(String username, String password);

    /**
     * 用户修改密码功能
     * @param uid 用户的id
     * @param username 用户的名字
     * @param oldPassword 用户的旧密码
     * @param newPassword 用户设置的新密码
     */
    void changePassword(Integer uid, String username,
                        String oldPassword, String newPassword);

    /**
     * 根据用户的id查询用户的数据
     * @param uid 用户的id
     * @return 返回用户的数据
     */
    Customer getByUid(Integer uid);

    /**
     * 更新用户的数据
     * @param uid 用户的id
     * @param username 用户的名称
     * @param customer 用户对象的数据
     */
    void changeInfo(Integer uid, String username, Customer customer);

    /**
     * 修改用户头像
     * @param uid 用户的id
     * @param avatar 用户头像的路径
     * @param username 用户的名称
     */
    void changeAvatar(Integer uid, String avatar, String username);
}
