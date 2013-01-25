package com.spark.psi.publish.base.approval;

import com.jiuqi.dna.core.type.GUID;


/**
 * 
 * <p>ҵ��������¼</p>
 *


 *
 
 * @version 2012-3-22
 */
public interface ApprovalRecordItem {
	
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
	
}
