package com.spark.psi.publish.base.materials.entity;

/**
 * 材料明细条目数据 <BR>
 * 查询方法：<br>
 * (1)根据条目ID查询，返回MaterialsItem对象
 */
public class MaterialsItemInfo {

	/**
	 * 商品基本信息
	 */
	protected MaterialsInfo baseInfo;

	/**
	 * 条目数据
	 */
	protected MaterialsItemData itemData;

	/**
	 * 获取商品基本信息
	 * 
	 * @return
	 */
	public MaterialsInfo getBaseInfo() {
		return baseInfo;
	}

	/**
	 * 获取商品条目数据对象
	 * 
	 * @return
	 */
	public MaterialsItemData getItemData() {
		return itemData;
	}

	
}
