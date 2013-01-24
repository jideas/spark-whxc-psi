package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * �޸Ŀ���������������ۡ��������ã�
 *
 */
public class InventoryLockTask extends SimpleTask {
	private GUID storeId;
	private GUID stockId;
	
	
	/**
	 * ��������
	 */
	private Double lockedCount;
	private InventoryType inventoryType = InventoryType.Materials;
	
	/**
	 * �޸�ָ����Ʒ��ָ���ֿ��еĿ����Ϣ����������������ɹ���;������������������
	 * @param storeId ָ���Ĳֿ�
	 * @param stockId ���Id
	 */
	public InventoryLockTask( GUID storeId, GUID stockId) {
		this.storeId = storeId;
		this.stockId = stockId;
	}
	
	public GUID getStorId() {
		return storeId;
	}
	public GUID getStockId() {
		return stockId;
	}

	public void setLockedCount(Double lockedCount) {
		this.lockedCount = lockedCount;
	}

	public Double getLockedCount() {
		return lockedCount;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}
}
