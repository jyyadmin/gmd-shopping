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
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;
import com.springcloud.vo.ResultValue;

/**
 * 商品信息
 * 
 * @author jyy
 *
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 添加商品信息
	 * 
	 * @param goods
	 * @return
	 */
	@RequestMapping(value="/insert")
	public ResultValue insert(Goods goods) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer insert = this.goodsService.insert(goods);
			if(insert > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息添加成功！！！");
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("商品信息添加失败！！！");
		return rv;
	}
	
	/**
	 * 查询商品信息
	 * 
	 * @param goods  商品信息
	 * @param pageNumber  分页
	 * @return
	 */
	@RequestMapping(value="/select")
	public ResultValue select(Goods goods,@RequestParam("pageNumber") Integer pageNumber) {
		ResultValue rv = new ResultValue();
		try {
			//查询满足条件的商品信息
			PageInfo<Goods> pageInfo = this.goodsService.select(goods, pageNumber);
			//从分页信息中获取商品信息
			List<Goods> list = pageInfo.getList();
			//如果查询到啦满足条件的商品信息
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				
				Map<String,Object> map = new HashMap<>();
				//将商品信息以指定键存入Map集合
				map.put("goodsList", list);
				
				PageUtils pageUtils = new PageUtils(pageInfo.getPages()*PageUtils.PAGE_ROW_COUNT);
				pageUtils.setPageNumber(pageNumber);
				//将分页信息以指定的名字存入Map集合
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
	 * 修改商品信息（热卖，新品，状态，商品图片）
	 * @param goods
	 * @return
	 */
	@RequestMapping(value="/updateById")
	public ResultValue updateById(Goods goods) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer goodsById = this.goodsService.updateGoodsById(goods);
			if(goodsById > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息修改成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("商品信息修改失败！！！");
		return rv;
	}
	
	/**
	 * 修改指定编号的商品信息
	 * 
	 * @param goods
	 * @return
	 */
	@RequestMapping(value="/update")
	public ResultValue update(Goods goods) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer update = this.goodsService.update(goods);
			if(update > 0) {
				rv.setCode(0);
				rv.setMessage("商品信息修改成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		rv.setMessage("商品信息修改失败！！！");
		return rv;
	}
		
	/**
	 * 查询商品前十的商品信息
	 * 
	 * @return
	 */
	@RequestMapping(value="/selectGroup")
	public ResultValue selectGroup() {
		
		ResultValue rv = new ResultValue();
		
		try {
			List<Goods> selectGroup = this.goodsService.selectGroup();
			if(selectGroup != null && selectGroup.size() > 0) {
				rv.setCode(0);
				//创建两个集合，用于保存柱状图x轴和y轴的数据
				List<String> x = new ArrayList<>();
				List<Integer> y = new ArrayList<>();
				//将查询结果中商品名称存入x集合,销量存入y集合
				for(Goods goods : selectGroup) {
					x.add(goods.getGoodName());
					y.add(goods.getGoodsSum());
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
		rv.setCode(0);
		return rv;
	}
	
	/**
	 * 获得index页面初始化的商品信息（目前仅提供热卖商品和新品商品）
	 * 
	 * @return
	 */
	@RequestMapping(value="/indexGoodsMessage")
	public ResultValue indexGoodsMessage() {
		
		ResultValue rv = new ResultValue();
		
		try {
			List<Goods> goodshot = this.goodsService.selectGoodsHot();
			List<Goods> goodsnew = this.goodsService.selectGoodsNew();
			if((goodshot != null && goodshot.size() > 0) && (goodsnew != null && goodsnew.size() > 0)) {
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("goodsHotList", goodshot);
				map.put("goodsNewList", goodsnew);
				rv.setDataMap(map);
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("页面初始化信息获取失败！！！");
		return rv;
	}
	
}
