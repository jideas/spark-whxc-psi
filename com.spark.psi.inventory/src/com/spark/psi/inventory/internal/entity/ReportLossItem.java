package com.spark.psi.inventory.internal.entity;

import com.jiuqi.dna.core.type.GUID;

public class ReportLossItem {
	private GUID id;
	private GUID reportLossSheetId;
	private GUID goodsId;
	private String goodsCode;
	private String goodsNumber;
	private String goodsName;
	private String goodsUnit;
	private String goodsSpec;
	private double reportCount;
	private int scale;
	private String reportReason;
	private String reportType;
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
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(String goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	
	
}
