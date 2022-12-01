package com.comz.store.mapper;

import com.comz.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AddressMapper {
    /**
     * 插入用户的收货地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的id统计收货地址的数量
     * @param uid 用户的id
     * @return 当前用户的收货地址的总数量
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户的id 查询用户的收货地址数据
     * @param uid 用户id
     * @return 收货地址数据
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据id查询收货地址数据
     * @param aid 收货地址
     * @return 收货地址数据，如果没有找到则返回null值
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户的id值来修改用户的收货地址，将其设置为非默认
     * @param uid 用户的id值
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer uid);

    Integer updateDefaultByAid(@Param("aid") Integer aid,
                               @Param("modifiedUser") String modifiedUser,
                               @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据aid删除删除对应的收货地址记录
     * @param aid 后货地址的id
     * @return 受影响的行数
     */
    Integer deleteByAid(@Param("aid") Integer aid);

    /**
     * 根据用户uid查询用户最后一次修改的收货记录
     * @param uid 用户的id
     * @return 受影响的行数
     */
    Address findLastModified(Integer uid);

    /**
     * 用户修改收货地址
     * @param address 收货地址的对象（包括name，cityCode，zipCode...）
     * @return 受影响行数
     */
    Integer updateAddressByAid(Address address);
}
