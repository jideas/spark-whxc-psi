package com.spark.psi.publish.base.bom.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.BOM_Constant.BOM_STATUS;

/**
 * 
 * Bom¡–±Ì
 * 
 */
public interface BomItem {
	public GUID getId();

	public String getRemark();

	public GUID getGoodsItemId();

	public String getGoodsName();

	public String getGoodsNo();

	public String getGoodsCode();

	public String getGoodsSpec();

	public String getGoodsUnit();

	public String getBomNo();

	public GUID getCreatorId();

	public String getCreator();

	public String getCreateDate();

	public GUID getApprovePerson();

	public String getApprovePersonName();

	public BOM_STATUS getStatus();
}
