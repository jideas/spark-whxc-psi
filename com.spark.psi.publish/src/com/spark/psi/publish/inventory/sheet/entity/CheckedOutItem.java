package com.spark.psi.publish.inventory.sheet.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

public interface CheckedOutItem {

	/**
	 * 获取出库单id
	 */
	public GUID getSheetId();

	public String getSheetNo();

	/**
	 * 获取出库时间
	 */
	public long getCheckOutDate();

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
	 * 获取出库类型
	 */
	public CheckingOutType getType();

	public GUID getCheckedOutPerson();

	public String getCheckedOutPersonName();

	public String getTakerUnit();

	public String getTaker();

	public String getVouchersNumber();

}
