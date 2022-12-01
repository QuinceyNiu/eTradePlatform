package com.comz.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address extends BaseEntity{
    Integer aid;
    Integer uid;
    String name;
    String stateName;
    String cityName;
    String zip;
    String address;
    String phone;
    String tag;
    Integer isDefault;

}
