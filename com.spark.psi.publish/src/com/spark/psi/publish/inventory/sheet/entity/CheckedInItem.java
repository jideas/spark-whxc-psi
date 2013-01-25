package com.spark.psi.publish.inventory.sheet.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;

public interface CheckedInItem {

	/**
	 * 获取入库单id
	 */
	public GUID getSheetId();

	public String getSheetNo();

	/**
	 * 获取创建日期
	 */
	public long getCreateDate();

	/**
	 * 获取入库时间
	 */
	public long getCheckinDate(); 

	/**
	 * 获取相关单据编号
	 */
	public String getRelaBillsNo();

	/**
	 * 获取入库仓库id
	 */
	public GUID getStoreId();

	/**
	 * 获取入库仓库名称
	 */
	public String getStoreName();

	/**
	 * 获取入库单状态
	 */
	public CheckingInStatus getStatus();

	/**
	 * 获取入库类型
	 */
	public CheckingInType getType();
	
	public GUID getCheckedInPerson();
	public String getCheckedInPersonName();

}
