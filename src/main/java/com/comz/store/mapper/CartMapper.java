package com.comz.store.mapper;

import com.comz.store.entity.Cart;
import com.comz.store.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    /**
     * 插入购物车数据(插入数据时，给的参数的Cart类的对象，因为购物车中的那些信息就是Cart类型的)
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insert(Cart cart);

    /**
     * 更新购物车某件商品的数量 （update语句中，一般都要给上modifiedUser、modifiedTime这两个字段，因为是修改表，所以要记录修改的人和时间）
     * @param cid 购物车数据id
     * @param num 更新的数量
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * 根据用用户的id和商品的id来查询购物车中的数据（查询的时候，一般返回要查询的那个数据的类型）
     * @param uid
     * @param pid
     * @return
     */
    Cart findByUidAndPid(Integer uid, Integer pid);

    List<CartVO> findVOByUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVO> findVOByCid(Integer[] cids);

    Integer deleteByCids(Integer[] cids);
}
