package com.spark.psi.publish.onlineorder.key;

import com.jiuqi.dna.core.type.GUID;
/**
 * 根据商品汇总信息，查询所需材料汇总信息<BR>
 * 
 *
 */
public class GetTotalMaterialsKey {

	private GUID[] onlineOrderIds;

	public GetTotalMaterialsKey(GUID... onlineOrderIds) {
		super();
		this.onlineOrderIds = onlineOrderIds;
	}

	public GUID[] getOnlineOrderIds() {
		return onlineOrderIds;
	}
	private GoodsItem[] goodsItems;
	
	public GetTotalMaterialsKey() {
		super();
	}
	
	public void setGoodsItems(GoodsItem[] goodsItems) {
		this.goodsItems = goodsItems;
	}

	public GoodsItem[] getGoodsItems() {
		return goodsItems;
	}

	public class GoodsItem
	{
		private GUID goodsId;//	商品Guid
		private String goodsCode;//	商品编号
		private String goodsNo;//	商品条码
		private String goodsName;//	商品名称
		private String goodsSpec;//	商品规格
		private String unit;//	单位
		private int goodsScale;//	商品小数位数
		private double count;//	数量
		
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
		
	}
			
}
