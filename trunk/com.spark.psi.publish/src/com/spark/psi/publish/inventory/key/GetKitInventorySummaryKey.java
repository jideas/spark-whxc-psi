package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;


/**
 * 查询指定物品在各个仓库的库存情况
 */
public class GetKitInventorySummaryKey {

	/**
	 * 物品名称
	 */
	private String kitName;

	/**
	 * 物品描述
	 */
	private String kitDesc;

	/**
	 * 单位
	 */
	private String unit;
	
	/**
	 * 仓库ID
	 */
	private GUID storeId;

	/**
	 * 构造函数
	 * 
	 * @param kitName
	 * @param kitDesc
	 * @param unit
	 */
	public GetKitInventorySummaryKey(String kitName, String kitDesc, String unit) {
		super();
		this.kitName = kitName;
		this.kitDesc = kitDesc;
		this.unit = unit;
	}

	/**
	 * @return the kitName
	 */
	public String getKitName() {
		return kitName;
	}

	/**
	 * @return the kitDesc
	 */
	public String getKitDesc() {
		return kitDesc;
	}

	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public GUID getStoreId() {
		return storeId;
	}

}
