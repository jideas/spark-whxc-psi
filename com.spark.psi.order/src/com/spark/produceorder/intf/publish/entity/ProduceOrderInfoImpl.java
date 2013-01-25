package com.spark.produceorder.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ProduceOrderStatus;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfo;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfoGoodsItem;
import com.spark.psi.publish.produceorder.entity.ProduceOrderInfoMaterialsItem;

/**
 * 生产订单详情
 * 
 */
public class ProduceOrderInfoImpl implements ProduceOrderInfo {

	private GUID id;// 行标识
	private String billsNo;// 订单编号
	private long planDate;// 计划完成日期
	private double goodsCount;// 商品数量
	private String remark;// 备注
	private String creator;// 制单人
	private long createDate;// 制单日期
	private long finishDate;// 完成日期
	private String approvePerson;// 审核人
	private long approveDate;// 审核时间
	private String rejectReason;

	private ProduceOrderStatus status;// 状态
	private ProduceOrderInfoGoodsItem[] goods;
	private ProduceOrderInfoMaterialsItem[] materials;
	private ReceivedLogImpl[] receivedLogs;
	private ReturnedLogImpl[] returnedLogs;

	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public String getBillsNo() {
		return billsNo;
	}

	public void setBillsNo(String billsNo) {
		this.billsNo = billsNo;
	}

	public long getPlanDate() {
		return planDate;
	}

	public void setPlanDate(long planDate) {
		this.planDate = planDate;
	}

	public double getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(double goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(long finishDate) {
		this.finishDate = finishDate;
	}

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

	public ProduceOrderStatus getStatus() {
		return status;
	}

	public void setStatus(ProduceOrderStatus status) {
		this.status = status;
	}

	public ProduceOrderInfoGoodsItem[] getGoods() {
		return goods;
	}

	public void setGoods(ProduceOrderInfoGoodsItem[] goods) {
		this.goods = goods;
	}

	public ProduceOrderInfoMaterialsItem[] getMaterials() {
		return materials;
	}

	public void setMaterials(ProduceOrderInfoMaterialsItem[] materials) {
		this.materials = materials;
	}

	public ReceivedLogImpl[] getReceivedLogs() {
		return receivedLogs;
	}

	public void setReceivedLogs(ReceivedLogImpl[] receivedLogs) {
		this.receivedLogs = receivedLogs;
	}

	public ReturnedLogImpl[] getReturnedLogs() {
		return returnedLogs;
	}

	public void setReturnedLogs(ReturnedLogImpl[] returnedLogs) {
		this.returnedLogs = returnedLogs;
	}
	
	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public class ReceivedLogImpl implements ReceivedLog {
		
		private GUID storeId;
		private String storeName;
		private String creator;

		private ItemImpl[] items;

		
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

		public String getCreator() {
			return creator;
		}

		public void setCreator(String creator) {
			this.creator = creator;
		}

		public void setItems(ItemImpl[] items) {
			this.items = items;
		}

		public ItemImpl[] getItems() {
			return items;
		}

		public class ItemImpl implements Item {
			private String materialName;
			private String materialSpec;
			private String materialUnit;
			private int scale;
			private double planCount;
			private double realCount;

			public double getPlanCount() {
				return planCount;
			}

			public void setPlanCount(double planCount) {
				this.planCount = planCount;
			}

			public double getRealCount() {
				return realCount;
			}

			public void setRealCount(double realCount) {
				this.realCount = realCount;
			}

			public String getMaterialName() {
				return materialName;
			}

			public void setMaterialName(String materialName) {
				this.materialName = materialName;
			}

			public String getMaterialSpec() {
				return materialSpec;
			}

			public void setMaterialSpec(String materialSpec) {
				this.materialSpec = materialSpec;
			}

			public String getMaterialUnit() {
				return materialUnit;
			}

			public void setMaterialUnit(String materialUnit) {
				this.materialUnit = materialUnit;
			}

			public int getScale() {
				return scale;
			}

			public void setScale(int scale) {
				this.scale = scale;
			}

		}

	}

	public class ReturnedLogImpl implements ReturnedLog {
		private GUID storeId;
		private String storeName;
		private String creator;

		private ItemImpl[] items;
		

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

		public String getCreator() {
			return creator;
		}

		public void setCreator(String creator) {
			this.creator = creator;
		}

		
		
		public void setItems(ItemImpl[] items) {
			this.items = items;
		}

		public ItemImpl[] getItems() {
			return items;
		}



		public class ItemImpl implements Item {
			private String materialName;
			private String materialSpec;
			private String materialUnit;
			private int scale;
			private double planCount;
			private double realCount;

			public double getPlanCount() {
				return planCount;
			}

			public void setPlanCount(double planCount) {
				this.planCount = planCount;
			}

			public double getRealCount() {
				return realCount;
			}

			public void setRealCount(double realCount) {
				this.realCount = realCount;
			}

			public String getMaterialName() {
				return materialName;
			}

			public void setMaterialName(String materialName) {
				this.materialName = materialName;
			}

			public String getMaterialSpec() {
				return materialSpec;
			}

			public void setMaterialSpec(String materialSpec) {
				this.materialSpec = materialSpec;
			}

			public String getMaterialUnit() {
				return materialUnit;
			}

			public void setMaterialUnit(String materialUnit) {
				this.materialUnit = materialUnit;
			}

			public int getScale() {
				return scale;
			}

			public void setScale(int scale) {
				this.scale = scale;
			}

		}

	}
}
