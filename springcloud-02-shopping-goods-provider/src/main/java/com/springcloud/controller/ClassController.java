package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.entity.Class1;
import com.springcloud.entity.Class2;
import com.springcloud.service.ClassService;
import com.springcloud.vo.ResultValue;

/**
 * 一级类别与二级类别的控制器
 * 
 * @author jyy
 *
 */
@RestController
@RequestMapping("class")
public class ClassController {

	@Autowired
	private ClassService classService;
	
	@RequestMapping(value="/selectAll")
	public ResultValue selectAll() {
		ResultValue rv = new ResultValue();
		
		try {
			//调用service中的方法，并获得查询结果
			List<Class1> selectAllClass1 = this.classService.selectAllClass1();
			List<Class2> selectAllClass2 = this.classService.selectAllClass2();
			//如果成功
			if(selectAllClass1 != null && selectAllClass1.size() > 0) {
				//设置结果的状态为0
				rv.setCode(0);
				//创建Map集保存查询结果
				Map<String,Object> map = new HashMap<>();
				//创建Map集保存查询结果
				map.put("class1List", selectAllClass1);
				map.put("class2List", selectAllClass2);
				//将Map集合添加到ResultValue对象中
				rv.setDataMap(map);
				//返回ResultValue对象
				return rv;
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}
	
	@RequestMapping(value="/selectById")
	public ResultValue selectById(@RequestParam("class1Id") Integer class1Id) {
		
		ResultValue rv = new ResultValue();
		
		try {
			//调用service中的方法，并获得查询结果
			List<Class2> class1Id2 = this.classService.selectClass2ByClass1Id(class1Id);
			//如果成功
			if(class1Id2 != null && class1Id2.size() > 0) {
				//设置结果的状态为0
				rv.setCode(0);
				//创建Map集保存查询结果
				Map<String,Object> map = new HashMap<>();
				//将查询结果保存到Map中
				map.put("class2List", class1Id2);
				//将Map集合添加到ResultValue对象中
				rv.setDataMap(map);
				//返回ResultValue对象
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}	
	
	
}
