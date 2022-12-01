package com.comz.store.service;

import com.comz.store.entity.Address;

import java.util.List;

public interface IAddressService {
    /**
     * 用于新增收货地址的接口
     * @param uid 用户的id，传入原因：因为需要知道是哪个用户做的新增操作，所以用uid来唯一标识
     * @param username 用户名，传入原因：因为会有modifiedUser 需要记录修改人是谁，所以需要传人
     * @param address 收货地址的详细信息
     */
    void addNewAddress(Integer uid, String username, Address address);

    List<Address> getByUid(Integer uid);

    Address getByAid(Integer aid, Integer uid);

    /**
     * 修改某个用户的某条收货数据为默认地址
     * @param aid 守护地址的id
     * @param uid 用户的id
     * @param username 表示修改执行的人
     */
    void setDefault(Integer aid, Integer uid, String username);


    /**
     * 删除用户选中的收货地址
     * @param aid 收货地址id
     * @param uid 用户id
     * @param username 用户名
     */
    void delete(Integer aid, Integer uid, String username);

    /**
     * 修改用户的收货地址
     * @param address 收货地址的信息
     */
    Integer updateAddress(Address address, String modifiedUser);
}
