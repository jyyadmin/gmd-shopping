package com.springcloud.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.springcloud.entity.Goods;

/**
 * 商品模型层的接口，用于定义商品模块的方法
 * 
 * @author jyy
 *
 */
public interface GoodsService {

	/**
	 * 添加新的商品信息
	 * 
	 * @param goods	新的商品信息
	 * 
	 * @return	成功返回大于0的整数，否则返回0
	 */
	public abstract Integer insert(Goods goods); 
	
	/**
	 * 查询满足条件的商品信息（分页功能）
	 * 
	 * @param goods   查询的条件
	 * @param pageNumber   页数
	 * @return 	  成功返回com.github.pagehelper.PageInfo.PageInfo<Goods>类型实例，否则返回null
	 */
	public abstract PageInfo<Goods> select(Goods goods,Integer pageNumber);
	
	/**
	 * 根据条件修改商品信息
	 * 
	 * @param goods  修改商品的信息
	 * @return  成功返回大于0的整数  否则返回小于等于0的整数
	 */
	public abstract Integer updateGoodsById(Goods goods);
	
	/**
	 * 修改指定编号的商品信息
	 * 
	 * @param goods  修改商品的信息
	 * @return	 成功返回大于0的整数  否则返回小于等于0的整数
	 */
	public abstract Integer update(Goods goods);
	
	/**
	 * 查询销量前十的商品信息
	 * 
	 * @return  成功返回java.util.List类型的实例，否则返回null
	 */
	public abstract List<Goods> selectGroup();
	
	/**
     * 查询热卖商品的前五行商品信息
     * 
     * @return  成功返回java.util.List类型的实例，否则返回null
     */
	public abstract List<Goods> selectGoodsHot();
	
	/**
     *  查询新品商品的前五行商品信息
     * 
     * @return  成功返回java.util.List类型的实例，否则返回null
     */
	public abstract List<Goods> selectGoodsNew();
	
}
