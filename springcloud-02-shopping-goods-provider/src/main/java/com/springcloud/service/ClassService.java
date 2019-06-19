package com.springcloud.service;

import java.util.List;

import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;

/**
 * ÓÃÓÚ¶¨ÒåÒ»¼¶Àà±ðÓë¶þ¼¶Àà±ðÄ£¿éµÄ·½·¨
 * 
 * @author jyy
 *
 */
public interface ClassService {

	/**
	 * ²éÑ¯ËùÓÐÒ»¼¶Àà±ðµÄÐÅÏ¢
	 * 
	 * @return	³É¹¦·µ»Øjava.util.ListÀàÐÍÊµÀý£¬·ñÔò·µ»Ønull
	 */
	public abstract List<Class1> selectAllClass1();
	
	/**
	 * ¸ù¾ÝÒ»¼¶Àà±ð²éÑ¯ÏàÓ¦¶þ¼¶Àà±ðµÄÐÅÏ¢
	 * 
	 * @param class1Id  类别编号
	 * @return	成功返回java.util.List类型实例，否则返回null
	 */
	public abstract List<Class2> selectClass2ByClass1Id(Integer class1Id);
	
	/**
	 * 查询所有二级类别的信息
	 * 
	 * @return  成功返回java.util.List类型实例，否则返回null
	 */
	public abstract List<Class2> selectAllClass2();
}
