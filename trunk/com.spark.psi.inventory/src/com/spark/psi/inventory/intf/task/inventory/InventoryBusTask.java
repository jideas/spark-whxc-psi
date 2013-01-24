package com.spark.psi.inventory.intf.task.inventory;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.psi.inventory.service.resource.InventoryDetEntity;
import com.spark.psi.publish.InventoryType;

/**
 * 修改库存数量、金额、平均库存成本
 *
 */
public class InventoryBusTask extends SimpleTask {
	private GUID storeId;
	private GUID stockId;
	private Double changeCount;
	private Double changeAmount;
	private boolean isUpdateAvgPrice = true;
	
	/**
	 * 新平均库存成本
	 */
	private Double newCost;
	/**
	 * 是否盘点
	 */
	private boolean isCount = false;
	/**
	 * 是否零售
	 */
	private boolean isRetail = false;
	
	private InventoryType inventoryType = InventoryType.Materials;
	
	private DetItem[] dets;
	
	
	/**
	 * 修改指定商品在指定仓库中的库存信息：库存数量，库存金额，采购在途数量，发货需求数量
	 * @param tenantsGuid 指定的租户
	 * @param storeId 指定的仓库
	 * @param stockId 存货Id
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
	 * 设置库存改变数量和单位
	 * 累加库存数量传入正数，减少库存数量传入负数
	 * @param changeCount
	 * @param unitCost 单价
	 */
	public void setChangeCountAndUnitCost(Double changeCount, Double unitCost) {
		if(null == changeCount || null == unitCost) return;
		this.changeCount = changeCount;
		this.changeAmount = DoubleUtil.mul(changeCount , unitCost,2); 
	}
	/**
	 * 设置库存数量改变量和库存金额改变量
	 * 累加库存数量传入正数，减少库存数量传入负数(金额同)
	 * @param changeCount 
	 * @param changeAmount
	 */
	public void setChangeCountAndAmount(Double changeCount, Double changeAmount) {
		if(null == changeCount || null == changeAmount) return;
		this.changeCount = changeCount;
		this.changeAmount = changeAmount;
	}
	/**
	 * 设置库存变化量
	 * (如果只设置了改变数量，没有改变设置改变金额，则改变金额默认为
	 * 当前该商品的平均库存成本 * 此次改变的数量)
	 * @param changeCount
	 */
	public void setChangeCount(Double changeCount) {
		this.changeCount = changeCount;
	}

	
	public boolean isUpdateAvgPrice() {
		return isUpdateAvgPrice;
	}
	/**
	 * 设置是否更新商品的平均库存成本 (默认为true)
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
		private GUID shelfId;//	货位标识
		private int shelfNo;//货位编号
		private int tiersNo;//	存货所在层数
		private GUID stockId;//	存货标识
		private double changeCount;//	存货数量
		private long produceDate;//	生产日期
		private GUID storeId;//	仓库id
		private GUID inventoryId; // 库存Id
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
		 * @param shelfId 货位ID
		 * @param shelfNo 货位编号
		 * @param tiersNo 层数
		 * @param stockId 存货ID
		 * @param changeCount 变化数量
		 * @param produceDate 生产日期
		 * @param storeId 仓库ID
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
