package com.comz.store.service.impl;

import com.comz.store.entity.Address;
import com.comz.store.mapper.AddressMapper;
import com.comz.store.service.IAddressService;
import com.comz.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    /* 在该方法中，首先需要统计count的数量，当count的数量没有超过上限时，再继续进行接下来的address.setXxx的插入操作
     *  当前面的判断都没有问题的时候，再进行 插入收货地址的操作
     *  */
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //调用收货地址的统计方法
        Integer count = addressMapper.countByUid(uid);
        if (count > maxCount) {
            throw new AddressCountLimitException("User shipping address exceeds limit");
        }

        //uid, idDefault
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0; //1表示默认，0表示不是默认
        address.setIsDefault(isDefault);

        //补全四项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        // 插入收货地址的方法
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("Inserting a user's shipping address generates an unknown exception");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        return list;
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("Receiving address data does not exist");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("Illegal data access");
        }
        return address;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("Receiving address does not exist");
        }

        //检查当前获取到的收货地址是否属于当前用户
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("Illegal data access");
        }
        //先将所有的收货地址设置为非默认
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1) {
            throw new UpdateException("Unknown data exceptions generated during update");
        }

        //将用户选中的某条地址设置为默认收货地址
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (rows != 1) {
            throw new UpdateException("Unknown exception generated when updating data");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("Receiving address data does not exist");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("Illegal data access");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if (rows != 1) {
            throw new DeleteException("Unknown exception when deleting data");
        }

        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            //直接终止程序
            return;
        }
        if (result.getIsDefault() == 1) {
            //将这条数据中的is_default字符设置为1
            Address address = addressMapper.findLastModified(uid);
            rows = addressMapper.updateDefaultByAid(address.getAid(), username, new Date());
        }
    }

    @Override
    public Integer updateAddress(Address address, String modifiedUser) {
        //获取并设置三个地址为null的字段


        //补全表中没有的其他字段
        address.setModifiedUser(modifiedUser);
        address.setModifiedTime(new Date());
        int result = addressMapper.updateAddressByAid(address);

        return result;
    }
}
