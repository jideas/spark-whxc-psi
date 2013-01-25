package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutStatus;
import com.spark.psi.publish.CheckingOutType;
import com.spark.psi.publish.inventory.sheet.entity.CheckKitItem;
import com.spark.psi.publish.inventory.sheet.entity.CheckedOutItem;

/**
 * 出库需求详情<br>
 * 查询方法：通过出库单ID查询CheckingOutInfo对象
 * ok
 */
public interface CheckingOutInfo {

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
	 * 获取中止原因（中止状态）
	 */
	public String getStopReason();
	
	public boolean isStoped();

	/**
	 * 获取出库商品列表（销售出库或者采购退货出库）
	 */
	public CheckingGoodsItem[] getCheckingGoodsItems();

	/**
	 * 获取出库物品列表（其他物品出库）
	 */
	public CheckKitItem[] getCheckKitItems();

	/**
	 * 获取确认处理记录列表
	 */
	public CheckedOutItem[] getCheckedOutItems();

	/**
	 * 确认出库记录
	 */
}