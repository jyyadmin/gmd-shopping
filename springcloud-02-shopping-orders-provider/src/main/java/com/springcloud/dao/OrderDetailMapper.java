package com.springcloud.dao;

import com.springcloud.entity.OrderDetail;
import com.springcloud.entity.Orders;

import java.util.List;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer orderDetailId);

    int insert(OrderDetail record);

    OrderDetail selectByPrimaryKey(Integer orderDetailId);

    List<OrderDetail> selectAll();

    int updateByPrimaryKey(OrderDetail record);
    
    /**
     * 查询指定订单编号的订单信息
     * 
     * @param orderId  订单编号
     * @return   成功返回java.util.list类型实例，否则返回null
     */
    public abstract List<OrderDetail> selectByOrderId(Integer orderId);
    
    /**
     * 批量添加订单明细
     * 
     * @param orders  一组订单明细
     * @return  成功返回大于0的整数，否者返回小于等于0的整数
     */
    public abstract Integer insertOrderDetail(Orders orders);
}