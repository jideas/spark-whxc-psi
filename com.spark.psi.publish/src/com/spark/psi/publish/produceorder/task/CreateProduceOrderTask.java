package com.spark.psi.publish.produceorder.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ProduceOrderStatus;
/**
 * 创建生产订单
 *
 */
public class CreateProduceOrderTask extends SimpleTask {



	private GUID id;//	行标识
	private String billsNo;//	订单编号
	private long planDate;//	计划完成日期
	private double goodsCount;//	商品数量
	private String remark;//	备注
	private String creator;//	制单人
	private long createDate;//	制单日期
	private long finishDate;//	完成日期
	private ProduceOrderStatus status;//	状态
	private GoodsItem[] goods;
	private MaterialsItem[] materials;
	private GUID[] onlineOrderIds;
	
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
	public ProduceOrderStatus getStatus() {
		return status;
	}
	public void setStatus(ProduceOrderStatus status) {
		this.status = status;
	}
	
	public GoodsItem[] getGoods() {
		return goods;
	}
	public void setGoods(GoodsItem[] goods) {
		this.goods = goods;
	}
	public MaterialsItem[] getMaterials() {
		return materials;
	}
	public void setMaterials(MaterialsItem[] materials) {
		this.materials = materials;
	}

	public void setOnlineOrderIds(GUID[] onlineOrderIds) {
		this.onlineOrderIds = onlineOrderIds;
	}
	public GUID[] getOnlineOrderIds() {
		return onlineOrderIds;
	}

	public class GoodsItem
	{
		private GUID id;//	行标识
		private GUID billsId;//	订单GUID
		private GUID goodsId;//	商品Guid
		private String goodsCode;//	商品编号
		private String goodsNo;//	商品条码
		private String goodsName;//	商品名称
		private String goodsSpec;//	商品规格
		private String unit;//	单位
		private int goodsScale;//	商品小数位数
		private double count;//	数量
		private double lockCount;//锁定数量
		private GUID bomId;//	bom表Id
		public GUID getId() {
			return id;
		}
		public void setId(GUID id) {
			this.id = id;
		}
		public GUID getBillsId() {
			return billsId;
		}
		public void setBillsId(GUID billsId) {
			this.billsId = billsId;
		}
		public GUID getGoodsId() {
			return goodsId;
		}
		public void setGoodsId(GUID goodsId) {
			this.goodsId = goodsId;
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
		public String getGoodsName() {
			return goodsName;
		}
		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}
		public String getGoodsSpec() {
			return goodsSpec;
		}
		public void setGoodsSpec(String goodsSpec) {
			this.goodsSpec = goodsSpec;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public int getGoodsScale() {
			return goodsScale;
		}
		public void setGoodsScale(int goodsScale) {
			this.goodsScale = goodsScale;
		}
		public double getCount() {
			return count;
		}
		public void setCount(double count) {
			this.count = count;
		}
		public GUID getBomId() {
			return bomId;
		}
		public void setBomId(GUID bomId) {
			this.bomId = bomId;
		}
		public void setLockCount(double lockCount) {
			this.lockCount = lockCount;
		}
		public double getLockCount() {
			return lockCount;
		}
	}
	
	public class MaterialsItem
	{
		private GUID id;//	行标识
		private GUID billsId;//	订单GUID
		private GUID MaterialId;//	商品Guid
		private String MaterialCode;//	商品编号
		private String MaterialNo;//	商品条码
		private String MaterialName;//	商品名称
		private String MaterialSpec;//	商品规格
		private String unit;//	单位
		private int MaterialScale;//	商品小数位数
		private double count;//	数量
		private GUID storeId;//	仓库ID
		private String storeName;//	仓库名称
		
		public GUID getId() {
			return id;
		}
		public void setId(GUID id) {
			this.id = id;
		}
		public GUID getBillsId() {
			return billsId;
		}
		public void setBillsId(GUID billsId) {
			this.billsId = billsId;
		}
		public GUID getMaterialId() {
			return MaterialId;
		}
		public void setMaterialId(GUID materialId) {
			MaterialId = materialId;
		}
		public String getMaterialCode() {
			return MaterialCode;
		}
		public void setMaterialCode(String materialCode) {
			MaterialCode = materialCode;
		}
		public String getMaterialNo() {
			return MaterialNo;
		}
		public void setMaterialNo(String materialNo) {
			MaterialNo = materialNo;
		}
		public String getMaterialName() {
			return MaterialName;
		}
		public void setMaterialName(String materialName) {
			MaterialName = materialName;
		}
		public String getMaterialSpec() {
			return MaterialSpec;
		}
		public void setMaterialSpec(String materialSpec) {
			MaterialSpec = materialSpec;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public int getMaterialScale() {
			return MaterialScale;
		}
		public void setMaterialScale(int materialScale) {
			MaterialScale = materialScale;
		}
		public double getCount() {
			return count;
		}
		public void setCount(double count) {
			this.count = count;
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
	}

}
