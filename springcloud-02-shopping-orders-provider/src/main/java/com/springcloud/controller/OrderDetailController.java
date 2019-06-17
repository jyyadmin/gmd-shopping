package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.OrderDetail;
import com.springcloud.service.OrderDetailService;
import com.springcloud.vo.ResultValue;

/**
 * ������ϸģ�͵Ŀ��Ʋ�
 * 
 * @author jyy
 *
 */
@RestController
@RequestMapping("orderDetail")
public class OrderDetailController {

	@Autowired
	private OrderDetailService orderDetailService;
	
	/**
	 * ��ѯָ��������ŵĶ�����ϸ��Ϣ
	 * 
	 * @param orderId  �������
	 * @param pageNumber  ҳ��
	 * @return   �ɹ����ش���0�����������򷵻�null
	 */
	@RequestMapping(value="selectByOrderId")
	public ResultValue selectByOrderId(@RequestParam("orderId") Integer orderId,@RequestParam("pageNumber") Integer pageNumber) {
		
		ResultValue rv = new ResultValue();
		
		try {
			PageInfo<OrderDetail> selectByOrderId = this.orderDetailService.selectByOrderId(orderId, pageNumber);
			
			List<OrderDetail> list = selectByOrderId.getList();
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("orderDetailList", list);
				
				PageUtils pageUtils = new PageUtils(5,selectByOrderId.getPages()*5);
				pageUtils.setPageNumber(pageNumber);
				map.put("pageUtils", pageUtils);
				
				rv.setDataMap(map);
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("û���ҵ����������Ķ�����ϸ��Ϣ������");
		return rv;
	}
}
