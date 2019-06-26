package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * orders_detail表对应的实体类，用于封装一行订单明细信息
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
	 * 订单明细表编号
	 */
	private Integer orderDetailId;

	/**
	 * 订单编号
	 */
    private Integer orderId;

    /**
	 * 商品编号
	 */
    private Integer goodId;

    /**
	 * 商品名称
	 */
    private String goodName;

    /**
	 * 成交价
	 */
    private Double transactionPrice;

    /**
	 * 成交数量
	 */
    private Integer transactionCount;
    
    /**
     * 用于保存订单明细的商品信息
     * 
     */
    private Goods goods;

    /**
     * 重写equals方法
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
	 * 重写hashCode方法
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodId == null) ? 0 : goodId.hashCode());
		return result;
	}

    
}