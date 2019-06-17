package com.springcloud.service;

import org.springframework.data.domain.Page;

import com.springcloud.entity.Users;

/**
 * ģ�Ͳ�Ľӿڣ����ڶ����û�ģ��ķ���
 * @author jyy
 *
 */
public interface UsersService {

	/**
	 * 	��֤�û��Ƿ��½�ɹ�
	 * 
	 * @param userId	�û����
	 * @param userPassword	�û�����
	 * @param jdictionId	Ȩ�ޱ��
	 * @return  �ɹ������û�������Ϣ��ʧ�ܷ���null
	 */
	public abstract Users login(Integer userId,String userPassword,Integer jdictionId);
	
	/**
	 * 	����µ��û���Ϣ
	 * 
	 * @param users	�µĵ��û�
	 * @return	��ӳɹ�����Users���͵�ʵ�������򷵻�null
	 */
	public abstract Users insert(Users users);
	
	/**
	 * ��ѯ�����������û���Ϣ
	 * 
	 * @param user    ��ѯ�û�
	 * @param pageNumber  ��ѯҳ��
	 * @return  		�ɹ�����Users���͵�ʵ�������򷵻�null
	 */
	public abstract Page<Users> select(Users user,Integer pageNumber);
	
	/**
	 * �޸�ָ���û���״̬
	 * 
	 * @param userId  �û����
	 * @param userStatus  �û�״̬
	 * @return  �޸ĳɹ����ش���������������򷵻�0
	 */
	public abstract Integer updateStatus(Integer userId,Integer userStatus);

	/**
	 * ���ݱ�Ų�ѯָ���û�
	 * 
	 * @param userId   �û����
	 * @return  �ɹ�����com.springcloud.entity.Users���͵�ʵ�������򷵻�null
	 */
	public abstract Users selectById(Integer userId);
	
	/**
	 * �޸�ָ����ŵ��û���Ϣ
	 * 
	 * @param users  �޸ĵ��û���Ϣ
	 * @return  �ɹ����ش���0�����������򷵻�null
	 */
	public abstract Integer update(Users users);
	
	/**
	 * �޸�ָ����ŵ��û�ͷ��
	 * 
	 * @param users �޸ĵ��û���Ϣ
	 * @return  �ɹ����ش���0�����������򷵻�null
	 */
	public abstract Integer updatePhoto(Users users);
	
	/**
	 * �޸�ָ����ŵ��û�����
	 * 
	 * @param users �޸ĵ��û���Ϣ
	 * @return  �ɹ����ش���0�����������򷵻�null
	 */
	public abstract Integer updatePassword(Users users);
	
	/**
	 * �޸�ָ����ŵ��û��ǳ�
	 * 
	 * @param users �޸ĵ��û���Ϣ
	 * @return  �ɹ����ش���0�����������򷵻�null
	 */
	public abstract Integer updateName(Users users);
}
