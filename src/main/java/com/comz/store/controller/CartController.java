package com.comz.store.controller;


import com.comz.store.service.ICartService;
import com.comz.store.util.JsonResult;
import com.comz.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("carts")
@RestController
public class CartController extends BaseController{

    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        cartService.addToCart(uid, pid, amount, username);

        return new JsonResult<>(OK);
    }

    @RequestMapping({"","/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        List<CartVO> data = cartService.getVOByUid(getUidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.addNum(cid, getUidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("{cid}/num/sub")
    public JsonResult<Integer> sunNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.subNum(cid, getUidFromSession(session), getUsernameFromSession(session));
        return new JsonResult<>(OK, data);
    }

/*    @RequestMapping("list")
    public JsonResult<List<CartVO>> getVOByCid(Integer cids[], HttpSession session) {
        List<CartVO> data = cartService.getVOByCid(getUidFromSession(session), cids);
        return new JsonResult<>(OK, data);
    }*/

    @RequestMapping("delete")
    public JsonResult<Void> delete(Integer[] cids, HttpSession session) {
        cartService.delete(cids, getUidFromSession(session));
        return new JsonResult<>(OK);
    }

}
