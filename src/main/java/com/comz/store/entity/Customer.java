package com.comz.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity{
        Integer uid;
        String username;
        String password;
        String salt;
        String phone;
        String email;
        Integer gender;
        String avatar;
        Integer isDelete;
}
