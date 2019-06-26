package com.springcloud.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.entity.Orders;
import com.springcloud.service.OrdersService;
import com.springcloud.vo.ResultValue;

/**
 * 订单模块的控制层
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
	 * 查询订单表的信息
	 * 
	 * @param orders 查询条件
	 * @param pageNumber  页数
	 * @return  成功返回大于0的整数，否则返回null
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
	 * 修改指定编号的订单状态
	 * 
	 * @param orders 修改的订单信息
	 * @return  成功返回大于0的整数，否则返回null
	 */
	@RequestMapping(value="/updateOrdersStatus")
	public ResultValue updateOrdersStatus(Orders orders) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer ordersStatus = this.ordersService.updateOrdersStatus(orders);
			if(ordersStatus > 0) {
				rv.setCode(0);
				rv.setMessage("订单状态修改成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("订单状态修改失败！！！");
		return rv;
	}
	
	/**
	 * 查询指定日期范围内的销售额
	 * 
	 * @param orders 查询条件
	 * @return 
	 */
	@RequestMapping(value="/selectGroup")
	public ResultValue selectGroup(Orders orders) {
		
		ResultValue rv = new ResultValue();
		
		try {
			List<Orders> list = this.ordersService.selectGroup(orders);
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				//创建两个集合，用于保存折线图x轴和y轴的数据
				List<String> x = new ArrayList<>();
				List<Double> y = new ArrayList<>();
				//将查询结果中统计结果的年月存入x集合,统计结果的销售额存入y集合
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
	
	/**
	 * 添加订单与订单明细
	 * 
	 * @param orders  视图层传递json字符串
	 * @return
	 */
	@RequestMapping(value="/insert")
	//@RequestBody:将视图层传递的json字符串转换为实体类
	public ResultValue insert(@RequestBody Orders orders) {
		
		ResultValue rv = new ResultValue();
		//创建订单的时间
		orders.setOrderTime(new Date());
		
		try {
			boolean b = this.ordersService.insert(orders);
			if(b) {
				rv.setCode(0);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("创建订单失败！！！");
		return rv;		
	}
	
	/**
	 * 查询users表中指定用户的订单信息
	 * 
	 * @param userId  用户编号
	 * @return
	 */
	@RequestMapping(value="/selectByUserId")
	public ResultValue selectByUserId(@RequestParam("userId") Integer userId) {
		
		ResultValue rv = new ResultValue();
		
		try {
			List<Orders> list = this.ordersService.selectByUserId(userId);
			if(list != null) {
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("ordersList", list);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("获取订单信息失败！！！");
		return rv;
	}
}
