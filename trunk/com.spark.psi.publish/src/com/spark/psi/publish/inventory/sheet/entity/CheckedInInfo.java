package com.spark.psi.publish.inventory.sheet.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.inventory.entity.CheckingGoodsItem;

public interface CheckedInInfo {

	/**
	 * 获取入库单id
	 */
	public GUID getSheetId(); 
	public String getSheetNo(); 

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
	 * 获取入库类型
	 */
	public CheckingInType getType();

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
	 * 获取入库商品列表（采购入库或者销售退货入库）
	 */
	public CheckingGoodsItem[] getCheckingGoodsItems();

	/**
	 * 获取入库物品列表（其他物品如入库）
	 */
	public CheckKitItem[] getCheckKitItems();  

	/**
	 * 获取零采入库时，付款方式
	 */
	public EnumEntity getDealingsWay();
	
	/**
	 * 物品来源
	 */
	public String getGoodsFrom();
 
}
