package com.spark.psi.publish.base.config.entity;

import com.jiuqi.dna.core.type.GUID;


public interface TenantInfo  {
	
	public GUID getId();

	public String getTitle();
	
	/**
	 * �Ƿ��ѿ���ֱ��ģʽ
	 */
	public boolean isDirectDelivery();
	
	/**
	 * ��ÿ����û���
	 * 
	 * @return int
	 */
	public int getUserCount();
	
	/**
	 * �Ƿ��Ѿ���ʼ��������
	 * 
	 * @return boolean
	 */
	public boolean isDealingsInited();
	
//	/**
//	 */
//	
//	public String getSystemName();
//	
}
