package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品表GOODS对应的实体类
 * 
 * @author jyy 	姜远焱
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
	/**
	 * 商品编号
	 */
    private Integer goodId;

    /**
	 * 商品名称
	 */
    private String goodName;

    /**
	 * 商品价格
	 */
    private Double goodPrices;

    /**
	 * 商品折扣价
	 */
    private Double goodDiscount;

    /**
	 * 商品状态：0表示在线，1表示下架
	 */
    private Integer goodStatus;

    /**
	 * 商品数量
	 */
    private Double goodCount;

    /**
	 * 商品是否新品：0表示新品，1表示非新品
	 */
    private Integer goodNew;

    /**
	 * 是否热卖：0表示热卖，1表示非热卖
	 */
    private Integer goodHot;

    /**
	 * 商品的级别（类型）：0高端、1中级、2低端
	 */
    private Integer goodLevel;

    /**
	 * 商品简介
	 */
    private String goodBrief;

    /**
	 * 商品详情
	 */
    private String goodDetails;

    /**
	 * 商品图片
	 */
    private String goodPhoto;

    /**
	 * 类型二的编号
	 */
    private Integer class2Id;
    
    /**
     * 输入商品价格下限
     */
    private Double goodsPriceMin;
    
    /**
     * 输入商品价格上限
     */
    private Double goodsPriceMax;
    
    /**
     * 查询条件：一级类别编号
     */
    private Integer class1Id;
    
    /**
     * 商品销量，用于保存统计分组的结果
     */
    private Integer goodsSum;

    
}