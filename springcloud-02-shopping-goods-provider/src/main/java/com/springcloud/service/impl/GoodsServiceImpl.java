package com.springcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springcloud.common.PageUtils;
import com.springcloud.dao.GoodsMapper;
import com.springcloud.entity.Goods;
import com.springcloud.service.GoodsService;

/**
 * 商品的模块方法
 * 
 * @author jyy
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Transactional
	@Override
	public Integer insert(Goods goods) {
		
		return this.goodsMapper.insert(goods);
	}

	
	@Override
	public PageInfo<Goods> select(Goods goods, Integer pageNumber) {
		//商品名称两端加上%
		goods.setGoodName("%" + goods.getGoodName() + "%");
		//设置每页的信息
		PageHelper.startPage(pageNumber+1,PageUtils.PAGE_ROW_COUNT); 
		//查询满足条件的商品
		List<Goods> list = this.goodsMapper.select(goods);
		//返回分页信
		return new PageInfo<>(list);
	}

	
	@Transactional
	@Override
	public Integer updateGoodsById(Goods goods) {
		
		return this.goodsMapper.updateGoodsById(goods);
	}


	@Override
	public Integer update(Goods goods) {
		
		return this.goodsMapper.updateByPrimaryKey(goods);
	}


	@Override
	public List<Goods> selectGroup() {
		
		return this.goodsMapper.selectGroup();
	}


	@Override
	public List<Goods> selectGoodsHot() {
		
		return this.goodsMapper.selectGoodsHot();
	}


	@Override
	public List<Goods> selectGoodsNew() {
		
		return this.goodsMapper.selectGoodsNew();
	}
	
}
