package com.spark.psi.publish.produceorder.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.ProduceOrderStatus;
/**
 * ������������
 *
 */
public class CreateProduceOrderTask extends SimpleTask {



	private GUID id;//	�б�ʶ
	private String billsNo;//	�������
	private long planDate;//	�ƻ��������
	private double goodsCount;//	��Ʒ����
	private String remark;//	��ע
	private String creator;//	�Ƶ���
	private long createDate;//	�Ƶ�����
	private long finishDate;//	�������
	private ProduceOrderStatus status;//	״̬
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
		private GUID id;//	�б�ʶ
		private GUID billsId;//	����GUID
		private GUID goodsId;//	��ƷGuid
		private String goodsCode;//	��Ʒ���
		private String goodsNo;//	��Ʒ����
		private String goodsName;//	��Ʒ����
		private String goodsSpec;//	��Ʒ���
		private String unit;//	��λ
		private int goodsScale;//	��ƷС��λ��
		private double count;//	����
		private double lockCount;//��������
		private GUID bomId;//	bom��Id
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
		private GUID id;//	�б�ʶ
		private GUID billsId;//	����GUID
		private GUID MaterialId;//	��ƷGuid
		private String MaterialCode;//	��Ʒ���
		private String MaterialNo;//	��Ʒ����
		private String MaterialName;//	��Ʒ����
		private String MaterialSpec;//	��Ʒ���
		private String unit;//	��λ
		private int MaterialScale;//	��ƷС��λ��
		private double count;//	����
		private GUID storeId;//	�ֿ�ID
		private String storeName;//	�ֿ�����
		
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
