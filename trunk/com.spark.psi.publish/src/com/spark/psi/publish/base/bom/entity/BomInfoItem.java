package com.spark.psi.publish.base.bom.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * Bom表材料明细条目信息<BR>
 * 
 */
public interface BomInfoItem {
	
	public GUID getMaterialId();

	public String getMaterialName();

	public String getMaterialSpec();

	public String getMaterialCode();

	public String getMaterialNo();

	public String getMaterialUnit();

	public double getCount();

	public double getLossRate();

	public double getRealCount();
}
