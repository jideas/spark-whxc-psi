package com.spark.psi.base.task.goods;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * 库存上、下限task
 * 处理库存上、下限， 库存金额上限的修改操作
 */
public class InventoryLimitTask extends SimpleTask {
	
		private GUID storeId, stockId;
	
		private double upperLimitCount = -1d;
		private double lowerLimitCount = -1d;
		private double upperLimitAmount = -1d;
		private InventoryType inventoryType = InventoryType.Materials;
		
		
		/**
		 * 修改库存金额上限
		 * @param storeId 仓库ID
		 * @param stockId 存货ID
		 * @param upperLimitAmount 库存金额上限
		 */
		public InventoryLimitTask(GUID storeId, GUID stockId,double upperLimitAmount) {
			this.storeId = storeId;
			this.stockId = stockId;
			this.upperLimitAmount = upperLimitAmount;
		}
		
		/**
		 * 修改库存数量上限，下限
		 * @param storeId 仓库ID
		 * @param stockId 存货ID
		 * @param upperLimitCount 库存数量上限
		 * @param lowerLimitCount 下限
		 */
		public InventoryLimitTask(GUID storeId, GUID stockId,double upperLimitCount,double lowerLimitCount) {
			this.storeId = storeId;
			this.stockId = stockId;
			this.lowerLimitCount = lowerLimitCount;
			this.upperLimitCount = upperLimitCount;
		}
		
		/**
		 * 修改库存数量上限，下限，库存金额上限
		 * @param storeId 仓库ID
		 * @param stockId 存货ID
		 * @param upperLimitCount 库存数量上限
		 * @param lowerLimitCount 库存数量下限
		 * @param upperLimitAmount 库存金额上限
		 */
		public InventoryLimitTask(GUID storeId, GUID stockId,double upperLimitCount,double lowerLimitCount,double upperLimitAmount) {
			this.storeId = storeId;
			this.stockId = stockId;
			this.lowerLimitCount = lowerLimitCount;
			this.upperLimitCount = upperLimitCount;
			this.upperLimitAmount = upperLimitAmount;
		}


		public GUID getStorId(){
        	return storeId;
        }


		public GUID getStockId(){
        	return stockId;
        }


		public double getUpperLimitCount(){
        	return upperLimitCount;
        }


		public double getLowerLimitCount(){
        	return lowerLimitCount;
        }


		public double getUpperLimitAmount(){
        	return upperLimitAmount;
        }

		public InventoryType getInventoryType() {
			return inventoryType;
		}

		public void setInventoryType(InventoryType inventoryType) {
			this.inventoryType = inventoryType;
		}

		
}
