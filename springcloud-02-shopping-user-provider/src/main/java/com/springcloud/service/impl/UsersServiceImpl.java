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
 * ģ�Ͳ��ʵ���࣬ʵ���û�ģ��ķ���
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
		//���ݲ�ѯ���ݣ�������̬����
		@SuppressWarnings("serial")  //��ע��ȡ�������߾���
		Specification<Users> pecificatio = new Specification<Users>() {

			@Override
			public Predicate toPredicate(Root<Users> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				//����List���ϣ����ڱ������õ�where����
				List<Predicate> whereList = new ArrayList<>();
				
				//����Users�е���������̬�Ĵ�����ѯ����
				//ģ����ѯ�����֣�
				if(users.getUserName()!=null && !users.getUserName().trim().equals("")) {
					whereList.add(criteriaBuilder.like(root.get("userName"),"%"+users.getUserName()+"%"));
				}
				//�û�״̬�Ĳ�ѯ����
				if(users.getUserStatus()!=-1) {
					whereList.add(criteriaBuilder.equal(root.get("userStatus"), users.getUserStatus()));
				}
				//�����е�������and��ϵ���ӵ�һ�𣬲�����
				return criteriaBuilder.and(whereList.toArray(new Predicate[whereList.size()]));
			}
			
		};
		//����JPa�ķ�ҳ��Ϣ
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
