package com.comz.store.controller;

import com.comz.store.entity.State;
import com.comz.store.service.IStateService;
import com.comz.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("states")
@RestController
public class StateController extends BaseController{
    @Autowired
    private IStateService stateService;

    @RequestMapping({"/", ""})
    public JsonResult<List<State>> getAll() {
        List<State> data = stateService.getAll_();
        return new JsonResult<>(OK, data);
    }
}
