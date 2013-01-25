package com.spark.deliver.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.deliver.entity.DeliverInfoItem;

/**
 * 
 * 配送单订单明细
 *
 */
public class DeliverInfoItemImpl implements DeliverInfoItem {

	private GUID id;//	行标识
	private GUID deliverSheetId;//	配送单ID
	private GUID onlineOrderId;//	网上订单Id
	private String onlineOrderNo;//	订单编号
	private String memberRealName;//	会员名称
	private double orderAmount;//订单金额

	private ItemImpl[] items;
	
	public GUID getId() {
		return id;
	}

	public void setId(GUID id) {
		this.id = id;
	}

	public GUID getDeliverSheetId() {
		return deliverSheetId;
	}

	public void setDeliverSheetId(GUID deliverSheetId) {
		this.deliverSheetId = deliverSheetId;
	}

	public GUID getOnlineOrderId() {
		return onlineOrderId;
	}

	public void setOnlineOrderId(GUID onlineOrderId) {
		this.onlineOrderId = onlineOrderId;
	}

	public String getOnlineOrderNo() {
		return onlineOrderNo;
	}

	public void setOnlineOrderNo(String onlineOrderNo) {
		this.onlineOrderNo = onlineOrderNo;
	}

	public String getMemberRealName() {
		return memberRealName;
	}

	public void setMemberRealName(String memberRealName) {
		this.memberRealName = memberRealName;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public ItemImpl[] getItems() {
		return items;
	}

	public void setItems(ItemImpl[] items) {
		this.items = items;
	}

	public class ItemImpl implements Item
	{
		private GUID goodsItemId;
		private String goodsName;
		private String goodsSpec;
		private double count;
		public GUID getGoodsItemId() {
			return goodsItemId;
		}
		public void setGoodsItemId(GUID goodsItemId) {
			this.goodsItemId = goodsItemId;
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
		public double getCount() {
			return count;
		}
		public void setCount(double count) {
			this.count = count;
		}
	}
}
