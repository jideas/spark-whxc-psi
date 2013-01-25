package com.spark.psi.query.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingOutType;

/**
 * ���ϳ��ⵥ��ѯʵ��<BR>
 * ��ѯ������<BR>
 * MaterialsCheckOutListEntity+GetMaterialsCheckOutListKey;
 *
 */
public interface MaterialsCheckOutItem {

	public GUID getSheetId();
	public String getSheetNo();
	public GUID getGoodsId();
	public String getGoodsCode();
	public String getGoodsName();
	public String getUnit();
	public double getAmount();
	public double getCount();
	public double getCost();
	public CheckingOutType getCheckingOutType();
	public long getCreateDate();
	public String getDepartment();
	public GUID getProduceSheetId();
	public String getProduceSheetNo();
}
