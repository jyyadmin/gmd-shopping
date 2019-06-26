package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.OrderDetailMapper;
import com.springcloud.dao.OrdersMapper;
import com.springcloud.entity.OrderDetail;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;

/**
 * 订单模块模型层实现类，用于实现订单模块的方法
 * 
 * @author jyy
 *
 */
@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersMapper ordersMapper;

	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@Override
	public PageInfo<Orders> selectOrders(Orders orders, Integer pageNumber) {

		if (orders.getUser() != null) {
			orders.getUser().setUserName("%" + orders.getUser().getUserName() + "%");
		}

		PageHelper.startPage(pageNumber + 1, PageUtils.PAGE_ROW_COUNT);
		List<Orders> selectOrders = this.ordersMapper.selectOrders(orders);
		return new PageInfo<>(selectOrders);
	}

	@Transactional
	@Override
	public Integer updateOrdersStatus(Orders orders) {

		return this.ordersMapper.updateOrdersStatus(orders);// 调用持久化
	}

	@Override
	public List<Orders> selectGroup(Orders orders) {

		return this.ordersMapper.selectGroup(orders);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public boolean insert(Orders orders) {
		// 添加订单信息
		int orderId = this.ordersMapper.insert(orders);
		// 如果订单信息添加成功，才能添加订单明细信息
		if (orderId > 0) {
			Integer orderDetail = this.orderDetailMapper.insertOrderDetail(orders);
			if (orderDetail > 0) {
				// 将redis中指定用户购物车的信息删除
				String key = "user" + orders.getUserId();
				ListOperations<String, OrderDetail> opsForList = this.redisTemplate.opsForList();

				// 获得用户在redis中所有的购物车信息
				List<OrderDetail> range = opsForList.range(key, 0, -1);

				// 获得用户需要结算购物车信息
				List<OrderDetail> orderDetailList = orders.getOrderDetailList();

				// 结算购物信息
				if (range.size() == orderDetailList.size()) {
					// 当两个集合中的长度相等，表示用户对购物车中所有商品进行了结算
					Boolean delete = this.redisTemplate.delete(key);
					if (delete) {
						return true;
					}
				} else {
					// 当两个集合中的长度不相等时，从所有购物车中删除已结算的购物信息
					for (OrderDetail o : orderDetailList) {
						int indexOf = range.indexOf(o);
						if (indexOf != -1) {
							range.remove(indexOf);
						}
					}
					this.redisTemplate.delete(key);
					for (OrderDetail o1 : range) {
						opsForList.rightPush(key, o1);
					}
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public List<Orders> selectByUserId(Integer userId) {
		
		return this.ordersMapper.selectByUserId(userId);
	}

}
