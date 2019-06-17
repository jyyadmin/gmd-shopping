package com.springcloud.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcloud.common.PageUtils;
import com.springcloud.entity.Users;
import com.springcloud.repository.UsersRepository;
import com.springcloud.service.UsersService;

/**
 * 模型层的实现类，实现用户模块的方法
 * @author jyy
 *
 */
@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	
	@Override
	public Users login(Integer userId, String userPassword, Integer jdictionId) {
		
		return this.usersRepository.login(userId, userPassword, jdictionId);
	}

	
	@Override
	public Users insert(Users users) {
		
		return this.usersRepository.save(users);
	}

	
	@Transactional
	@Override
	public Page<Users> select(Users users, Integer pageNumber) {
		//根据查询数据，创建动态条件
		@SuppressWarnings("serial")  //此注解取消掉黄线警告
		Specification<Users> pecificatio = new Specification<Users>() {

			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				//创建List集合，用于保存所用的where条件
				List<Predicate> whereList = new ArrayList<>();
				
				//根据Users中的条件，动态的创建查询条件
				//模糊查询（名字）
				if(users.getUserName()!=null && !users.getUserName().trim().equals("")) {
					whereList.add(criteriaBuilder.like(root.get("userName"),"%"+users.getUserName()+"%"));
				}
				//用户状态的查询条件
				if(users.getUserStatus()!=-1) {
					whereList.add(criteriaBuilder.equal(root.get("userStatus"), users.getUserStatus()));
				}
				//将所有的条件以and关系连接到一起，并返回
				return criteriaBuilder.and(whereList.toArray(new Predicate[whereList.size()]));
			}
			
		};
		//创建JPa的分页信息
		PageRequest of = PageRequest.of(pageNumber, PageUtils.PAGE_ROW_COUNT);
		
		return this.usersRepository.findAll(pecificatio,of);
	}

	
	@Transactional
	@Override
	public Integer updateStatus(Integer userId, Integer userStatus) {
		
		return this.usersRepository.updateStatus(userId, userStatus);
	}

	
	@Transactional
	@Override
	public Users selectById(Integer userId) {
		
		return this.usersRepository.findById(userId).get();
	}

	@Transactional
	@Override
	public Integer update(Users users) {
		
		return this.usersRepository.update(users);
	}


	@Transactional
	@Override
	public Integer updatePhoto(Users users) {
		
		return this.usersRepository.updatePhoto(users);
	}


	@Transactional
	@Override
	public Integer updatePassword(Users users) {
		
		return this.usersRepository.updatePassword(users);
	}


	@Transactional
	@Override
	public Integer updateName(Users users) {
		
		return this.usersRepository.updateName(users);
	}

}
