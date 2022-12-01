package com.comz.store.mapper;

import com.comz.store.entity.State;

import java.util.List;

public interface StateMapper {

    /**
     * 查询所有的州的信息
     * @return 返回所有州的名字
     */
    List<State> findAll();
}
