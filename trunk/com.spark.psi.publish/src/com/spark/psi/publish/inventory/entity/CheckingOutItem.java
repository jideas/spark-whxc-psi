package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;

/**
 * 出库需求列表项目<br>
 * 查询方法：ListEntry<CheckingOutItem>+GetCheckingOutListKey
 * ok
 */
public interface CheckingOutItem {

	/**
	 * 获取出库单id
	 */
	public GUID getSheetId(); 

	/**
	 * 获取创建日期
	 */
	public long getCreateDate();

	/**
	 * 获取计划出库时间
	 */
	public long getPlanCheckoutDate();

	/**
	 * 获取上次出库时间
	 */
	public long getLastCheckoutDate();

	/**
	 * 获取相关单据编号
	 */
	public String getRelaBillsNo();

	/**
	 * 获取出库仓库id
	 */
	public GUID getStoreId();

	/**
	 * 获取出库仓库名称
	 */
	public String getStoreName();

	/**
	 * 获取出库单状态
	 */
	public CheckingOutStatus getStatus();

	/**
	 * 获取出库类型
	 */
	public CheckingOutType getType();

	/**
	 * 获取出库确认人
	 */
	public GUID[] getCheckoutEmployeeIds();

	/**
	 * 获取出库确认人
	 */
	public String getCheckoutEmployees();
}