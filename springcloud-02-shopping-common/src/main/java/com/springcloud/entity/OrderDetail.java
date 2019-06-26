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
    private Integer transactionCount;
    
    /**
     * ���ڱ��涩����ϸ����Ʒ��Ϣ
     * 
     */
    private Goods goods;

    /**
     * ��дequals����
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		if (goodId == null) {
			if (other.goodId != null)
				return false;
		} else if (!goodId.equals(other.goodId))
			return false;
		return true;
	}

	/**
	 * ��дhashCode����
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodId == null) ? 0 : goodId.hashCode());
		return result;
	}

    
}