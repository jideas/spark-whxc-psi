package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.SysParamKey;

/**
 * �⻧��Ϣ<br>
 * ��ѯ����������GUID��ѯTenant����
 */
public interface Tenant {
//
//	protected GUID id;

//	/**
//	 * �Ƿ���ֱ��
//	 */
//	protected boolean directSupply;
//
//	/**
//	 * @return the id
//	 */
//		return id;
//	}
//
	/**
	 * �Ƿ���ֱ��
	 * @return the directSupply
	 */
	public boolean isDirectSupply();
	
	/**
	 * �⻧ID
	 */
	public GUID getId();
	
	public boolean getSysParamstatus(SysParamKey key);

	/**
	 * �⻧����
	 * 
	 * @return String
	 */
	public String getTitle();
	
	/**
	 * �������������Ϣ
	 * 
	 * @return ApprovalConfig
	 */
	public ApprovalConfig getApprovalConfig();
	
	/**
	 * ��ÿ����û���
	 * 
	 * @return int
	 */
	public int getUserCount();
	
	/**
	 * ��ҵ�汾
	 * 
	 * @return String
	 */
	public String getProduct();
	
	/**
	 * ����ʼ����
	 * 
	 * @return long
	 */
	public long getStartDate();
	
	/**
	 * ����������
	 * 
	 * @return long
	 */
	public long getEndDate();
	
}
