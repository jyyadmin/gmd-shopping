package com.springcloud.service;

import org.springframework.data.domain.Page;

import com.springcloud.entity.Users;

/**
 * 模型层的接口：用于定义用户模块的方法
 * @author jyy
 *
 */
public interface UsersService {

	/**
	 * 	验证用户是否登陆成功
	 * 
	 * @param userId	用户编号
	 * @param userPassword	用户密码
	 * @param jdictionId	权限编号
	 * @return  成功返回用户完整信息，失败返回null
	 */
	public abstract Users login(Integer userId,String userPassword,Integer jdictionId);
	
	/**
	 * 	添加新的用户信息
	 * 
	 * @param users	新的的用户
	 * @return	添加成功返回Users类型的实例，否则返回null
	 */
	public abstract Users insert(Users users);
	
	/**
	 * 查询满足条件的用户信息
	 * 
	 * @param user    查询用户
	 * @param pageNumber  查询页数
	 * @return  		成功返回Users类型的实例，否则返回null
	 */
	public abstract Page<Users> select(Users user,Integer pageNumber);
	
	/**
	 * 修改指定用户的状态
	 * 
	 * @param userId  用户编号
	 * @param userStatus  用户状态
	 * @return  修改成功返回大于零的整数，否则返回0
	 */
	public abstract Integer updateStatus(Integer userId,Integer userStatus);

	/**
	 * 根据编号查询指定用户
	 * 
	 * @param userId   用户编号
	 * @return  成功返回com.springcloud.entity.Users类型的实例，否则返回null
	 */
	public abstract Users selectById(Integer userId);
	
	/**
	 * 修改指定编号的用户信息
	 * 
	 * @param users  修改的用户信息
	 * @return  成功返回大于0的整数，否则返回null
	 */
	public abstract Integer update(Users users);
	
	/**
	 * 修改指定编号的用户头像
	 * 
	 * @param users 修改的用户信息
	 * @return  成功返回大于0的整数，否则返回null
	 */
	public abstract Integer updatePhoto(Users users);
	
	/**
	 * 修改指定编号的用户密码
	 * 
	 * @param users 修改的用户信息
	 * @return  成功返回大于0的整数，否则返回null
	 */
	public abstract Integer updatePassword(Users users);
	
	/**
	 * 修改指定编号的用户昵称
	 * 
	 * @param users 修改的用户信息
	 * @return  成功返回大于0的整数，否则返回null
	 */
	public abstract Integer updateName(Users users);
}
