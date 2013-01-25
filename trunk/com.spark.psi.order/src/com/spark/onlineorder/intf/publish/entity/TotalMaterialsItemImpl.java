package com.spark.onlineorder.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
/**
 * ������Ʒ������Ϣ����ѯ������ϻ�����Ϣ<BR>
 * ��ѯ������<BR>
 * List<TotalMaterialsItem> + GetTotalMaterialsKey
 *
 */
public class TotalMaterialsItemImpl implements TotalMaterialsItem {

	
	private GoodsItemImpl[] goods;
	private MaterialsItemImpl[] materials;
	
	public GoodsItemImpl[] getGoods() {
		return goods;
	}

	public void setGoods(GoodsItemImpl[] goods) {
		this.goods = goods;
	}

	public MaterialsItemImpl[] getMaterials() {
		return materials;
	}

	public void setMaterials(MaterialsItemImpl[] materials) {
		this.materials = materials;
	}

	public class GoodsItemImpl implements GoodsItem
	{
		private GUID goodsId;//	��ƷGuid
		private String goodsCode;//	��Ʒ���
		private String goodsNo;//	��Ʒ����
		private String goodsName;//	��Ʒ����
		private String goodsSpec;//	��Ʒ���
		private String unit;//	��λ
		private int goodsScale;//	��ƷС��λ��
		private double count;//	����
		//TODO
//		private double lockCount;//��������
		private GUID bomId;//	bom��Id
		
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
//		public void setLockCount(double lockCount) {
//			this.lockCount = lockCount;
//		}
//		public double getLockCount() {
//			return lockCount;
//		}
	}
	
	public class MaterialsItemImpl implements MaterialsItem
	{
		private GUID MaterialId;//	��ƷGuid
		private String MaterialCode;//	��Ʒ���
		private String MaterialNo;//	��Ʒ����
		private String MaterialName;//	��Ʒ����
		private String MaterialSpec;//	��Ʒ���
		private String unit;//	��λ
		private int MaterialScale;//	��ƷС��λ��
		private double count;//	����
		
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

	}

}