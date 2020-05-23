package com.project.miu.service;

import com.project.miu.bean.model.Goods;
import com.project.miu.bean.model.UserBalance;
import com.project.miu.bean.model.UserOrder;
import com.project.miu.commons.util.IdGenerateUtil;
import com.project.miu.dao.GoodsDao;
import com.project.miu.dao.UserBalanceDao;
import com.project.miu.dao.UserOrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @Author: gelj
 * @Date: 2020/5/22 2:41 下午
 *
 * 积分兑换
 */

@Service
public class PointExchangeService {

    @Autowired
    private UserBalanceDao userBalanceDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private UserOrderDao userOrderDao;

    public Integer pointExchange(String userUuid,String goodsUuid) throws Exception {
        //生成订单信息
        UserOrder userOrder = new UserOrder();
        String orderUuid = IdGenerateUtil.getUUID();
        userOrder.setOrderUuid(orderUuid);
        userOrder.setUserUuid(userUuid);
        userOrder.setGoodsUuid(goodsUuid);
        userOrder.setOrderStatus(2);
        userOrderDao.save(userOrder);
        //1、查询用户余额
        UserBalance userBalance = userBalanceDao.findByUserUuidAndDeleteData(userUuid,1);
        long accountBalance;
        if (userBalance !=null){
            accountBalance = userBalance.getUserAccountBalance();
        }else {
            throw new Exception("用户账号异常！");
        }
        //2、查询商品所需积分
        Goods goods = goodsDao.findByGoodsUuidAndDeleteData(goodsUuid,1);
        Long goodsPrice = 0l;
        if (goods !=null){
            goodsPrice = goods.getGoodsPrice();
        }else {
            throw new Exception("商品异常！");
        }
        //3、购买
        if(accountBalance < goodsPrice){//账户余额不足
            //throw new Exception("账户余额不足");
            return 2;//购买失败
        }else{
            UserOrder byOrderUuid = userOrderDao.findByOrderUuid(orderUuid);
            Integer orderStatus = byOrderUuid.getOrderStatus();
            if (orderStatus.equals(2)){
                userBalance.setUserAccountBalance(accountBalance-goodsPrice);
                userBalanceDao.saveAndFlush(userBalance);
                byOrderUuid.setOrderStatus(3);
                userOrderDao.saveAndFlush(byOrderUuid);
                return 1;//购买成功
            }
        }
        return 2;
    }
}
