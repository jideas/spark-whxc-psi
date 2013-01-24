package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.ApprovalConfig.Mode;

/**
 * 
 * <p>������¼</p>
 *


 *
 
 * @version 2012-5-21
 */
public interface ApprovalLog{
	
	public GUID getId();
	
	/**
	 * ��������
	 * 
	 * @return long
	 */
	public long getApprovalDate();
	
	/**
	 * ���ݺ�
	 * 
	 * @return String
	 */
	public String getBillsNumber();
	
	/**
	 * ��������
	 * 
	 * @return String
	 */
	public String getBusTypeName();
	
	/**
	 * ���
	 * 
	 * @return double
	 */
	public double getAmount();
	
	/**
	 * �Ƶ�����
	 * 
	 * @return long
	 */
	public long getCreateDate();
	
	/**
	 * �Ƶ���
	 * 
	 * @return String
	 */
	public String getCreatePerson();
	
	/**
	 * ��������
	 * 
	 * @return Mode
	 */
	public Mode getMode();
	
}
