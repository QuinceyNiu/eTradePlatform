package com.comz.store.service.impl;

import com.comz.store.entity.Address;
import com.comz.store.entity.Order;
import com.comz.store.entity.OrderItem;
import com.comz.store.mapper.OrderMapper;
import com.comz.store.service.IAddressService;
import com.comz.store.service.ICartService;
import com.comz.store.service.IOrderService;
import com.comz.store.service.ex.InsertException;
import com.comz.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;


    @Override
    public Order creat(Integer aid, Integer uid, String username, Integer[] cids) {
        //需要下单的列表
        List<CartVO> list = cartService.getVOByCid(uid, cids);
        //计算产品的总价
        Long totalPrice = 0L;
        for (CartVO c : list) {
            totalPrice += c.getRealPrice() * c.getNum();
        }
        Address address = addressService.getByAid(aid, uid);
        Order order = new Order();

        //收货地址
        order.setUid(address.getUid());
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvState(address.getStateName());
        order.setRecvCity(address.getCityName());
        order.setRecvAddress(address.getAddress());
        //支付状态、总价、时间
        order.setStatus(0);
        order.setTotalPrice(totalPrice);
        order.setOrderTime(new Date());
        //日志
        order.setCreatedUser(username);
        order.setCreatedTime(new Date());
        order.setModifiedTime(new Date());
        order.setModifiedUser(username);

        //插数据
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new InsertException("插入数据异常");
        }

        //创建订单详细项(OrderItem)的数据
        for (CartVO cartVO : list) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(cartVO.getPid());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setNum(cartVO.getNum());
            //日志
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            order.setModifiedUser(username);
            order.setModifiedTime(new Date());

            //插数据
            rows = orderMapper.insertOrderItem(orderItem);
            if (rows != 1) {
                throw new InsertException("插入数据异常");
            }
        }

        return order;
    }
}
