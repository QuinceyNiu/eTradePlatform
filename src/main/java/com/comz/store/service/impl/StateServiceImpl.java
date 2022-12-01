package com.comz.store.service.impl;

import com.comz.store.entity.State;
import com.comz.store.mapper.StateMapper;
import com.comz.store.service.IStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl implements IStateService {
    @Autowired
    private StateMapper stateMapper;


    @Override
    public List<State> getAll_() {
        List<State> list = stateMapper.findAll();
        return list;
    }
}
