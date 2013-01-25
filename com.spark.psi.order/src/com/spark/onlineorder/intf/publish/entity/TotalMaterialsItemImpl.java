package com.spark.onlineorder.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.onlineorder.entity.TotalMaterialsItem;
/**
 * 根据商品汇总信息，查询所需材料汇总信息<BR>
 * 查询方法：<BR>
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
		private GUID goodsId;//	商品Guid
		private String goodsCode;//	商品编号
		private String goodsNo;//	商品条码
		private String goodsName;//	商品名称
		private String goodsSpec;//	商品规格
		private String unit;//	单位
		private int goodsScale;//	商品小数位数
		private double count;//	数量
		//TODO
//		private double lockCount;//锁定数量
		private GUID bomId;//	bom表Id
		
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
		private GUID MaterialId;//	商品Guid
		private String MaterialCode;//	商品编号
		private String MaterialNo;//	商品条码
		private String MaterialName;//	商品名称
		private String MaterialSpec;//	商品规格
		private String unit;//	单位
		private int MaterialScale;//	商品小数位数
		private double count;//	数量
		
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
