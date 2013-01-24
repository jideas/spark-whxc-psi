package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.ApprovalConfig.Mode;

/**
 * 
 * <p>审批记录</p>
 *


 *
 
 * @version 2012-5-21
 */
public interface ApprovalLog{
	
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
	
	/**
	 * 单据类型
	 * 
	 * @return Mode
	 */
	public Mode getMode();
	
}
