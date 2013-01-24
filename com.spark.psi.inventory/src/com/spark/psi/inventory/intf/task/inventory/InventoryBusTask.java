package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.inventory.service.resource.InventoryDetEntity;
import com.spark.psi.publish.InventoryType;

/**
 * �޸Ŀ����������ƽ�����ɱ�
 *
 */
public class InventoryBusTask extends SimpleTask {
	private GUID storeId;
	private GUID stockId;
	private Double changeCount;
	private Double changeAmount;
	private boolean isUpdateAvgPrice = true;
	
	/**
	 * ��ƽ�����ɱ�
	 */
	private Double newCost;
	/**
	 * �Ƿ��̵�
	 */
	private boolean isCount = false;
	/**
	 * �Ƿ�����
	 */
	private boolean isRetail = false;
	
	private InventoryType inventoryType = InventoryType.Materials;
	
	private DetItem[] dets;
	
	
	/**
	 * �޸�ָ����Ʒ��ָ���ֿ��еĿ����Ϣ����������������ɹ���;������������������
	 * @param tenantsGuid ָ�����⻧
	 * @param storeId ָ���Ĳֿ�
	 * @param stockId ���Id
	 */
	public InventoryBusTask( GUID storeId, GUID stockId) {
		this.storeId = storeId;
		this.stockId = stockId;
	}
	
	public GUID getStoreId() {
		return storeId;
	}
	public GUID getStockId() {
		return stockId;
	}
	public Double getChangeCount() {
		return changeCount;
	}
	public Double getChangeAmount() {
		return changeAmount;
	}
	
	/**
	 * ���ÿ��ı������͵�λ
	 * �ۼӿ�������������������ٿ���������븺��
	 * @param changeCount
	 * @param unitCost ����
	 */
	public void setChangeCountAndUnitCost(Double changeCount, Double unitCost) {
		if(null == changeCount || null == unitCost) return;
		this.changeCount = changeCount;
		this.changeAmount = DoubleUtil.mul(changeCount , unitCost,2); 
	}
	/**
	 * ���ÿ�������ı����Ϳ����ı���
	 * �ۼӿ�������������������ٿ���������븺��(���ͬ)
	 * @param changeCount 
	 * @param changeAmount
	 */
	public void setChangeCountAndAmount(Double changeCount, Double changeAmount) {
		if(null == changeCount || null == changeAmount) return;
		this.changeCount = changeCount;
		this.changeAmount = changeAmount;
	}
	/**
	 * ���ÿ��仯��
	 * (���ֻ�����˸ı�������û�иı����øı����ı���Ĭ��Ϊ
	 * ��ǰ����Ʒ��ƽ�����ɱ� * �˴θı������)
	 * @param changeCount
	 */
	public void setChangeCount(Double changeCount) {
		this.changeCount = changeCount;
	}

	
	public boolean isUpdateAvgPrice() {
		return isUpdateAvgPrice;
	}
	/**
	 * �����Ƿ������Ʒ��ƽ�����ɱ� (Ĭ��Ϊtrue)
	 * @param isUpdateAvgPrice
	 */
	public void setUpdateAvgPrice(boolean isUpdateAvgPrice) {
		this.isUpdateAvgPrice = isUpdateAvgPrice;
	}

	public void setNewCost(Double newCost) {
		this.newCost = newCost;
	}

	public Double getNewCost() {
		return newCost;
	}

	public void setCount(boolean isCount) {
		this.isCount = isCount;
	}

	public boolean isCount() {
		return isCount;
	}

	public void setRetail(boolean isRetail) {
		this.isRetail = isRetail;
	}

	public boolean isRetail() {
		return isRetail;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public void setDets(DetItem[] dets) {
		this.dets = dets;
	}

	public DetItem[] getDets() {
		return dets;
	}
	
	public class DetItem
	{
		private GUID shelfId;//	��λ��ʶ
		private int shelfNo;//��λ���
		private int tiersNo;//	������ڲ���
		private GUID stockId;//	�����ʶ
		private double changeCount;//	�������
		private long produceDate;//	��������
		private GUID storeId;//	�ֿ�id
		private GUID inventoryId; // ���Id
		public int getShelfNo() {
			return shelfNo;
		}
		public GUID getShelfId() {
			return shelfId;
		}
		public int getTiersNo() {
			return tiersNo;
		}
		public GUID getStockId() {
			return stockId;
		}
		public double getChangeCount() {
			return changeCount;
		}
		public long getProduceDate() {
			return produceDate;
		}
		public GUID getStoreId() {
			return storeId;
		}
		/**
		 * 
		 * @param shelfId ��λID
		 * @param shelfNo ��λ���
		 * @param tiersNo ����
		 * @param stockId ���ID
		 * @param changeCount �仯����
		 * @param produceDate ��������
		 * @param storeId �ֿ�ID
		 */
		public DetItem(GUID shelfId,int shelfNo,int tiersNo, GUID stockId, double changeCount, long produceDate, GUID storeId) {
			super();
			this.shelfId = shelfId;
			this.shelfNo = shelfNo;
			this.tiersNo = tiersNo;
			this.stockId = stockId;
			this.changeCount = changeCount;
			this.produceDate = produceDate;
			this.storeId = storeId;
		}
		public void setInventoryId(GUID inventoryId) {
			this.inventoryId = inventoryId;
		}
		public GUID getInventoryId() {
			return inventoryId;
		}
		
		
	}

}
