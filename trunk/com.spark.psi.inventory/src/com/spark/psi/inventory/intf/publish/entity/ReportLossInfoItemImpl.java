package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.inventory.entity.ReportLossInfoItem;
import com.spark.psi.publish.inventory.entity.ReportLossInfoItemDet;

public class ReportLossInfoItemImpl implements ReportLossInfoItem {

	private GUID id;
	private GUID reportLossSheetId;
	private GUID goodsId;
	private String goodsName;
	private String goodsCode;
	private String goodsNo;
	private String goodsUnit;
	private String goodsSpec;
	private int scale;
	private double reportCount;
	private String reportReason;
	private ReportLossInfoItemDet[] itemDets;
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getReportLossSheetId() {
		return reportLossSheetId;
	}
	public void setReportLossSheetId(GUID reportLossSheetId) {
		this.reportLossSheetId = reportLossSheetId;
	}
	public GUID getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(GUID goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public String getGoodsSpec() {
		return goodsSpec;
	}
	public void setGoodsSpec(String goodsSpec) {
		this.goodsSpec = goodsSpec;
	}
	public double getReportCount() {
		return reportCount;
	}
	public void setReportCount(double reportCount) {
		this.reportCount = reportCount;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public ReportLossInfoItemDet[] getItemDets() {
		return itemDets;
	}
	public void setItemDets(ReportLossInfoItemDet[] itemDets) {
		this.itemDets = itemDets;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	
	
}
