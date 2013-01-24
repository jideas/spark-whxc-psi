package com.spark.psi.inventory.internal.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.inventory.internal.entity.ReportLossItem;

public class ReportLossItemTask extends Task<ReportLossItemTask.Method> {
	public enum Method {
		Create, Modify
	}
	
	private ReportLossItem[] items;
	
	
	
	public ReportLossItem[] getItems() {
		return items;
	}
	public void setItems(ReportLossItem[] items) {
		this.items = items;
	}
//	private GUID id;
//	private GUID sheetId;
//	private GUID goodsId;
//	private String goodsCode;
//	private String goodsNumber;
//	private String goodsName;
//	private String goodsUnit;
//	private String goodsSpec;
//	private double reportCount;
//	private String reportReason;
//	public GUID getId() {
//		return id;
//	}
//	public void setId(GUID id) {
//		this.id = id;
//	}
//	public GUID getGoodsId() {
//		return goodsId;
//	}
//	public void setGoodsId(GUID goodsId) {
//		this.goodsId = goodsId;
//	}
//	public String getGoodsCode() {
//		return goodsCode;
//	}
//	public void setGoodsCode(String goodsCode) {
//		this.goodsCode = goodsCode;
//	}
//	public String getGoodsNumber() {
//		return goodsNumber;
//	}
//	public void setGoodsNumber(String goodsNumber) {
//		this.goodsNumber = goodsNumber;
//	}
//	public String getGoodsName() {
//		return goodsName;
//	}
//	public void setGoodsName(String goodsName) {
//		this.goodsName = goodsName;
//	}
//	public String getGoodsUnit() {
//		return goodsUnit;
//	}
//	public void setGoodsUnit(String goodsUnit) {
//		this.goodsUnit = goodsUnit;
//	}
//	public String getGoodsSpec() {
//		return goodsSpec;
//	}
//	public void setGoodsSpec(String goodsSpec) {
//		this.goodsSpec = goodsSpec;
//	}
//	public double getReportCount() {
//		return reportCount;
//	}
//	public void setReportCount(double reportCount) {
//		this.reportCount = reportCount;
//	}
//	public String getReportReason() {
//		return reportReason;
//	}
//	public void setReportReason(String reportReason) {
//		this.reportReason = reportReason;
//	}
//	public GUID getSheetId() {
//		return sheetId;
//	}
//	public void setSheetId(GUID sheetId) {
//		this.sheetId = sheetId;
//	}
//	
	
}
