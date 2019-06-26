package com.springcloud.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.OrderDetail;

/**
 * ������ϸģ���ģ�Ͳ㣬���ڶ��嶩����ϸģ��ķ���
 * 
 * @author jyy
 *
 */
public interface OrderDetailService {

	/**
	 * ��ѯָ��������ŵĶ�����ϸ��Ϣ
	 * 
	 * @param orderId  �������
	 * @param pageNumber  ҳ��
	 * @return  ����com.github.pagehelper.PageInfo<OrderDetail>����ʵ��
	 */
	public abstract PageInfo<OrderDetail> selectByOrderId(Integer orderId,Integer pageNumber);
	
	/**
	 * ���ﳵ����Ӷ�����ϸ��Ϣ
	 * 
	 * @param userId   �û����
	 * @param ordeetail  ������ϸ
	 * @return  ��ӳɹ�����true�����򷵻�false
	 */
	public abstract boolean addShopping(Integer userId,OrderDetail orderDetail);
	
	/**
	 * ��ѯָ���û��Ĺ��ﳵ��Ϣ
	 * 
	 * @param userId  �û����
	 * @return  �ɹ�����java.utils.Listʵ�壬���򷵻�null
	 */
	public abstract List<OrderDetail> selectShopping(Integer userId);
	
	/**
	 * ɾ��ָ���û����ﳵ�е���Ʒ��Ϣ
	 * 
	 * @param userId  �û����
	 * @param orderDetail  ������ϸ
	 * @return  �ɹ�����true,���򷵻�false
	 */
	public abstract boolean removerShopping(Integer userId,OrderDetail orderDetail);
	
}
