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
 * 订单明细模型的控制层
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
	 * 查询指定订单编号的订单明细信息
	 * 
	 * @param orderId    订单编号
	 * @param pageNumber 页数
	 * @return 成功返回大于0的整数，否则返回null
	 */
	@RequestMapping(value = "selectByOrderId")
	public ResultValue selectByOrderId(@RequestParam("orderId") Integer orderId,
			@RequestParam("pageNumber") Integer pageNumber) {

		ResultValue rv = new ResultValue();

		try {
			PageInfo<OrderDetail> selectByOrderId = this.orderDetailService.selectByOrderId(orderId, pageNumber);

			List<OrderDetail> list = selectByOrderId.getList();
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("orderDetailList", list);

				PageUtils pageUtils = new PageUtils(5, selectByOrderId.getPages() * 5);
				pageUtils.setPageNumber(pageNumber);
				map.put("pageUtils", pageUtils);

				rv.setDataMap(map);
				return rv;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		rv.setCode(1);
		rv.setMessage("没有找到满足条件的订单明细信息！！！");
		return rv;
	}

	/**
	 * 向购物车中添加订单明细
	 * 
	 * @param userId  用户编号
	 * @param orderDetail  订单明细
	 * @return
	 */
	@RequestMapping(value = "/addShopping")
	public ResultValue addShopping(@RequestParam("userId") Integer userId, OrderDetail orderDetail) {

		ResultValue rv = new ResultValue();

		try {
			boolean b = this.orderDetailService.addShopping(userId, orderDetail);
			if (b) {
				rv.setCode(0);
				rv.setMessage("添加购物车成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("添加购物车失败！！！");
		return rv;

	}

	/**
	 * 查询指定用户的商品购物车
	 * 
	 * @param userId   用户编号
	 * @return
	 */
	@RequestMapping(value = "/selectShopping")
	public ResultValue selectShopping(@RequestParam("userId") Integer userId) {

		ResultValue rv = new ResultValue();

		try {
			List<OrderDetail> list = this.orderDetailService.selectShopping(userId);
			if (list != null && list.size() > 0) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("shoppingList", list);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("获取购物车信息失败！！！");
		return rv;

	}

	/**
	 * 删除指定用户购物车中的商品信息
	 * 
	 * @param userId  用户编号
	 * @param orderDetail   订单明细
	 * @return
	 */
	@RequestMapping(value = "/removeShopping")
	public ResultValue removeShopping(@RequestParam("userId") Integer userId, OrderDetail orderDetail) {

		ResultValue rv = new ResultValue();

		try {
			boolean b = this.orderDetailService.removerShopping(userId, orderDetail);
			if (b) {
				rv.setCode(0);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("移除购物车中的商品失败！！！");
		return rv;
	}
}
