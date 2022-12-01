package com.comz.store.controller;

import com.comz.store.entity.Order;
import com.comz.store.service.IOrderService;
import com.comz.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("orders")
@RestController
public class OrderController extends BaseController{
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        Order data = orderService.creat(aid, getUidFromSession(session), getUsernameFromSession(session), cids);
        return new JsonResult<>(OK, data);
    }

}
