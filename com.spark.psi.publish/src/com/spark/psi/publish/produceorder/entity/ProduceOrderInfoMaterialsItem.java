package com.spark.psi.publish.produceorder.entity;

import com.jiuqi.dna.core.type.GUID;

/**
 * 生产订单材料明细
 *
 */
public interface ProduceOrderInfoMaterialsItem {

	public GUID getId();
	public GUID getBillsId();
	public GUID getMaterialId();
	public String getMaterialCode();
	public String getMaterialNo();
	public String getMaterialName();
	public String getMaterialSpec() ;
	public String getUnit();
	public int getMaterialScale();
	public double getCount();
	public double getReceivedCount() ;
	public double getReturnedCount();
	public GUID getStoreId();
	public String getStoreName();
}
