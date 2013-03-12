package com.spark.psi.publish.inventory.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.LimitKey;
import com.spark.psi.publish.ShelfLifeWarningType;

/**
 * ��ѯ������Ԥ�������б�
 * <BR>��ѯ������List<ShelfLifeWarningMaterialsItem>+GetShelfLifeWarningMaterialsKey;
 * @author tc
 * 
 */
public class GetShelfLifeWarningMaterialsKey extends LimitKey{

	public GetShelfLifeWarningMaterialsKey(int offset, int count,
			boolean queryTotal) {
		super(offset, count, queryTotal);
	}
	private GUID storeId;
	private ShelfLifeWarningType shelfLifeWarningType;
	public GUID getStoreId() {
		return storeId;
	}
	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}
	public ShelfLifeWarningType getShelfLifeWarningType() {
		return shelfLifeWarningType;
	}
	public void setShelfLifeWarningType(ShelfLifeWarningType shelfLifeWarningType) {
		this.shelfLifeWarningType = shelfLifeWarningType;
	}
	
	
}
