/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.store.storage.intf.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-11       zhongxin        
 * ============================================================*/

package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * �޸Ŀ����;����
 *
 */
public class InventoryOnWayTask extends SimpleTask {
	private GUID storeId;
	private GUID stockId;
	
	private Double onWayCount;
	private InventoryType inventoryType = InventoryType.Materials;
	
	public Double getOnWayCount() {
		return onWayCount;
	}

	/**
	 * �޸�ָ����Ʒ��ָ���ֿ��еĿ����Ϣ����������������ɹ���;������������������
	 * @param storeId ָ���Ĳֿ�
	 * @param stockId ���Id
	 * 
	 */
	public InventoryOnWayTask( GUID storeId, GUID stockId) {
		this.storeId = storeId;
		this.stockId = stockId;
	}
	
	public GUID getStoreId() {
		return storeId;
	}
	public GUID getStockId() {
		return stockId;
	}
	/**
	 * ���òɹ���;����
	 * @param onWayCount
	 */
	public void setOnWayCount(Double onWayCount) {
		this.onWayCount = onWayCount;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}
	
}
