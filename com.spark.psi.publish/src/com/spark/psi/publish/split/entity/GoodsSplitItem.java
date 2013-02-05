package com.spark.psi.publish.split.entity;

import com.jiuqi.dna.core.type.GUID;

public interface GoodsSplitItem {
	public GUID getRECID();

	public String getBillNo();

	public String getCreator();

	public GUID getCreatorId();

	public long getCreateDate();

	public String getApprovalPerson();

	public GUID getApprovalPersonId();

	public long getApprovalDate();

	public String getDistributPerson();

	public GUID getDistributPersonId();

	public long getDistributDate();

	public String getStatus();

	public String getRejectReason();

	public String getRemark();

	public GUID getStoreId();

	public long getFinishDate();
}
