package com.spark.psi.publish.split.entity;

import java.util.List;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.split.constant.GoodsSplitStatus;

public interface GoodsSplitInfo {

	public List<GoodsSplitDet_Goods> getGoodsDets();

	public List<GoodsSplitDet_Material> getMaterialDets();

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

	public GoodsSplitStatus getStatus();

	public String getRejectReason();

	public String getRemark();

	public GUID getStoreId();

	public long getFinishDate();
}
