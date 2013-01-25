package com.spark.deliver.intf.publish.entity;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.deliver.entity.DeliverInfoItem;

/**
 * 
 * ���͵�������ϸ
 *
 */
public class DeliverInfoItemImpl implements DeliverInfoItem {

	private GUID id;//	�б�ʶ
	private GUID deliverSheetId;//	���͵�ID
	private GUID onlineOrderId;//	���϶���Id
	private String onlineOrderNo;//	�������
	private String memberRealName;//	��Ա����
	private double orderAmount;//�������

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
