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
     * ��ѯָ��������ŵĶ�����Ϣ
     * 
     * @param orderId  �������
     * @return   �ɹ�����java.util.list����ʵ�������򷵻�null
     */
    public abstract List<OrderDetail> selectByOrderId(Integer orderId);
    
    /**
     * ������Ӷ�����ϸ
     * 
     * @param orders  һ�鶩����ϸ
     * @return  �ɹ����ش���0�����������߷���С�ڵ���0������
     */
    public abstract Integer insertOrderDetail(Orders orders);
}