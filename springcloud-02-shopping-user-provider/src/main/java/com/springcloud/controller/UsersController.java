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
 *控制层，接受视图层的数据，并调用模型层的方法，将数据返回到视图层中
 * 
 * @author jyy
 *
 */
@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	/**
	 * 判断用户是否登录成功
	 * 
	 * @param userId	用户编号
	 * @param userPassword	用户密码
	 * @param jdictionId	权限编号
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ResultValue login(@RequestParam("userId") Integer userId, @RequestParam("userPassword") String userPassword,
			@RequestParam("jdictionId") Integer jdictionId) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Users login = this.usersService.login(userId, userPassword, jdictionId);
			//登录信息正确时
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
		//登录信息错误时
		rv.setCode(1);
		rv.setMessage("登录信息不正确，请重新登录！！！");
		return rv;
	}
	
	/**
	 * 添加新的用户信息
	 * 
	 * @param users 保存用户的信息
	 * @return
	 */
	@RequestMapping(value = "/insert")
	public ResultValue insert(Users users) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Users insert = this.usersService.insert(users);
			//用户录入成功时
			if(insert != null ) {
				rv.setCode(0);
				rv.setMessage("用户录入成功.！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		//用户录入失败时
		rv.setCode(1);
		rv.setMessage("用户录入失败，请重新输！！！");
		return rv;
	}

	/**
	 * 查询满足条件的用户信息
	 * 
	 * @param users  查询条件
	 * @param pageNumber  页数
	 * @return
	 */
	@RequestMapping(value="/select")
	public ResultValue select(Users users,@RequestParam("pageNumber") Integer pageNumber) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Page<Users> page = this.usersService.select(users, pageNumber);
			//获得分页信息
			List<Users> list = page.getContent();
			//判断是否查询到数据
			if(list != null && list.size() > 0) {
				rv.setCode(0);
				
				Map<String,Object> map = new HashMap<>();
				//将分页数据添加到Map中
				map.put("userList", list);
				
				PageUtils pageUtils = new PageUtils((int)page.getTotalElements());
				pageUtils.setPageNumber(pageNumber);
				//将分页信息添加到Map中
				map.put("pageUtils", pageUtils);
				
				//将Map添加到ResultValue对象中
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
	 * 修改指定用户的状态
	 * 
	 * @param userId  用户编号
	 * @param userStatus  用户状态
	 * @return
	 */
	@RequestMapping(value="/updateStatus")
	public ResultValue updateStatus(@RequestParam("userId") Integer userId,@RequestParam("userStatus") Integer userStatus) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer status = this.usersService.updateStatus(userId, userStatus);
			if(status > 0) {
				//状态修改成功时
				rv.setCode(0);
				rv.setMessage("用户状态修改成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//状态修改失败时
		rv.setCode(1);
		rv.setMessage("用户状态修改失败！！！");
		return rv;
	}
	
	/**
	 * 根据ID编号查询指定用户
	 * 
	 * @param userId  用户编号
	 * @return
	 */
	@RequestMapping(value="/select/{userId}")
	public ResultValue selectById(@PathVariable("userId") Integer userId) {
		
		ResultValue rv = new ResultValue();
		
		try {
			//调用service中的方法，并获得查询结果
			Users id = this.usersService.selectById(userId);
			//如果成功
			if(id != null){
				//设置结果的状态为0
				rv.setCode(0);
				//创建Map集保存查询结果
				Map<String,Object> map = new HashMap<>();
				//将查询结果保存到Map中
				map.put("users", id);
				//将Map集合添加到ResultValue对象中
				rv.setDataMap(map);
				//返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("获取用户信息失败！！！");
		return rv;
	}
	
	/**
	 * 修改用户的信息
	 * 
	 * @param users  用户信息
	 * @return
	 */
	@RequestMapping(value="/update")
	public ResultValue update(Users users) {
		
		ResultValue rv = new ResultValue();
		
		try {
			//调用service中的方法，并获得查询结果
			Integer update = this.usersService.update(users);
			//如果修改成功
			if(update > 0) {
				//设置结果的状态为0
				rv.setCode(0);
				//返回ResultValue对象
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户信息修改失败！！！");
		return rv;
		
	}
	
	/**
	 * 修改用户的头像
	 * 
	 * @param users
	 * @return
	 */
	@RequestMapping(value="/updatePhoto")
	public ResultValue updatePhoto(Users users) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer updatePhoto = this.usersService.updatePhoto(users);
			if(updatePhoto > 0) {
				rv.setCode(0);
				rv.setMessage("用户头像修改成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户头像修改失败！！！");
		return rv;
	}
	
	/**
	 * 修改用户的密码
	 * 
	 * @param users
	 * @return
	 */
	@RequestMapping(value="/updatePassword")
	public ResultValue updatePassword(Users users) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer updatePassword = this.usersService.updatePassword(users);
			if(updatePassword > 0) {
				rv.setCode(0);
				rv.setMessage("用户密码修改成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户密码修改失败！！！");
		return rv;
	}
	/**
	 * 修改用户的昵称
	 * 
	 * @param users
	 * @return
	 */
	@RequestMapping(value="/updateName")
	public ResultValue updateName(Users users) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Integer updateName = this.usersService.updateName(users);
			if(updateName > 0) {
				rv.setCode(0);
				rv.setMessage("用户昵称修改成功！！！");
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("用户昵称修改失败！！！");
		return rv;
	}
	
	/**
	 * 判断用户名出现的次数
	 * 
	 * @param userName  用户名
	 * @return
	 */
	@RequestMapping(value="/countByUserName")
	public ResultValue countByUserName(@RequestParam("userName") String userName) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Long countByUserName = this.usersService.countByUserName(userName);
			if(countByUserName != null) {
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("count", countByUserName);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("服务器正忙，请稍后再试！！！");
		return rv;
	}
	
	/**
	 * 普通用户登录
	 * 
	 * @param userName  用户名称
	 * @param userPassword  用户密码
	 * @param userStatus  用户状态
	 * @param jdictionId  用户权限
	 * @return
	 */
	@RequestMapping(value="/userLogin")
	public ResultValue userLogin(@RequestParam String userName,@RequestParam String userPassword,@RequestParam Integer userStatus,@RequestParam Integer jdictionId) {
		
		ResultValue rv = new ResultValue();
		
		try {
			Users userLogin = this.usersService.userLogin(userName, userPassword, userStatus, jdictionId);
			if(userLogin != null) {
				rv.setCode(0);
				Map<String,Object> map = new HashMap<>();
				map.put("userMessage", userLogin);
				rv.setDataMap(map);
				return rv;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		rv.setCode(1);
		rv.setMessage("登录信息不正确，请重新登录！！！");
		return rv;
	}
	
		
	
}




