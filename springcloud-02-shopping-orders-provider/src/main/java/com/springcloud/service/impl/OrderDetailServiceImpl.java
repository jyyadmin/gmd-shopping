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
 * ������ϸ��ģ�Ͳ��ʵ���࣬����ʵ�ֶ�����ϸģ��ķ���
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
		//���÷�ҳ��Ϣ
		PageHelper.startPage(pageNumber+1,5);
		//��ѯָ����ŵĶ�����ϸ��Ϣ
		List<OrderDetail> selectByOrderId = this.orderDetailMapper.selectByOrderId(orderId);
		
		return new PageInfo<>(selectByOrderId);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean addShopping(Integer userId,OrderDetail orderDetail) {
		
		try {		
			ListOperations<String,OrderDetail> opsForList = this.redisTemplate.opsForList();
			//����redis���ݿ��б������ݵļ�
			String key = "user" + userId;
			//���ﳵ���������
			//1�����ָ������list�ĳ��ȣ���ô��û����ﳵ�ж�����ϸ��������
			Long size = opsForList.size(key);
			if(size == 0) {
				//��ǰ�û��Ĺ��ﳵΪ�գ�ֱ�ӽ�������ϸ����list����
				opsForList.leftPush(key, orderDetail);
			}else {
				//��ǰ�û��Ĺ��ﳵ��Ϊ�գ���Ҫ�жϹ��ﳵ���Ƿ�����¹���Ķ�����ϸ
				//1�����redis��ָ������list���е�����
				List<OrderDetail> list = opsForList.range(key, 0, -1);
				//2����list�в����µĶ�����ϸ�Ƿ���ڣ���list�����в��Ҷ�����ϸ�״γ��ֵ�λ�ã�û�ҵ�����-1��
				int indexOf = list.indexOf(orderDetail);
				if(indexOf == -1) {
					//�ڹ��ﳵ��û���ҵ��¹���Ķ�����ϸ��ֱ�ӽ��µĶ�����ϸ��ӵ�redis��ָ������list����
					opsForList.leftPush(key, orderDetail);
				}else {
					//�ڹ��ﳵ���ҵ����¹��򶩵���ϸ
					//���redis��ָ����list�ĵ�n��Ԫ��
					OrderDetail o = opsForList.index(key, indexOf);
					Integer num1 = o.getTransactionCount();
					Integer num2 = orderDetail.getTransactionCount();
					//�޸Ķ�����ϸ�Ĺ�������
					o.setTransactionCount(num1 + num2);
					//���޸ĺ�Ķ�����ϸ���·ŵ�redis��ָ������listԭ����λ��
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
		//����ָ���������redis��list���϶�Ӧ�ļ�
		String key = "user" + userId;
		
		//���redisָ������list���ϵĳ���
		Long size = opsForList.size(key);
		
		//���list��û��Ԫ�أ���������
		if(size == 0) {
			return false;
		}
		
		//���list������Ԫ��
		List<OrderDetail> list = opsForList.range(key, 0, -1);
		//��list�����в��Ҳ����״γ��ֵ�λ�ã�û���ҵ�����-1
		int indexOf = list.indexOf(orderDetail);
		//����ڹ��ﳵ��û���ҵ�ָ��Ԫ�أ���������
		if(indexOf == -1) {
			return false;
		}
		//����Ʒ��list���Ƴ�
		list.remove(indexOf);
		
		this.redisTemplate.delete(key);
		
		for(OrderDetail o : list) {
			opsForList.rightPush(key, o);
		}
		
		return true;
	}

}
