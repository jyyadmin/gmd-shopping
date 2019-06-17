package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.OrdersMapper;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;

/**
 * 订单模块模型层实现类，用于实现订单模块的方法
 * 
 * @author jyy
 *
 */
@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private OrdersMapper ordersMapper;
	
	@Override
	public PageInfo<Orders> selectOrders(Orders orders, Integer pageNumber) {
		
		if(orders.getUser()!=null) {
			orders.getUser().setUserName("%" + orders.getUser().getUserName() + "%");
		}

		PageHelper.startPage(pageNumber+1,PageUtils.PAGE_ROW_COUNT);
		List<Orders> selectOrders = this.ordersMapper.selectOrders(orders);
		return new PageInfo<>(selectOrders);
	}

	@Transactional
	@Override
	public Integer updateOrdersStatus(Orders orders) {
		
		return this.ordersMapper.updateOrdersStatus(orders);//调用持久化
	}

	@Override
	public List<Orders> selectGroup(Orders orders) {
		
		return this.ordersMapper.selectGroup(orders);
	}
	
}
