package com.spark.psi.inventory.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo;

/**
 * 库存调拨单列表项目<br>
 * 查询方法：根据调拨单ID查询InventoryAllocateSheetInfo对象
 */
public class InventoryAllocateSheetInfoImpl implements InventoryAllocateSheetInfo{

	/**
	 * 调拨单ID
	 * 
	 * @return
	 */
	private GUID sheetId;

	/**
	 * 调拨单号码
	 * 
	 * @return
	 */
	private String sheetNumber;

	/**
	 * 调拨单状态
	 * 
	 * @return
	 */
	private InventoryAllocateStatus status;

	/**
	 * 源仓库ID
	 * 
	 * @return
	 */
	private GUID sourceStoreId;

	/**
	 * 源仓库名称
	 * 
	 * @return
	 */
	private String sourceStoreName;

	/**
	 * 目的仓库ID
	 * 
	 * @return
	 */
	private GUID destinationStoreId;

	/**
	 * 目的仓库名称
	 * 
	 * @return
	 */
	private String destinationStoreName;

	/**
	 * 调入时间
	 * 
	 * @return
	 */
	private long allocateInDate;

	/**
	 * 调出时间
	 * 
	 * @return
	 */
	private long allocateOutDate;

	/**
	 * 创建时间
	 * 
	 * @return
	 */
	private long createDate;

	/**
	 * 制单人ID
	 * 
	 * @return
	 */
	private GUID creatorId;

	/**
	 * 制单人名称
	 * 
	 * @return
	 */
	private String creatorName;

	/**
	 * 调拨原因
	 * 
	 * @return
	 */
	private String cause;
	
	/**
	 * 退回原因
	 * 
	 * @return
	 */
	private String danyCause;
	
	private String approvePerson;
	private long approveDate;
	private GUID approvePersonId;

	
	/**
	 * 调入人
	 * 
	 */
	private String allocateInName;

	/**
	 * 调出人
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
	 * 调拨商品条目列表
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
	 * 调拨商品条目信息
	 */
	public static class AllocateGoodsItemImpl implements AllocateGoodsItem{

		private GUID id;
		/**
		 * 商品条目ID
		 * 
		 * @return
		 */
		private GUID goodsItemId;

		/**
		 * 商品条目编码
		 * 
		 * @return
		 */
		private String goodsItemCode;
		
		private String stockNumber;

		/**
		 * 商品条目名称
		 * 
		 * @return
		 */
		private String goodsItemName;

		/**
		 * 商品条目属性
		 * 
		 * @return
		 */
		private String goodsItemProperties;

		/**
		 * 商品条目单位
		 * 
		 * @return
		 */
		private String goodsItemUnit;

		/**
		 * 商品数量小数位
		 * 
		 * @return
		 */
		private int countDecimal;

		/**
		 * 可用库存
		 * 
		 * @return
		 */
		private double availableCount;

		/**
		 * 调拨数量
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
