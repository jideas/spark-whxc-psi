package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryCountStatus;
import com.spark.psi.publish.InventoryCountType;
import com.spark.psi.publish.inventory.entity.InventoryCountSheetInfo;

/**
 * �̵㵥��ϸ��Ϣ<br>
 * ��ѯ�����������̵㵥ID��ѯInventoryCountSheetInfo����
 */
public class InventoryCountSheetInfoImpl implements InventoryCountSheetInfo{

	/**
	 * ����ID
	 * 
	 * @return
	 */
	private GUID sheetId;

	/**
	 * ���ݺ�
	 * 
	 * @return
	 */
	private String sheetNumber;

	/**
	 * �̵㵥����
	 * 
	 * @return
	 */
	private InventoryCountType sheetType;

	/**
	 * �̵㵥״̬
	 * 
	 * @return
	 */
	private InventoryCountStatus sheetstatus;

	/**
	 * ��ʼ����
	 * 
	 * @return
	 */
	private long startDate;

	/**
	 * ��������
	 * 
	 * @return
	 */
	private long endDate;

	/**
	 * �ֿ�ID
	 * 
	 * @return
	 */
	private GUID storeId;

	/**
	 * �ֿ�����
	 * 
	 * @return
	 */
	private String storeName;

	/**
	 * �Ƶ���ID
	 * 
	 * @return
	 */
	private GUID creatorId;

	/**
	 * �Ƶ�������
	 * 
	 * @return
	 */
	private String creatorName;
	/**
	 * �̵�������
	 */
	private String name;

	/**
	 * ��ע��Ϣ
	 * 
	 * @return
	 */
	private String memo;

	/**
	 * ��Ʒ�̵���Ŀ�б�
	 * 
	 * @return
	 */
	private GoodsCountItemImpl[] goodsCountItems;

	/**
	 * ������Ʒ�̵���Ŀ�б�
	 * 
	 * @return
	 */
	private KitCountItemImpl[] kitCountItems;

	public GUID getSheetId() {
		return sheetId;
	}

	public void setSheetId(GUID sheetId) {
		this.sheetId = sheetId;
	}

	public String getSheetNumber() {
		return sheetNumber;
	}

	public void setSheetNumber(String sheetNumber) {
		this.sheetNumber = sheetNumber;
	}

	public InventoryCountType getSheetType() {
		return sheetType;
	}

	public void setSheetType(InventoryCountType sheetType) {
		this.sheetType = sheetType;
	}

	public InventoryCountStatus getSheetstatus() {
		return sheetstatus;
	}

