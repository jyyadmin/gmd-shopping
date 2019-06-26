package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.dao.OrderDetailMapper;
import com.springcloud.entity.OrderDetail;
import com.springcloud.service.OrderDetailService;

/**
 * 订单明细块模型层的实现类，用于实现订单明细模块的方法
 * 
 * @author jyy
 *
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public PageInfo<OrderDetail> selectByOrderId(Integer orderId, Integer pageNumber) {
		//设置分页信息
		PageHelper.startPage(pageNumber+1,5);
		//查询指定编号的订单明细信息
		List<OrderDetail> selectByOrderId = this.orderDetailMapper.selectByOrderId(orderId);
		
		return new PageInfo<>(selectByOrderId);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean addShopping(Integer userId,OrderDetail orderDetail) {
		
		try {		
			ListOperations<String,OrderDetail> opsForList = this.redisTemplate.opsForList();
			//创建redis数据库中保存数据的键
			String key = "user" + userId;
			//向购物车中添加数据
			//1、获得指定键的list的长度（获得此用户购物车中订单明细的数量）
			Long size = opsForList.size(key);
			if(size == 0) {
				//当前用户的购物车为空，直接将订单明细插入list即可
				opsForList.leftPush(key, orderDetail);
			}else {
				//当前用户的购物车不为空，需要判断购物车中是否存在新购买的订单明细
				//1、获得redis中指定键的list所有的数据
				List<OrderDetail> list = opsForList.range(key, 0, -1);
				//2、在list中查找新的订单明细是否存在（在list集合中查找订单明细首次出现的位置，没找到返回-1）
				int indexOf = list.indexOf(orderDetail);
				if(indexOf == -1) {
					//在购物车中没有找到新购买的订单明细，直接将新的订单明细添加到redis中指定键的list即可
					opsForList.leftPush(key, orderDetail);
				}else {
					//在购物车中找到了新购买订单明细
					//获得redis中指定键list的第n个元素
					OrderDetail o = opsForList.index(key, indexOf);
					Integer num1 = o.getTransactionCount();
					Integer num2 = orderDetail.getTransactionCount();
					//修改订单明细的购买数量
					o.setTransactionCount(num1 + num2);
					//将修改后的订单明细重新放到redis中指定键的list原来的位置
					opsForList.set(key, indexOf, o);
				}
			}
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}


	@SuppressWarnings({ "unchecked" })
	@Override
	public List<OrderDetail> selectShopping(Integer userId) {
		ListOperations<String,OrderDetail> opsForList = this.redisTemplate.opsForList();
		String key = "user" + userId;
		return opsForList.range(key, 0, -1);
	}


	@SuppressWarnings("unchecked")
	@Override
	public boolean removerShopping(Integer userId, OrderDetail orderDetail) {
		ListOperations<String,OrderDetail> opsForList = this.redisTemplate.opsForList();
		//根据指定编号生成redis中list集合对应的键
		String key = "user" + userId;
		
		//获得redis指定键的list集合的长度
		Long size = opsForList.size(key);
		
		//获得list中没有元素，结束方法
		if(size == 0) {
			return false;
		}
		
		//获得list中所有元素
		List<OrderDetail> list = opsForList.range(key, 0, -1);
		//在list集合中查找参数首次出现的位置，没有找到返回-1
		int indexOf = list.indexOf(orderDetail);
		//如果在购物车中没有找到指定元素，结束方法
		if(indexOf == -1) {
			return false;
		}
		//将商品从list中移除
		list.remove(indexOf);
		
		this.redisTemplate.delete(key);
		
		for(OrderDetail o : list) {
			opsForList.rightPush(key, o);
		}
		
		return true;
	}

}
