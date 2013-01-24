package com.spark.psi.base.task.goods;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryType;

/**
 * ����ϡ�����task
 * �������ϡ����ޣ� ��������޵��޸Ĳ���
 */
public class InventoryLimitTask extends SimpleTask {
	
		private GUID storeId, stockId;
	
		private double upperLimitCount = -1d;
		private double lowerLimitCount = -1d;
		private double upperLimitAmount = -1d;
		private InventoryType inventoryType = InventoryType.Materials;
		
		
		/**
		 * �޸Ŀ��������
		 * @param storeId �ֿ�ID
		 * @param stockId ���ID
		 * @param upperLimitAmount ���������
		 */
		public InventoryLimitTask(GUID storeId, GUID stockId,double upperLimitAmount) {
			this.storeId = storeId;
			this.stockId = stockId;
			this.upperLimitAmount = upperLimitAmount;
		}
		
		/**
		 * �޸Ŀ���������ޣ�����
		 * @param storeId �ֿ�ID
		 * @param stockId ���ID
		 * @param upperLimitCount �����������
		 * @param lowerLimitCount ����
		 */
		public InventoryLimitTask(GUID storeId, GUID stockId,double upperLimitCount,double lowerLimitCount) {
			this.storeId = storeId;
			this.stockId = stockId;
			this.lowerLimitCount = lowerLimitCount;
			this.upperLimitCount = upperLimitCount;
		}
		
		/**
		 * �޸Ŀ���������ޣ����ޣ����������
		 * @param storeId �ֿ�ID
		 * @param stockId ���ID
		 * @param upperLimitCount �����������
		 * @param lowerLimitCount �����������
		 * @param upperLimitAmount ���������
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
