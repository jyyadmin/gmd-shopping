package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ��Ʒ��GOODS��Ӧ��ʵ����
 * 
 * @author jyy 	��Զ��
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
	/**
	 * ��Ʒ���
	 */
    private Integer goodId;

    /**
	 * ��Ʒ����
	 */
    private String goodName;

    /**
	 * ��Ʒ�۸�
	 */
    private Double goodPrices;

    /**
	 * ��Ʒ�ۿۼ�
	 */
    private Double goodDiscount;

    /**
	 * ��Ʒ״̬��0��ʾ���ߣ�1��ʾ�¼�
	 */
    private Integer goodStatus;

    /**
	 * ��Ʒ����
	 */
    private Double goodCount;

    /**
	 * ��Ʒ�Ƿ���Ʒ��0��ʾ��Ʒ��1��ʾ����Ʒ
	 */
    private Integer goodNew;

    /**
	 * �Ƿ�������0��ʾ������1��ʾ������
	 */
    private Integer goodHot;

    /**
	 * ��Ʒ�ļ������ͣ���0�߶ˡ�1�м���2�Ͷ�
	 */
    private Integer goodLevel;

    /**
	 * ��Ʒ���
	 */
    private String goodBrief;

    /**
	 * ��Ʒ����
	 */
    private String goodDetails;

    /**
	 * ��ƷͼƬ
	 */
    private String goodPhoto;

    /**
	 * ���Ͷ��ı��
	 */
    private Integer class2Id;
    
    /**
     * ������Ʒ�۸�����
     */
    private Double goodsPriceMin;
    
    /**
     * ������Ʒ�۸�����
     */
    private Double goodsPriceMax;
    
    /**
     * ��ѯ������һ�������
     */
    private Integer class1Id;
    
    /**
     * ��Ʒ���������ڱ���ͳ�Ʒ���Ľ��
     */
    private Integer goodsSum;

    
}