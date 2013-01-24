package com.spark.psi.publish.base.approval;

import com.jiuqi.dna.core.type.GUID;


/**
 * 
 * <p>业务审批记录</p>
 *


 *
 
 * @version 2012-3-22
 */
public interface ApprovalRecordItem {
	
	public GUID getId();
	
	/**
	 * 审批日期
	 * 
	 * @return long
	 */
	public long getApprovalDate();
	
	/**
	 * 单据号
	 * 
	 * @return String
	 */
	public String getBillsNumber();
	
	/**
	 * 单据类型
	 * 
	 * @return String
	 */
	public String getBusTypeName();
	
	/**
	 * 金额
	 * 
	 * @return double
	 */
	public double getAmount();
	
	/**
	 * 制单日期
	 * 
	 * @return long
	 */
	public long getCreateDate();
	
	/**
	 * 制单人
	 * 
	 * @return String
	 */
	public String getCreatePerson();
	
}
