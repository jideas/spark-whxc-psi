package com.spark.psi.query.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.CheckingInType;

/**
 * ������ⵥ��ѯʵ��<BR>
 * ��ѯ������<BR>
 * MaterialsCheckInListEntity+GetMaterialsCheckInListKey;
 *
 */
public interface MaterialsCheckInItem {

	public GUID getSheetId();
	public String getSheetNo();
	public GUID getGoodsId();
	public String getGoodsCode();
	public String getGoodsName();
	public String getUnit();
	public double getPrice();
	public double getAmount();
	public double getCount();
	public double getStandardCost();
	public double getStandardAmount();
	public GUID getPurchaseSheetId();
	public String getPurchaseSheetNo();
	public long getCheckinDate();
	public CheckingInType getCheckingInType();
	public GUID getPartnerId();
	public String getPartnerName();
	public String getPartnerNo();
}
