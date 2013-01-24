package com.spark.b2c.publish.JointVenture.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.JointSettlementStatus;

public interface JointSettlementInfo {

	public GUID getId();
	public String getSupplierName();
	public String getNamePY();
	public String getShortName();
	public String getSupplierNo();
	public GUID getSupplierId();
	public long getBeginDate();
	public long getEndDate();
	public double getSalesAmount();
	public double getPercentageAmount();
	public double getAdjustAmount();
	public double getMolingAmount();
	public double getAmount();
	public double getPaidAmount() ;
	public GUID getCreatorId();
	public String getCreator();
	public long getCreateDate();
	public JointSettlementStatus getStatus();
	public String getSheetNo();
	public String getRemark();
	public String getDenyReason();
	public JointSettlementInfoItem[] getItems();
	public JointSettlementLog[] getLogs();
}
