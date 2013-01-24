package com.spark.oms.publish.base.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.EnabledStatus;
import com.spark.oms.publish.UserStatus;
import com.spark.oms.publish.UserType;

/**
 * �����û����ñ༭ʹ��
 * 
 *
 */
public interface UserInfo {
	
	/**
	 * �û�(����)id
	 * @return
	 */
	public GUID getRECID();

	/**
	 * �û�(����)���
	 * @return
	 */
	public String getUserCode();
	
	/**
	 * �û�(����)����
	 * @return
	 */
	public String getUserName();
	
	/**
	 * ����
	 * @return
	 */
	public String getDepartment();
	
	/**
	 * ��λ
	 * @return
	 */
	public String getDuty();
	
	/**
	 * �ֻ�
	 * @return
	 */
	public String getMobilePhone();
	
	/**
	 * �����ʼ�
	 * @return
	 */
	public String getEmail();
	
	/**
	 * ��ְ����
	 */
	public long getEntryDate();
	
	/**
	 * ����ְ��
	 * @return
	 */
	public String getSalesRank();
	
	/**
	 * �����ص�
	 * @return
	 */
	public String getWorkingProvince();
	/**
	 * �����ص�
	 * @return
	 */
	public String getWorkingCity();
	/**
	 * �����ص�
	 * @return
	 */
	public String getWorkingCounty();
	
	/**
	 * ��ְ�ص�
	 * @return
	 */
	public String getProvince();
	/**
	 * ��ְ�ص�
	 * @return
	 */
	public String getCity();
	/**
	 * ��ְ�ص�
	 * @return
	 */
	public String getCounty();
	
	/**
	 * �û���ְ״̬
	 * @return
	 */
	public UserStatus getUserStatus();
	
	/**
	 * �û�����״̬
	 * @return
	 */
	public EnabledStatus getEnabledStatus();
	
	/**
	 * ������
	 * @return
	 */
	public GUID getCreatePerson();
	/**
	 * ������
	 * @return
	 */
	public String getCreatePersonName();
	/**
	 * ����ʱ��
	 * @return
	 */
	public long getCreateDate();

	/**
	 * ������
	 * @return
	 */
	public GUID getUpdatePerson();
	/**
	 * ������
	 * @return
	 */
	public String getUpdatePersonName();
	/**
	 * ����ʱ��
	 * @return
	 */
	public long getUpdateDate();
	
	/**
	 * ��ְ����
	 */
	public long getTerminalDate();
	
	/**
	 * ��¼����
	 */
	public String getPassWord();
	
	/**
	 * �û���ɫȨ��
	 * @return
	 */
	public RoleItem[] getRoleItems();
	
	/**
	 * �û� ����
	 * @return
	 */
	public UserType getUserType();
	
	public String getWorkingArea();
	
	public String getArea();
}