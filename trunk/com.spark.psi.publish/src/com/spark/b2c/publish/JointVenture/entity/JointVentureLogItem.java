package com.spark.b2c.publish.JointVenture.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * 联营信息记录<BR>
 * 查询方法：<BR>
 * JointVentureLogListEntity+GetJointVentureLogListKey;
 * 
 *
 */
public interface JointVentureLogItem {

	public GUID getId();
	public GUID getSupplierId();
	public GUID getMaterialId();
	public String getMaterialName();
	public String getMaterialCode();
	public String getMaterialNo();
	public String getMaterialUnit();
	public long getBeginDate();
	public long getEndDate();
	public double getPercentage();
}
