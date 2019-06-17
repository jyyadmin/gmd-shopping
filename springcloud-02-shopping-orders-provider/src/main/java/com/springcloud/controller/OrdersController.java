package com.springcloud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;
import com.springcloud.vo.ResultValue;

/**
 * ����ģ��Ŀ��Ʋ�
 * 
 * @author jyy
 *
 */
@RestController
@RequestMapping("orders")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;

	/**
	 * ��ѯ���������Ϣ
	 * 
	 * @param orders ��ѯ����
	 * @param pageNumber  ҳ��
	 * @return  �ɹ����ش���0�����������򷵻�null
	 */
	@RequestMapping(value="/selectOrders")
	public ResultValue selectOrders(Orders orders,@RequestParam("pageNumber") Integer pageNumber) {
		
		ResultValue rv = new ResultValue();
		
		try {
			PageInfo<Orders> pageInfo = this.ordersService.selectOrders(orders, pageNumber);
			
			List<Orders> list = pageInfo.getList();
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("ordersList", list);
				
				PageUtils pageUtils = new PageUtils(pageInfo.getPages()*PageUtils.PAGE_ROW_COUNT);
				pageUtils.setPageNumber(pageNumber);
				map.put("pageUtils", pageUtils);
				rv.setDataMap(map);
				return rv;
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}
	
	/**
	 * �޸�ָ����ŵĶ���״̬
	 * 
	 * @param orders �޸ĵĶ�����Ϣ
	 * @return  �ɹ����ش���0�����������򷵻�null
	 */
	@RequestMapping(value="/updateOrdersStatus")
	public ResultValue updateOrdersStatus(Orders orders) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer ordersStatus = this.ordersService.updateOrdersStatus(orders);
			if(ordersStatus > 0) {
				rv.setCode(0);
				rv.setMessage("����״̬�޸ĳɹ�������");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("����״̬�޸�ʧ�ܣ�����");
		return rv;
	}
	
	/**
	 * ��ѯָ�����ڷ�Χ�ڵ����۶�
	 * 
	 * @param orders ��ѯ����
	 * @return 
	 */
	@RequestMapping(value="/selectGroup")
	public ResultValue selectGroup(Orders orders) {
		
		ResultValue rv = new ResultValue();
		
		try {
			List<Orders> list = this.ordersService.selectGroup(orders);
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				//�����������ϣ����ڱ�������ͼx���y�������
				List<String> x = new ArrayList<>();
				List<Double> y = new ArrayList<>();
				//����ѯ�����ͳ�ƽ�������´���x����,ͳ�ƽ�������۶����y����
				for(Orders order : list) {
					x.add(order.getOrderMonth());
					y.add(order.getOrderPrice());
				}
				Map<String,Object> map = new HashMap<>();
				map.put("x", x);
				map.put("y", y);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		return rv;
	}
}
