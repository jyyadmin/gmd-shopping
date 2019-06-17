package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.dao.OrderDetailMapper;
import com.springcloud.entity.OrderDetail;
import com.springcloud.service.OrderDetailService;

/**
 * ������ϸ��ģ�Ͳ��ʵ���࣬����ʵ�ֶ�����ϸģ��ķ���
 * 
 * @author jyy
 *
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Override
	public PageInfo<OrderDetail> selectByOrderId(Integer orderId, Integer pageNumber) {
		//���÷�ҳ��Ϣ
		PageHelper.startPage(pageNumber+1,5);
		//��ѯָ����ŵĶ�����ϸ��Ϣ
		List<OrderDetail> selectByOrderId = this.orderDetailMapper.selectByOrderId(orderId);
		
		return new PageInfo<>(selectByOrderId);
	}

}