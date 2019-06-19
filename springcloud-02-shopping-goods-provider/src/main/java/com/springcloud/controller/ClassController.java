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
 * һ�������������Ŀ�����
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
			//����service�еķ���������ò�ѯ���
			List<Class1> selectAllClass1 = this.classService.selectAllClass1();
			List<Class2> selectAllClass2 = this.classService.selectAllClass2();
			//����ɹ�
			if(selectAllClass1 != null && selectAllClass1.size() > 0) {
				//���ý����״̬Ϊ0
				rv.setCode(0);
				//����Map�������ѯ���
				Map<String,Object> map = new HashMap<>();
				//����Map�������ѯ���
				map.put("class1List", selectAllClass1);
				map.put("class2List", selectAllClass2);
				//��Map������ӵ�ResultValue������
				rv.setDataMap(map);
				//����ResultValue����
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
			//����service�еķ���������ò�ѯ���
			List<Class2> class1Id2 = this.classService.selectClass2ByClass1Id(class1Id);
			//����ɹ�
			if(class1Id2 != null && class1Id2.size() > 0) {
				//���ý����״̬Ϊ0
				rv.setCode(0);
				//����Map�������ѯ���
				Map<String,Object> map = new HashMap<>();
				//����ѯ������浽Map��
				map.put("class2List", class1Id2);
				//��Map������ӵ�ResultValue������
				rv.setDataMap(map);
				//����ResultValue����
				return rv;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		return rv;
	}	
	
	
}
