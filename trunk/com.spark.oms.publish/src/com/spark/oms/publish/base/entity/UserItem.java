package com.spark.oms.publish.base.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.oms.publish.EnabledStatus;
import com.spark.oms.publish.UserStatus;

/**
 * �û��б�ְ�ܲ��ţ������Ŷӣ�
 */
public interface UserItem {
	
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
	
	
	//�����ص�
	public String getWorkingProvince();
	public String getWorkingCity();
	public String getWorkingCounty();
	
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
}