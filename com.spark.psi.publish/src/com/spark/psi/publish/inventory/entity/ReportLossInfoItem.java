package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ReportLossInfoItem {
	/**
	 * 
	 * @return
	 */
	public GUID getId();
	/**
	 * 所属的报损单ID
	 * @return
	 */
	public GUID getReportLossSheetId();
	
	/**
	 * 商品ID
	 * @return
	 */
	public GUID getGoodsId();
	
	/**
	 * 商品编号
	 * @return
	 */
	public String getGoodsCode();
	
	/**
	 * 条码
	 * @return
	 */
	public String getGoodsNo();
	
	/**
	 * 商品名称
	 * @return
	 */
	public String getGoodsName();
	
	/**
	 * 商品单位
	 * @return
	 */
	public String getGoodsUnit();
	
	/**
	 * 商品规格
	 * @return
	 */
	public String getGoodsSpec();
	
	/**
	 * 报损数量
	 * @return
	 */
	public double getReportCount();
	
	public int getScale();
	
	// 目前只有材料报损
//	public ReportLossType getReportLossType();
	
	public String getReportReason();
	
	public ReportLossInfoItemDet[] getItemDets();
}
