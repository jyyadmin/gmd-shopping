package com.springcloud.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.OrderDetail;

/**
 * 订单明细模块的模型层，用于定义订单明细模块的方法
 * 
 * @author jyy
 *
 */
public interface OrderDetailService {

	/**
	 * 查询指定订单编号的订单明细信息
	 * 
	 * @param orderId  订单编号
	 * @param pageNumber  页数
	 * @return  返回com.github.pagehelper.PageInfo<OrderDetail>类型实例
	 */
	public abstract PageInfo<OrderDetail> selectByOrderId(Integer orderId,Integer pageNumber);
	
	/**
	 * 向购物车中添加订单明细信息
	 * 
	 * @param userId   用户编号
	 * @param ordeetail  订单明细
	 * @return  添加成功返回true，否则返回false
	 */
	public abstract boolean addShopping(Integer userId,OrderDetail orderDetail);
	
	/**
	 * 查询指定用户的购物车信息
	 * 
	 * @param userId  用户编号
	 * @return  成功返回java.utils.List实体，否则返回null
	 */
	public abstract List<OrderDetail> selectShopping(Integer userId);
	
	/**
	 * 删除指定用户购物车中的商品信息
	 * 
	 * @param userId  用户编号
	 * @param orderDetail  订单明细
	 * @return  成功返回true,否则返回false
	 */
	public abstract boolean removerShopping(Integer userId,OrderDetail orderDetail);
	
}
