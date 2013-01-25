package com.spark.psi.publish.inventory.sheet.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

public interface CheckedOutInfo {

	/**
	 * 获取出库单id
	 */
	public GUID getSheetId();
	public String getSheetNo(); 

	/**
	 * 获取出库时间
	 */
	public long getCheckoutDate(); 

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

	/**
	 * 获取客户或者供应商ID
	 */
	public GUID getPartnerId();

	/**
	 * 获取客户或者供应商名称
	 */
	public String getPartnerName();

	/**
	 * 获取备注信息
	 */
	public String getRemark();
	
	/**
	 * 物品来源
	 */
	public String getGoodsFrom();
	
	/**
	 * 物品用途
	 */
	public String getGoodsUse(); 

	/**
	 * 获取出库商品列表（销售出库或者采购退货出库）
	 */
	public CheckedGoodsItem[] getCheckedGoodsItems();

	/**
	 * 获取出库物品列表（其他物品出库）
	 */
	public CheckKitItem[] getCheckKitItems();

	/**
	 * 获取确认处理记录列表
	 */
	public CheckedOutItem[] getCheckedOutItems(); 
}
