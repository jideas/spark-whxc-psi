package com.spark.psi.publish.base.bom.entity;

import com.jiuqi.dna.core.type.GUID;

public interface BomHistoryItem {

	public GUID getId();

	public String getBomNo();

	public String getCreator();

	public String getApprovor();

	public String getIneffectDate();

	public String getOuteffectDate();

	public String getIneffector();

	public String getOuteffector();
}
