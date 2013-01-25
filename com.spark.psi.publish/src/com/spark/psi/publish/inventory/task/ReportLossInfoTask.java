package com.spark.psi.publish.inventory.task;

import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ReportLossStatus;
import com.spark.psi.publish.inventory.entity.ReportLossInfoItemDet;

public class ReportLossInfoTask extends Task<ReportLossInfoTask.Method> {
	
	public enum Method {
		Create, Modify
	}
	
	private GUID id;
	private GUID storeId;
	private String remark;
	private GUID creatorId;
	private String creator;
	private long createDate;
	private String sheetNo;
	private ReportLossStatus status;
	
	private Item[] items;
	
	
	
	public GUID getId() {
		return id;
	}
	public void setId(GUID id) {
		this.id = id;
	}
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public GUID getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public Item[] getItems() {
		return items;
	}
	public void setItems(Item[] items) {
		this.items = items;
	}
	public ReportLossStatus getStatus() {
		return status;
	}	public void setStatus(ReportLossStatus status) {
		this.status = status;
	}
	public String getSheetNo() {
		return sheetNo;
	}
	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}

	public interface Item {
//		private GUID id;
//		private GUID goodsId;
//		private String goodsCode;
//		private String goodsNumber;
//		private String goodsName;
//		private String goodsUnit;
//		private String goodsSpec;
//		private double reportCount;
//		private String reportReason;
		
		public GUID getId();
		public GUID getGoodsId();
		public String getGoodsCode();
		public String getGoodsNumber();
		public String getGoodsName();
		public String getGoodsUnit();
		public String getGoodsSpec();
		public int getScale();
		public double getReportCount();
		public String getReportReason();
		public ReportLossInfoItemDet[] getItemDets();
	}
	
}
