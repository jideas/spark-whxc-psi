package com.spark.psi.publish.inventory.entity;

import com.jiuqi.dna.core.type.GUID;

public interface ReportLossInfoItemDet {
	
	public GUID getId();
	
	public GUID getShelfId();
	
	public int getShelfNo();
	
	public int getTiersNo();
	
	public GUID getStockId();
	
	public double getCount();
	
	public long getProduceDate();
	
	public GUID getReportLossItemId();
	
//	public GUID getSheetId();
	
}
