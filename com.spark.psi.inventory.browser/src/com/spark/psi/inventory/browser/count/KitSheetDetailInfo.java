package com.spark.psi.inventory.browser.count;

import com.jiuqi.dna.core.type.GUID;

/**
 * 用于其它物品界面的数据传递
 */
public class KitSheetDetailInfo{
	
	private String store;
	private String creator;
	private Kit[] kits;	
	
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public Kit[] getKits() {
		return kits;
	}

	public void setKits(Kit[] kits) {
		this.kits = kits;
	}
	
	public final static class  Kit {
		
		private String kitName;	
		private String kitDesc;	
		private String kitUnit;
		private double count;	
		private double actualCount;	
		private String memo;		
		private boolean existInventory;
		
		public Kit(){
			this.count = 0;
			this.actualCount = 0;
		}
		
		public Kit(String kitName,String kitDesc,String kitUnit,double count){
			this.kitName = kitName;
			this.kitDesc = kitDesc;
			this.kitUnit = kitUnit;
			this.count = count;
		}
		
		public Kit(String kitName,String kitDesc,String kitUnit,double count,double actualCount,String memo){
			this.kitName = kitName;
			this.kitDesc = kitDesc;
			this.kitUnit = kitUnit;
			this.count = count;
			this.actualCount = actualCount;
			this.memo = memo;
		}

		/**
		 * 物品名称
		 */
		public String getKitName() {
			return kitName;
		}
		
		/**
		 * 物品名称
		 */
		public void setKitName(String kitName) {
			this.kitName = kitName;
		}

		/**
		 * 物品描述
		 */
		public String getKitDesc() {
			return kitDesc;
		}

		/**
		 * 物品描述
		 */
		public void setKitDesc(String kitDesc) {
			this.kitDesc = kitDesc;
		}

		/**
		 * 物品单位
		 */
		public String getKitUnit() {
			return kitUnit;
		}

		/**
		 * 物品单位
		 */
		public void setKitUnit(String kitUnit) {
			this.kitUnit = kitUnit;
		}

		/**
		 * 物品账面数量/初始化数量/库存数量
		 */
		public double getCount() {
			return count;
		}

		/**
		 * 物品账面数量/初始化数量/库存数量
		 */
		public void setCount(double count) {
			this.count = count;
		}

		/**
		 * 物品实际数量
		 */
		public double getActualCount() {
			return actualCount;
		}

		/**
		 * 物品实际数量
		 */
		public void setActualCount(double actualCount) {
			this.actualCount = actualCount;
		}

		/**
		 * 说明
		 */
		public String getRemark() {
			return memo;
		}

		/**
		 * 说明
		 */
		public void setMemo(String memo) {
			this.memo = memo;
		}
		
		/**
		 * 返回当前物品的主键
		 */
		public String getId(){
			//edit by leezizi:简单的形成的字符串容易重复，并且容易和数字混淆
			return GUID.MD5Of(this.kitName + "~" +this.kitDesc + "~" +this.kitUnit).toString();
		}

		/**
		 * 是否在原仓库中存在，判断是新增物品，还是原始物品
		 * @return
		 */
		public boolean isExistInventory() {
			return existInventory;
		}

		/**
		 * 是否在原仓库中存在，判断是新增物品，还是原始物品
		 * @param existInventory
		 */
		public void setExistInventory(boolean existInventory) {
			this.existInventory = existInventory;
		}
		
		
	}
}