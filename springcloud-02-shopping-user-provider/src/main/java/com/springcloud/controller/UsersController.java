package com.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.service.UsersService;
import com.springcloud.vo.ResultValue;

/**
 *���Ʋ㣬������ͼ������ݣ�������ģ�Ͳ�ķ����������ݷ��ص���ͼ����
 * 
 * @author jyy
 *
 */
@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	/**
	 *�û���Ϣ
	 * 
	 * @param userId	�û����
	 * @param userPassword	�û�����
	 * @param jdictionId	Ȩ�ޱ��
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ResultValue login(@RequestParam("userId") Integer userId, @RequestParam("userPassword") String userPassword,
			@RequestParam("jdictionId") Integer jdictionId) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Users login = this.usersService.login(userId, userPassword, jdictionId);
			//��¼��Ϣ��ȷʱ
			if (login != null) {
				rv.setCode(0);
				Map<String, Object> map = new HashMap<>();
				map.put("loginUser", login);
				rv.setDataMap(map);
				return rv;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//��¼��Ϣ����ʱ
		rv.setCode(1);
		rv.setMessage("��¼��Ϣ����ȷ�������µ�¼������");
		return rv;
	}
	
	/**
	 *¼���û���Ϣ
	 * 
	 * @param users �����û�����Ϣ
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ResultValue insert(Users users) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Users insert = this.usersService.insert(users);
			//�û�¼��ɹ�ʱ
			if(insert != null ) {
				rv.setCode(0);
				rv.setMessage("�û�¼��ɹ�.������");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		//�û�¼��ʧ��ʱ
		rv.setCode(1);
		rv.setMessage("�û�¼��ʧ�ܣ��������䣡����");
		return rv;
	}

	@RequestMapping(value="/select")
	public ResultValue select(Users users,@RequestParam("pageNumber") Integer pageNumber) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Page<Users> page = this.usersService.select(users, pageNumber);
			//��÷�ҳ��Ϣ
			List<Users> list = page.getContent();
			//�ж��Ƿ��ѯ������
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				
				Map<String,Object> map = new HashMap<>();
				//����ҳ������ӵ�Map��
				map.put("userList", list);
				
				PageUtils pageUtils = new PageUtils((int)page.getTotalElements());
				pageUtils.setPageNumber(pageNumber);
				//����ҳ��Ϣ��ӵ�Map��
				map.put("pageUtils", pageUtils);
				
				//��Map��ӵ�ResultValue������
				rv.setDataMap(map);
				
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rv.setCode(1);
		return rv;
	}
	
	
	@RequestMapping(value="/updateStatus")
	public ResultValue updateStatus(@RequestParam("userId") Integer userId,@RequestParam("userStatus") Integer userStatus) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer status = this.usersService.updateStatus(userId, userStatus);
			if(status > 0) {
				//״̬�޸ĳɹ�ʱ
				rv.setCode(0);
				rv.setMessage("�û�״̬�޸ĳɹ�������");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//״̬�޸�ʧ��ʱ
		rv.setCode(1);
		rv.setMessage("�û�״̬�޸�ʧ�ܣ�����");
		return rv;
	}
	
	@RequestMapping(value="/select/{userId}")
	public ResultValue selectById(@PathVariable("userId") Integer userId) {
		
		ResultValue rv = new ResultValue();
		
		try {
			//����service�еķ���������ò�ѯ���
			Users id = this.usersService.selectById(userId);
			//����ɹ�
			if(id != null){
				//���ý����״̬Ϊ0
				rv.setCode(0);
				//����Map�������ѯ���
				Map<String,Object> map = new HashMap<>();
				//����ѯ������浽Map��
				map.put("users", id);
				//��Map������ӵ�ResultValue������
				rv.setDataMap(map);
				//����ResultValue����
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("��ȡ�û���Ϣʧ�ܣ�����");
		return rv;
	}
	
	@RequestMapping(value="/update")
	public ResultValue update(Users users) {
		
		ResultValue rv = new ResultValue();
		
		try {
			//����service�еķ���������ò�ѯ���
			Integer update = this.usersService.update(users);
			//����޸ĳɹ�
			if(update > 0) {
				//���ý����״̬Ϊ0
				rv.setCode(0);
				//����ResultValue����
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("�û���Ϣ�޸�ʧ�ܣ�����");
		return rv;
		
	}
	
	@RequestMapping(value="/updatePhoto")
	public ResultValue updatePhoto(Users users) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer updatePhoto = this.usersService.updatePhoto(users);
			if(updatePhoto > 0) {
				rv.setCode(0);
				rv.setMessage("�û�ͷ���޸ĳɹ�������");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("�û�ͷ���޸�ʧ�ܣ�����");
		return rv;
	}
	
	@RequestMapping(value="/updatePassword")
	public ResultValue updatePassword(Users users) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer updatePassword = this.usersService.updatePassword(users);
			if(updatePassword > 0) {
				rv.setCode(0);
				rv.setMessage("�û������޸ĳɹ�������");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("�û������޸�ʧ�ܣ�����");
		return rv;
	}
	
	@RequestMapping(value="/updateName")
	public ResultValue updateName(Users users) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer updateName = this.usersService.updateName(users);
			if(updateName > 0) {
				rv.setCode(0);
				rv.setMessage("�û��ǳ��޸ĳɹ�������");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("�û��ǳ��޸�ʧ�ܣ�����");
		return rv;
	}
	
}
