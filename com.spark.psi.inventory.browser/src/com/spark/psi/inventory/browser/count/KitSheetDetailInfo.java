package com.spark.psi.inventory.browser.count;

import com.jiuqi.dna.core.type.GUID;

/**
 * ����������Ʒ��������ݴ���
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
		 * ��Ʒ����
		 */
		public String getKitName() {
			return kitName;
		}
		
		/**
		 * ��Ʒ����
		 */
		public void setKitName(String kitName) {
			this.kitName = kitName;
		}

		/**
		 * ��Ʒ����
		 */
		public String getKitDesc() {
			return kitDesc;
		}

		/**
		 * ��Ʒ����
		 */
		public void setKitDesc(String kitDesc) {
			this.kitDesc = kitDesc;
		}

		/**
		 * ��Ʒ��λ
		 */
		public String getKitUnit() {
			return kitUnit;
		}

		/**
		 * ��Ʒ��λ
		 */
		public void setKitUnit(String kitUnit) {
			this.kitUnit = kitUnit;
		}

		/**
		 * ��Ʒ��������/��ʼ������/�������
		 */
		public double getCount() {
			return count;
		}

		/**
		 * ��Ʒ��������/��ʼ������/�������
		 */
		public void setCount(double count) {
			this.count = count;
		}

		/**
		 * ��Ʒʵ������
		 */
		public double getActualCount() {
			return actualCount;
		}

		/**
		 * ��Ʒʵ������
		 */
		public void setActualCount(double actualCount) {
			this.actualCount = actualCount;
		}

		/**
		 * ˵��
		 */
		public String getRemark() {
			return memo;
		}

		/**
		 * ˵��
		 */
		public void setMemo(String memo) {
			this.memo = memo;
		}
		
		/**
		 * ���ص�ǰ��Ʒ������
		 */
		public String getId(){
			//edit by leezizi:�򵥵��γɵ��ַ��������ظ����������׺����ֻ���
			return GUID.MD5Of(this.kitName + "~" +this.kitDesc + "~" +this.kitUnit).toString();
		}

		/**
		 * �Ƿ���ԭ�ֿ��д��ڣ��ж���������Ʒ������ԭʼ��Ʒ
		 * @return
		 */
		public boolean isExistInventory() {
			return existInventory;
		}

		/**
		 * �Ƿ���ԭ�ֿ��д��ڣ��ж���������Ʒ������ԭʼ��Ʒ
		 * @param existInventory
		 */
		public void setExistInventory(boolean existInventory) {
			this.existInventory = existInventory;
		}
		
		
	}
}