package com.comz.store.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class State extends BaseEntity{
    private Integer id;
    private String sta_name;
}
