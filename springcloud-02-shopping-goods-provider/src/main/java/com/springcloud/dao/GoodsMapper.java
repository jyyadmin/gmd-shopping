package com.springcloud.dao;

import com.springcloud.entity.Goods;
import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer goodId);

    int insert(Goods record);

    Goods selectByPrimaryKey(Integer goodId);

    List<Goods> selectAll();

    int updateByPrimaryKey(Goods record);
    
    /**
     * 根据指定条件查询商品信息
     * 
     * @param goods	商品信息
     * @return  成功返回java.util.List类型的实例，否则返回null
     */
    public abstract List<Goods> select(Goods goods);
    
    /**
     * 根据条件修改GOODS表中指定的good_id的商品信息
     * @param goods   查询的商品
     * @return   成功返回大于0的整数 , 否则返回小于等于0的整数
     */
    public abstract Integer updateGoodsById(Goods goods);
    
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