package com.comz.store.mapper;

import com.comz.store.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CustomerMapper {

    /**
     * 插入用户数据
     * @param customer 用户的相关数据
     * @return 受影响的行数(增、删、改都有受影响的行数，通过返回的行数来判断是否执行成功)
     */
    Integer insert(Customer customer);

    /**
     * 根据用户名查询用户的数据
     * @param username 用户名
     * @return 如果找到对应的用户则返回这个用户的数据，如果没有找到则返回null
     */
    Customer findByUsername(String username);

    /**
     * 根据用户的uid来修改用户密码
     * @param uid 用户的id
     * @param password 用户输入的新密码
     * @param modifiedUser 表示修改的执行者
     * @param modifiedTime 表示修改的时间
     * @return 返回被影响的行数
     */
    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户的uid查询用户信息
     * @param uid 用户的id
     * @return 如果找到则返回用户的信息，否则则返回null值
     */
    Customer findByUid(Integer uid);

    /**
     * 根据用户id修改用户的信息
     * @param uid 用户id
     * @param phone 用户的电话号码
     * @param email 用户的邮箱
     * @param modifiedUser 修改的人
     * @param modifiedTime 修改的时间
     * @return
     */
    Integer updateByUid(Integer uid, String phone, String email,
                        String modifiedUser, Date modifiedTime);


    /**
     * @param uid 用户id
     * @param avatar 用户头像图片在操作系统中的路径
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 影响行数
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
