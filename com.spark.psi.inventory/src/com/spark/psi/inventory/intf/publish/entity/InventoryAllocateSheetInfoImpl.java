package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo;

/**
 * ���������б���Ŀ<br>
 * ��ѯ���������ݵ�����ID��ѯInventoryAllocateSheetInfo����
 */
public class InventoryAllocateSheetInfoImpl implements InventoryAllocateSheetInfo{

	/**
	 * ������ID
	 * 
	 * @return
	 */
	private GUID sheetId;

	/**
	 * ����������
	 * 
	 * @return
	 */
	private String sheetNumber;

	/**
	 * ������״̬
	 * 
	 * @return
	 */
	private InventoryAllocateStatus status;

	/**
	 * Դ�ֿ�ID
	 * 
	 * @return
	 */
	private GUID sourceStoreId;

	/**
	 * Դ�ֿ�����
	 * 
	 * @return
	 */
	private String sourceStoreName;

	/**
	 * Ŀ�Ĳֿ�ID
	 * 
	 * @return
	 */
	private GUID destinationStoreId;

	/**
	 * Ŀ�Ĳֿ�����
	 * 
	 * @return
	 */
	private String destinationStoreName;

	/**
	 * ����ʱ��
	 * 
	 * @return
	 */
	private long allocateInDate;

	/**
	 * ����ʱ��
	 * 
	 * @return
	 */
	private long allocateOutDate;

	/**
	 * ����ʱ��
	 * 
	 * @return
	 */
	private long createDate;

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
	 * ����ԭ��
	 * 
	 * @return
	 */
	private String cause;
	
	/**
	 * �˻�ԭ��
	 * 
	 * @return
	 */
	private String danyCause;
	
	private String approvePerson;
	private long approveDate;
	private GUID approvePersonId;

	
	/**
	 * ������
	 * 
	 */
	private String allocateInName;

	/**
	 * ������
	 * 
	 */
	private String allocateOutName;


	public String getApprovePerson() {
		return approvePerson;
	}

	public void setApprovePerson(String approvePerson) {
		this.approvePerson = approvePerson;
	}


	public long getApproveDate() {
		return approveDate;
	}


	public void setApproveDate(long approveDate) {
		this.approveDate = approveDate;
	}


	public GUID getApprovePersonId() {
		return approvePersonId;
	}


	public void setApprovePersonId(GUID approvePersonId) {
		this.approvePersonId = approvePersonId;
	}


	public String getAllocateInName() {
		return allocateInName;
	}


	public void setAllocateInName(String allocateInName) {
		this.allocateInName = allocateInName;
	}


	public String getAllocateOutName() {
		return allocateOutName;
	}


	public void setAllocateOutName(String allocateOutName) {
		this.allocateOutName = allocateOutName;
	}


	/**
	 * ������Ʒ��Ŀ�б�
	 * 
	 * @return
	 */
	private AllocateGoodsItemImpl[] items;

	
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


	public InventoryAllocateStatus getStatus() {
		return status;
	}


	public void setStatus(InventoryAllocateStatus status) {
		this.status = status;
	}


	public GUID getSourceStoreId() {
		return sourceStoreId;
	}


	public void setSourceStoreId(GUID sourceStoreId) {
		this.sourceStoreId = sourceStoreId;
	}


	public String getSourceStoreName() {
		return sourceStoreName;
	}


	public void setSourceStoreName(String sourceStoreName) {
		this.sourceStoreName = sourceStoreName;
	}


	public GUID getDestinationStoreId() {
		return destinationStoreId;
	}


	public void setDestinationStoreId(GUID destinationStoreId) {
		this.destinationStoreId = destinationStoreId;
	}


	public String getDestinationStoreName() {
		return destinationStoreName;
	}


	public void setDestinationStoreName(String destinationStoreName) {
		this.destinationStoreName = destinationStoreName;
	}


	public long getAllocateInDate() {
		return allocateInDate;
	}


	public void setAllocateInDate(long allocateInDate) {
		this.allocateInDate = allocateInDate;
	}


	public long getAllocateOutDate() {
		return allocateOutDate;
	}


	public void setAllocateOutDate(long allocateOutDate) {
		this.allocateOutDate = allocateOutDate;
	}


	public long getCreateDate() {
		return createDate;
	}


	public void setCreateDate(long createDate) {
		this.createDate = createDate;
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


	public String getCause() {
		return cause;
	}


	public void setCause(String cause) {
		this.cause = cause;
	}


	public AllocateGoodsItemImpl[] getItems() {
		return items;
	}


	public void setItems(AllocateGoodsItemImpl[] items) {
		this.items = items;
	}


	public void setDanyCause(String danyCause) {
		this.danyCause = danyCause;
	}


	public String getDanyCause() {
		return danyCause;
	}


	/**
	 * ������Ʒ��Ŀ��Ϣ
	 */
	public static class AllocateGoodsItemImpl implements AllocateGoodsItem{

		private GUID id;
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
		private String goodsItemCode;
		
		private String stockNumber;

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
		 * ���ÿ��
		 * 
		 * @return
		 */
		private double availableCount;

		/**
		 * ��������
		 * 
		 * @return
		 */
		private double allocateCount;

		
		
		public GUID getId() {
			return id;
		}

		public void setId(GUID id) {
			this.id = id;
		}

		public GUID getGoodsItemId() {
			return goodsItemId;
		}

		public void setGoodsItemId(GUID goodsItemId) {
			this.goodsItemId = goodsItemId;
		}

		public String getGoodsItemCode() {
			return goodsItemCode;
		}

		public void setGoodsItemCode(String goodsItemCode) {
			this.goodsItemCode = goodsItemCode;
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

		public double getAvailableCount() {
			return availableCount;
		}

		public void setAvailableCount(double availableCount) {
			this.availableCount = availableCount;
		}

		public double getAllocateCount() {
			return allocateCount;
		}

		public void setAllocateCount(double allocateCount) {
			this.allocateCount = allocateCount;
		}

		public String getStockNumber() {
			return stockNumber;
		}

		public void setStockNumber(String stockNumber) {
			this.stockNumber = stockNumber;
		}
		
		

	}

}
