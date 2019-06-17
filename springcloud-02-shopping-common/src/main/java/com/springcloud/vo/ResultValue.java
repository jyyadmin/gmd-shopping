package com.springcloud.vo;

import java.util.Map;

import lombok.Data;

/**
 * ���屾��Ŀ������controller�Ľ������
 * 
 * @author jyy
 *
 */
@Data
public class ResultValue implements java.io.Serializable{
	
	private static final long serialVersionUID = -7333058567132357663L;

	/**
	 * ���õ�ǰ���ؽ����״̬�� 0��ʾ�ɹ���1��ʾʧ��
	 */
	private Integer code;
	
	/**
	 * ���÷�����Ϣ
	 */
	private String message;
	
	/**
	 * ���÷�������
	 */
	private Map<String,Object> dataMap;
}