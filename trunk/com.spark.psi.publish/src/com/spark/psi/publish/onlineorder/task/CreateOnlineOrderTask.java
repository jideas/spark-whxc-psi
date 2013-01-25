package com.spark.psi.publish.onlineorder.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.OnlineOrderStatus;

public class CreateOnlineOrderTask extends SimpleTask {

	private GUID memberId;
	private String realName;
	private String consignee;
	private String consigneeTel;
	private String address;
	private long deliveryeDate;
	private String remark;
	private double disAmount;
	private double totalAmount;
	private String type;
	private GUID stationId;
	private String stationName;
	private boolean isToDoor;
	private Item[] items;

	public GUID getMemberId() {
		return memberId;
	}

	public void setMemberId(GUID memberId) {
		this.memberId = memberId;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getDeliveryeDate() {
		return deliveryeDate;
	}

	public void setDeliveryeDate(long deliveryeDate) {
		this.deliveryeDate = deliveryeDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getDisAmount() {
		return disAmount;
	}

	public void setDisAmount(double disAmount) {
		this.disAmount = disAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public GUID getStationId() {
		return stationId;
	}

	public void setStationId(GUID stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public CreateOnlineOrderTask() {
	}
	
	public void setItems(Item[] items) {
		this.items = items;
	}

	public Item[] getItems() {
		return items;
	}

	public void setToDoor(boolean isToDoor) {
		this.isToDoor = isToDoor;
	}

	public boolean isToDoor() {
		return isToDoor;
	}

	public class Item
	{
		private GUID goodsId;//	商品ID
		private String goodsCode;//	商品编码
		private String goodsNo;//	商品条码
		private String goodsName;//	商品名称
		private String goodsSpec;//	商品规格
		private String unit;//	单位
		private int goodsScale;//	商品小数位数
		private double price;//	单价
		private double count;//	数量
		private double discount;//	折扣率
		private double disAmount;//	折扣额
		private double amount;//	金额
		
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
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public double getCount() {
			return count;
		}
		public void setCount(double count) {
			this.count = count;
		}
		public double getDiscount() {
			return discount;
		}
		public void setDiscount(double discount) {
			this.discount = discount;
		}
		public double getDisAmount() {
			return disAmount;
		}
		public void setDisAmount(double disAmount) {
			this.disAmount = disAmount;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}

	}

}
