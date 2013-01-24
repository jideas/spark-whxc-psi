package com.spark.psi.base;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryWarningType;
import com.spark.psi.publish.MaterialsStatus;

/**
 * ���϶���<br>
 * ��ѯ������<br>
 * (1)����ID��ѯMaterials����
 * (2)....
 */
public interface Materials {
	public GUID getId();
	public String getMaterialCode();
	public String getMaterialName();
	public String getNamePY();
	public GUID getCategoryId();
	public String getRemark();
	public boolean isCanDalete();
	public boolean isRefFlag();
	public InventoryWarningType getInventoryWarningType();
	public long getCreateDate();
	public long getLastModifyDate();
	public String getLastModifyPerson();
	public GUID getCreatorId() ;
	public String getCreator();
	public MaterialsStatus getStatus();
	public int getShelfLife();
	public int getWarningDay();
	public boolean isJointVenture();
	public GUID getSupplierId();
	public double getPercentage();
}
