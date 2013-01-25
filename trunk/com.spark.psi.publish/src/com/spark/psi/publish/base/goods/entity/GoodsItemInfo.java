package com.spark.psi.publish.base.goods.entity;

/**
 * 商品明细条目数据 <BR>
 * 查询方法：<br>
 * (1)根据条目ID查询，返回GoodsItem对象
 */
public class GoodsItemInfo {

	/**
	 * 商品基本信息
	 */
	protected GoodsInfo baseInfo;

	/**
	 * 条目数据
	 */
	protected GoodsItemData itemData;

	/**
	 * 获取商品基本信息
	 * 
	 * @return
	 */
	public GoodsInfo getBaseInfo() {
		return baseInfo;
	}

	/**
	 * 获取商品条目数据对象
	 * 
	 * @return
	 */
	public GoodsItemData getItemData() {
		return itemData;
	}

	
}
