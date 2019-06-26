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
 * ����ģ��ģ�Ͳ�ʵ���࣬����ʵ�ֶ���ģ��ķ���
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

		return this.ordersMapper.updateOrdersStatus(orders);// ���ó־û�
	}

	@Override
	public List<Orders> selectGroup(Orders orders) {

		return this.ordersMapper.selectGroup(orders);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public boolean insert(Orders orders) {
		// ��Ӷ�����Ϣ
		int orderId = this.ordersMapper.insert(orders);
		// ���������Ϣ��ӳɹ���������Ӷ�����ϸ��Ϣ
		if (orderId > 0) {
			Integer orderDetail = this.orderDetailMapper.insertOrderDetail(orders);
			if (orderDetail > 0) {
				// ��redis��ָ���û����ﳵ����Ϣɾ��
				String key = "user" + orders.getUserId();
				ListOperations<String, OrderDetail> opsForList = this.redisTemplate.opsForList();

				// ����û���redis�����еĹ��ﳵ��Ϣ
				List<OrderDetail> range = opsForList.range(key, 0, -1);

				// ����û���Ҫ���㹺�ﳵ��Ϣ
				List<OrderDetail> orderDetailList = orders.getOrderDetailList();

				// ���㹺����Ϣ
				if (range.size() == orderDetailList.size()) {
					// �����������еĳ�����ȣ���ʾ�û��Թ��ﳵ��������Ʒ�����˽���
					Boolean delete = this.redisTemplate.delete(key);
					if (delete) {
						return true;
					}
				} else {
					// �����������еĳ��Ȳ����ʱ�������й��ﳵ��ɾ���ѽ���Ĺ�����Ϣ
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