	public void setSheetstatus(InventoryCountStatus sheetstatus) {
		this.sheetstatus = sheetstatus;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public GUID getStoreId() {
		return storeId;
	}

	public void setStoreId(GUID storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public GUID getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(GUID creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getRemark() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public GoodsCountItemImpl[] getGoodsCountItems() {
		return goodsCountItems;
	}

	public void setGoodsCountItems(GoodsCountItemImpl[] goodsCountItems) {
		this.goodsCountItems = goodsCountItems;
	}

	public KitCountItemImpl[] getKitCountItems() {
		return kitCountItems;
	}

	public void setKitCountItems(KitCountItemImpl[] kitCountItems) {
		this.kitCountItems = kitCountItems;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * ��Ʒ�̵���Ŀ
	 */
	public static class GoodsCountItemImpl implements GoodsCountItem {

		/**
		 * ��Ʒ��ĿID
		 * 
		 * @return
		 */
		private GUID goodsItemId;

		/**
		 * ��Ʒ��Ŀ����
		 * 
		 * @return
		 */
		private String goodsCode;
		private String goodsNo;

		/**
		 * ��Ʒ��Ŀ����
		 * 
		 * @return
		 */
		private String goodsItemName;

		/**
		 * ��Ʒ��Ŀ����
		 * 
		 * @return
		 */
		private String goodsItemProperties;

		/**
		 * ��Ʒ��Ŀ��λ
		 * 
		 * @return
		 */
		private String goodsItemUnit;

		/**
		 * ��Ʒ����С��λ
		 * 
		 * @return
		 */
		private int countDecimal;

		/**
		 * ��Ʒ��������
		 * 
		 * @return
		 */
		private double count;

		/**
		 * ��Ʒʵ������
		 * 
		 * @return
		 */
		private double actualCount;

		/**
		 * ��ȡ˵��
		 * 
		 * @return
		 */
		private String memo;
		
		/**
		 * �Ƿ�����Ʒ
		 */
		private boolean existInventory;

		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		public void setGoodsItemId(GUID goodsItemId) {
			this.goodsItemId = goodsItemId;
		}

		public String getGoodsCode() {
			return goodsCode;
		}

		public void setGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
		}

		public String getGoodsNo() {
			return goodsNo;
		}

		public void setGoodsNo(String goodsNo) {
			this.goodsNo = goodsNo;
		}

		public String getGoodsItemName() {
			return goodsItemName;
		}

		public void setGoodsItemName(String goodsItemName) {
			this.goodsItemName = goodsItemName;
		}

		public String getGoodsItemProperties() {
			return goodsItemProperties;
		}

		public void setGoodsItemProperties(String goodsItemProperties) {
			this.goodsItemProperties = goodsItemProperties;
		}

		public String getGoodsItemUnit() {
			return goodsItemUnit;
		}

		public void setGoodsItemUnit(String goodsItemUnit) {
			this.goodsItemUnit = goodsItemUnit;
		}

		public int getScale() {
			return countDecimal;
		}

		public void setCountDecimal(int countDecimal) {
			this.countDecimal = countDecimal;
		}

		public double getCount() {
			return count;
		}

		public void setCount(double count) {
			this.count = count;
		}

		public double getActualCount() {
			return actualCount;
		}

		public void setActualCount(double actualCount) {
			this.actualCount = actualCount;
		}

		public String getRemark() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

		public void setExistInventory(boolean existInventory) {
			this.existInventory = existInventory;
		}

		public boolean isExistInventory() {
			return existInventory;
		}
		
		

	}

	/**
	 * ������Ʒ�̵���Ŀ
	 */
	public static class KitCountItemImpl implements  KitCountItem{
		/**
		 * ��Ʒ����
		 * 
		 * @return
		 */
		private String kitName;

		/**
		 * ��Ʒ����
		 * 
		 * @return
		 */
		private String kitDesc;

		/**
		 * ��Ʒ��λ
		 * 
		 * @return
		 */
		private String kitUnit;

		/**
		 * ��Ʒ��������
		 * 
		 * @return
		 */
		private double count;

		/**
		 * ��Ʒʵ������
		 * 
		 * @return
		 */
		private double actualCount;

		/**
		 * ˵��
		 * 
		 * @return
		 */
		private String memo;

		/**
		 * �Ƿ�����Ʒ
		 */
		private boolean existInventory;
		
		public String getKitName() {
			return kitName;
		}

		public void setKitName(String kitName) {
			this.kitName = kitName;
		}

		public String getKitDesc() {
			return kitDesc;
		}

		public void setKitDesc(String kitDesc) {
			this.kitDesc = kitDesc;
		}

		public String getKitUnit() {
			return kitUnit;
		}

		public void setKitUnit(String kitUnit) {
			this.kitUnit = kitUnit;
		}

		public double getCount() {
			return count;
		}

		public void setCount(double count) {
			this.count = count;
		}

		public double getActualCount() {
			return actualCount;
		}

		public void setActualCount(double actualCount) {
			this.actualCount = actualCount;
		}

		public String getRemark() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}

		public void setExistInventory(boolean existInventory) {
			this.existInventory = existInventory;
		}

		public boolean isExistInventory() {
			return existInventory;
		}
		

	}

}
