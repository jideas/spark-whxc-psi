package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * �޸Ľ�������
 *
 */
public class InventoryDeliveringTask extends SimpleTask {
	private GUID storeId;
	private GUID stockId;
	
	private Double toDeliverCount;
	private InventoryType inventoryType = InventoryType.Materials;
	
	public Double getToDeliverCount() {
		return toDeliverCount;
	}

	/**
	 * �޸�ָ����Ʒ��ָ���ֿ��еĿ����Ϣ����������������ɹ���;������������������
	 * @param tenantsGuid ָ�����⻧
	 * @param storeId ָ���Ĳֿ�
	 * @param stockId ���Id
	 */
	public InventoryDeliveringTask( GUID storeId, GUID stockId) {
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
	 * ���ÿ��ı������͵�λ
	 * �ۼӿ�������������������ٿ���������븺��
	 * @param changeCount
	

	/**
	 * ���÷�����������
	 * @param toDeliverCount
	 */
	public void setToDeliverCount(Double toDeliverCount) {
		this.toDeliverCount = toDeliverCount;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}
	
}
