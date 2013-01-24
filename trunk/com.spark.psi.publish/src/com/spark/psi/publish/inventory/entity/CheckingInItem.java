package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;

/**
 * 入库单列表项目<br>
 * 查询方法：ListEntry<CheckingInItem>+GetCheckingInListKey
 * ok
 */
public interface CheckingInItem {

	/**
	 * 获取入库单id
	 */
	public GUID getSheetId(); 
	/**
	 * 获取创建日期
	 */
	public long getCreateDate();

	/**
	 * 获取计划入库时间
	 */
	public long getPlanCheckinDate();

	/**
	 * 获取上次入库时间
	 */
	public long getLastCheckinDate();

	/**
	 * 获取相关单据编号
	 */
	public String getRelaBillsNo();

	/**
	 * 获取入库仓库id
	 */
	public GUID getStoreId();
	
	public String getRemark();

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

	/**
	 * 获取入库确认人
	 */
	public GUID[] getCheckinEmployeeIds();

	/**
	 * 获取入库确认人
	 */
	public String getCheckinEmployees();

}
