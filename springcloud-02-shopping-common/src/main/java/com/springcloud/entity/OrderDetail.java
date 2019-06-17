package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * orders_detail���Ӧ��ʵ���࣬���ڷ�װһ�ж�����ϸ��Ϣ
 *  
 * @author jyy
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail implements java.io.Serializable{
   
	private static final long serialVersionUID = -6494914096196712445L;

	/**
	 * ������ϸ����
	 */
	private Integer orderDetailId;

	/**
	 * �������
	 */
    private Integer orderId;

    /**
	 * ��Ʒ���
	 */
    private Integer goodId;

    /**
	 * ��Ʒ����
	 */
    private String goodName;

    /**
	 * �ɽ���
	 */
    private Double transactionPrice;

    /**
	 * �ɽ�����
	 */
    private String transactionCount;

    
}