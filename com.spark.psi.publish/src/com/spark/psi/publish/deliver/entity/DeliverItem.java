package com.spark.psi.publish.deliver.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.DeliverStatus;

/**
 * 
 * 配送单列表<BR>
 * 查询方法：<BR>
 * GetDeliverListKey+DeliverListEntity;
 *
 */
public interface DeliverItem {

	public GUID getId();
	public String getSheetNo();
	public String getRemark();
	public GUID getCreatorId();
	public String getCreator();
	public long getCreateDate();
	public long getFinishDate();
	public GUID getStationId();
	public String getStationName();
	public String getAddress();
	public DeliverStatus getStatus();
	public GUID getDeliverPersonId();
	public String getDeliverPerson();
	public long getDeliverDate();
	public GUID getConsigneeId();
	public String getConsignee();
	public long getConsigneeDate();
	public int getDeliveredPackageCount();
	public int getReceivedPackageCount();
	public String getDescription();
	public String getFormula();
	public GUID getHandlerId();
	public String getHandler();
	public long getHandleDate();
	public long getPlanDate();


}
